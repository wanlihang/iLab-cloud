package com.ilab.service.util;

import com.alibaba.fastjson.JSONObject;
import com.ilab.service.model.PodInfo;
import com.ilab.service.model.TaskInfo;
import com.ruoyi.common.core.utils.StringUtils;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wanlh
 */
@Slf4j
public class KubernetesClientUtil {

    @Value("object")
    public static final String OBJECT = "D2SACScheduler";
    private KubernetesClient client;

    public KubernetesClientUtil() {
        Config config = new ConfigBuilder().withMasterUrl("https://192.168.103.121:6443").build();
        config.setTrustCerts(true);
        try {
            this.client = new DefaultKubernetesClient(config);
        } catch (Exception e) {
            log.error("[K8SClientUtil] [初始化集群连接失败]");
        }
    }

    public List<Pod> getAllPodList() {
        try {
            return this.client.pods().inAnyNamespace().list().getItems();
        } catch (KubernetesClientException e) {
            log.error("获取pod列表失败", e);
        }
        return Collections.emptyList();
    }

    public List<Node> getNodeListByLabel(Map<String, String> labels) {
        // 1. 验证参数
        if (labels.isEmpty()) {
            log.warn("[K8SClientServiceImpl] [getNodeListByLabel] [节点标签参数为空]");
            return Collections.emptyList();
        }

        // 3. 获取节点列表
        try {
            return this.client.nodes().withLabels(labels).list().getItems();
        } catch (KubernetesClientException e) {
            log.error("获取 node 列表失败", e);
        }
        return Collections.emptyList();
    }

    public void createPod(PodInfo podInfo) {
        // 1. 验证参数
        if (StringUtils.isBlank(podInfo.getPodName()) ||
            StringUtils.isBlank(podInfo.getImage()) ||
            StringUtils.isBlank(podInfo.getNamespace())) {
            log.warn("[K8SClientServiceImpl] [createPod] [容器参数验证未通过] " +
                "[podInfo:{}]", JSONObject.toJSONString(podInfo));
            return;
        }

        // 2. 创建容器
        try {
            PodFluent.MetadataNested<PodBuilder> podBuilderMetadataNested = new PodBuilder()
                .withNewMetadata()
                .withName(podInfo.getPodName());
            if (null != podInfo.getAnnotations()) {
                podBuilderMetadataNested = podBuilderMetadataNested.withAnnotations(podInfo.getAnnotations());
            }
            PodFluent.SpecNested<PodBuilder> specNested = podBuilderMetadataNested
                .endMetadata()
                .withNewSpec();
            if (null != podInfo.getNodeSelectorMap()) {
                specNested = specNested.addToNodeSelector(podInfo.getNodeSelectorMap());
            }
            PodSpecFluent.ContainersNested<PodFluent.SpecNested<PodBuilder>> ifNotPresent = specNested.addNewContainer()
                .withName(TransformerUtil.imageName2ContainerName(podInfo.getImage()))
                .withImage(podInfo.getImage()).withImagePullPolicy("IfNotPresent")
                .withCommand("/bin/bash", "-ce", "tail -f /dev/null*", "&& cd /app");
            if (null != podInfo.getResourcesRequests() || null != podInfo.getResourceLimits()) {
                ContainerFluent.ResourcesNested<PodSpecFluent.ContainersNested<PodFluent.SpecNested<PodBuilder>>> containersNestedResourcesNested = ifNotPresent.withNewResources();
                if (null != podInfo.getResourcesRequests()) {
                    containersNestedResourcesNested = containersNestedResourcesNested.addToRequests(podInfo.getResourceLimits());
                }
                if (null != podInfo.getResourceLimits()) {
                    containersNestedResourcesNested = containersNestedResourcesNested.addToLimits(podInfo.getResourceLimits());
                }
                ifNotPresent = containersNestedResourcesNested.endResources();
            }
            Pod podBuilder = ifNotPresent.endContainer()
                .endSpec().build();
            this.client.pods()
                .inNamespace(podInfo.getNamespace())
                .create(podBuilder);
        } catch (KubernetesClientException e) {
            log.error("[K8SClientServiceImpl] [createPod] [创建 pod 失败]", e);
        }
    }

    public void deletePod(Pod pod) {
        String podName = pod.getMetadata().getName();
        String namespace = pod.getMetadata().getNamespace();
        // 1. 验证参数
        if (StringUtils.isBlank(podName) || StringUtils.isBlank(namespace)) {
            log.warn("[K8SClientServiceImpl] [deletePod] [容器参数验证未通过] " +
                "[podInfo:{}]", JSONObject.toJSONString(pod));
            return;
        }
        // 2. 删除pod
        try {
            this.client.pods().inNamespace(namespace).withName(podName).delete();
        } catch (KubernetesClientException e) {
            log.error("[K8SClientServiceImpl] [deletePod] [删除pod失败]", e);
        }
    }

    public void deletePodByNamespace(String namespace) {
        // 1. 验证参数
        if (StringUtils.isBlank(namespace)) {
            log.warn("[K8SClientServiceImpl] [deletePodByNamespace] [容器参数验证未通过] " +
                "[namespace:{}]", namespace);
            return;
        }

        // 3. 删除pod
        try {
            this.client.pods().inNamespace(namespace).delete();
        } catch (KubernetesClientException e) {
            log.error("[K8SClientServiceImpl] [deletePodByNamespace] [删除pod失败]", e);
        }
    }

    public void execEquivalent(Pod pod, TaskInfo command, long l) {
        String podName = pod.getMetadata().getName();
        String namespace = pod.getMetadata().getNamespace();
        // 1. 验证参数
        if (StringUtils.isBlank(podName) || StringUtils.isBlank(namespace)) {
            log.warn("[K8SClientServiceImpl] [execEquivalent] [容器参数验证未通过] " +
                "[podInfo:{}]", JSONObject.toJSONString(pod));
            return;
        }

        // 2. 执行命令
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream error = new ByteArrayOutputStream();

            CountDownLatch execLatch = new CountDownLatch(1);

            String[] execCommand = new String[]{"/bin/bash", "-c", "cd /app && pwd && " + command.getTaskExecScript()};
            ExecWatch execWatch = this.client.pods().inNamespace(namespace).withName(podName).writingOutput(out).writingError(error).usingListener(new ExecListener() {
                @Override
                public void onOpen(Response response) {
                    log.info("[K8SClientServiceImpl] [MyPodExecListener] [控制台已开启]");
                }

                @Override
                public void onFailure(Throwable t, Response failureResponse) {
                    log.info("[K8SClientServiceImpl] [MyPodExecListener] [打开控制台出错]");
                    execLatch.countDown();
                }

                @Override
                public void onClose(int i, String s) {
                    log.info("[K8SClientServiceImpl] [MyPodExecListener] [控制台关闭中]");
                    execLatch.countDown();
                }
            }).exec(execCommand);

            boolean latchTerminationStatus = execLatch.await(l, TimeUnit.MILLISECONDS);
            if (!latchTerminationStatus) {
                log.warn("[K8SClientServiceImpl] [execEquivalent] [Latch could not terminate within specified time]");
            }
            log.info("[K8SClientServiceImpl] [execEquivalent] [Exec: {} {}] \n[Exec Output: \n{}]", JSONObject.toJSONString(podName), execCommand, out);
            execWatch.close();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            log.warn("[K8SClientServiceImpl] [execEquivalent] [Interrupted while waiting for the exec: {}]", ie.getMessage());
        }
    }

    /**
     * 重试等待 pod 就绪
     *
     * @param podInfo pod信息
     */
    public Pod retryUntilPodReady(PodInfo podInfo) throws InterruptedException {
        Pod pod = new Pod();
        int retryNum = 1;
        while (retryNum <= 40) {
            try {
                pod = this.client.pods().inNamespace(podInfo.getNamespace()).withName(podInfo.getPodName())
                    .waitUntilReady(10, TimeUnit.SECONDS);
                break;
            } catch (Exception e) {
                retryNum++;
                log.info("[K8SClientServiceImpl] [retryUntilPodReady] [wait pod ready，重试第 {} 次]", retryNum);
                Thread.sleep(250);
            }
        }
        return pod;
    }

    public List<io.fabric8.kubernetes.api.model.Service> getAllServiceList() {
        try {
            return this.client.services().inAnyNamespace().list().getItems();
        } catch (KubernetesClientException e) {
            // Handle exception
            log.error("获取service列表失败", e);
        }
        return Collections.emptyList();
    }

    public static Integer getCode(String time) {
        if (time.equals(OBJECT)) {
            return Integer.MAX_VALUE;
        }
        return 1;
    }

    public void createService() {
        try {
            io.fabric8.kubernetes.api.model.Service service = new ServiceBuilder().withNewMetadata().withName("svc2").endMetadata().withNewSpec().withType("ExternalName").withExternalName("my.database.example.com").addNewPort().withName("80").withProtocol("TCP").withPort(80).endPort().endSpec().withNewStatus().withNewLoadBalancer().addNewIngress().withIp("146.148.47.155").endIngress().endLoadBalancer().endStatus().build();
            io.fabric8.kubernetes.api.model.Service createService = this.client.services().inNamespace("default").createOrReplace(service);
        } catch (KubernetesClientException ex) {
            // Handle exception
            ex.printStackTrace();
        }
    }

}
