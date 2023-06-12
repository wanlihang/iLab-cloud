package com.ilab.service.thread;

import cn.hutool.core.util.RandomUtil;
import com.ilab.service.algorithm.D2SACAlgorithm;
import com.ilab.service.algorithm.DA3CAlgorithm;
import com.ilab.service.algorithm.DSACAlgorithm;
import com.ilab.service.algorithm.NSGA3Algorithm;
import com.ilab.service.constant.NodeInfoConstant;
import com.ilab.service.constant.PodInfoConstant;
import com.ilab.service.model.NodeInfo;
import com.ilab.service.model.PodInfo;
import com.ilab.service.model.TaskInfo;
import com.ilab.service.util.KubernetesClientUtil;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodSpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ilab.service.util.ServiceContextUtil.iLabSchedulerBasedTaskLife;

/**
 * 任务调度模块
 *
 * @author wanlh
 */
@Slf4j
public class ILabScheduleExecutor implements Runnable {

/*
    private final String schedulerType = "iLabScheduler"; // 废弃
    private final String schedulerType = "KDS";
    private final String schedulerType = "RR";
    private final String schedulerType = "A3CScheduler"; // 5001
    private final String schedulerType = "NSGA3Scheduler"; // 5003
    private final String schedulerType = "D2SACScheduler"; // 5000
*/


    private static final AtomicInteger POS = new AtomicInteger(0);

    private final TaskInfo taskInfo;

    public ILabScheduleExecutor(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    /**
     * 任务调度线程启动器
     */
    @Override
    public void run() {
        String schedulerType = taskInfo.getSchedulerType();
        long submissionTime = System.currentTimeMillis();
        // 1. 初始化 pod 信息
        PodInfo podInfo = new PodInfo();
        podInfo.setNamespace(PodInfoConstant.DEFAULT_NAMESPACE);
        podInfo.setPodName(taskInfo.getTaskName());
        podInfo.setImage(taskInfo.getDockerImageTag());

        // 2. 选择 node 节点
        log.info("[ILabScheduleExecutor] [run] [任务调度开始]");
        NodeInfo nodeInfo = this.taskScheduler(taskInfo);
        long schedulerEndTime = System.currentTimeMillis();
        log.info("[ILabScheduleExecutor] [run] [任务调度结束] [ {} 任务调度时间: {}]", schedulerType, (schedulerEndTime - submissionTime) / 1000.0);

        Integer nodeId = -1;
        if (ObjectUtils.isNotEmpty(nodeInfo)) {
            Map<String, String> nodeSelectorMap = new HashMap<>(1);
            nodeSelectorMap.put(NodeInfoConstant.LABEL_HOSTNAME, nodeInfo.getNodeName());
            podInfo.setNodeSelectorMap(nodeSelectorMap);
            /*
 若需要gpu且调度到node4节点，则加上声明
            if (StringUtils.equals(NodeInfoConstant.NODE4, nodeInfo.getNodeName()) && StringUtils.equals(taskInfo.getRequireGpu(), CommonConstant.Y)) {
                Map<String, Quantity> resourcesRequests = new HashMap<>(2);
                resourcesRequests.put("tencent.com/vcuda-core", new Quantity("100"));
                resourcesRequests.put("tencent.com/vcuda-memory", new Quantity("31"));
                Map<String, Quantity> resourceLimits = new HashMap<>(2);
                resourceLimits.put("tencent.com/vcuda-core", new Quantity("100"));
                resourceLimits.put("tencent.com/vcuda-memory", new Quantity("31"));
                podInfo.setResourcesRequests(resourcesRequests);
                podInfo.setResourceLimits(resourceLimits);
            } else {
                Map<String, String> nodeSelectorMap = new HashMap<>(1);
                nodeSelectorMap.put(NodeInfoConstant.LABEL_HOSTNAME, nodeInfo.getNodeName());
                podInfo.setNodeSelectorMap(nodeSelectorMap);
            }
*/
        }

        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        Pod pod = new Pod();

        // 1. 创建容器
        kubernetesClientUtil.createPod(podInfo);
        // 2. 重试等待 pod ready
        try {
            pod = kubernetesClientUtil.retryUntilPodReady(podInfo);
        } catch (InterruptedException e) {
            log.error("[ILabScheduleExecutor] [run] [重试等待 pod ready 出错]", e);
            throw new RuntimeException(e);
        }
        if (ObjectUtils.isNotEmpty(nodeInfo)) {
            PodSpec podSpec = new PodSpec();
            podSpec.setNodeName(nodeInfo.getNodeName());
            pod.setSpec(podSpec);
        }
        nodeId = NodeInfoConstant.NODE_NAME_2_ID_MAP.get(pod.getSpec().getNodeName());
        log.info("[ILabScheduleExecutor] [run] [ nodeId: {}]", nodeId);
        ILabThreadPool.getInstance(nodeId).submitNodeThreadPool(
            new ILabTaskExecutor(pod, taskInfo, schedulerType, submissionTime, schedulerEndTime), nodeId);
    }

    private NodeInfo taskScheduler(TaskInfo taskInfo) {
        switch (taskInfo.getSchedulerType()) {
            case "KDS":
                return this.KDSAlgorithm();
            case "RR":
                return this.RRAlgorithm();
            case "NSGA3Scheduler":
                return this.NSGA3SchedulerAlgorithm(taskInfo);
            case "A3CScheduler":
                return this.A3CSchedulerAlgorithm(taskInfo);
            case "D2SACScheduler":
                return this.D2SACSchedulerAlgorithm(taskInfo);
            default:
                return this.KDSAlgorithm();
        }
    }

    private NodeInfo KDSAlgorithm() {
        NodeInfo nodeInfo = new NodeInfo();
        List<String> nodeNameList = new ArrayList<>(NodeInfoConstant.NODE_NAME_2_ID_MAP.keySet());
        int i = RandomUtil.randomInt(nodeNameList.size());
        nodeInfo.setNodeName(nodeNameList.get(i));
        return nodeInfo;
    }

    private NodeInfo RRAlgorithm() {
        NodeInfo nodeInfo = new NodeInfo();
        List<String> nodeNameList = new ArrayList<>(NodeInfoConstant.NODE_NAME_2_ID_MAP.keySet());
        int i = POS.get() % nodeNameList.size();
        POS.incrementAndGet();
        nodeInfo.setNodeName(nodeNameList.get(i));
        return nodeInfo;
    }

    @Deprecated
    private NodeInfo iLabSchedulerAlgorithm(TaskInfo taskInfo) {
        return iLabSchedulerBasedTaskLife.getNodeInfo(taskInfo);
    }

    private NodeInfo D2SACSchedulerAlgorithm(TaskInfo taskInfo) {
        return D2SACAlgorithm.getNodeInfo(taskInfo);
    }

    private NodeInfo A3CSchedulerAlgorithm(TaskInfo taskInfo) {
        return DA3CAlgorithm.getNodeInfo(taskInfo);
    }

    private NodeInfo DSACSchedulerAlgorithm(TaskInfo taskInfo) {
        return DSACAlgorithm.getNodeInfo(taskInfo);
    }

    private NodeInfo NSGA3SchedulerAlgorithm(TaskInfo taskInfo) {
        return NSGA3Algorithm.getNodeInfo(taskInfo);
    }

}
