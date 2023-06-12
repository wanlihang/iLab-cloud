package com.ilab.service.controller;

import cn.hutool.core.util.IdUtil;
import com.ilab.service.algorithm.ILabSchedulerBasedTaskLife;
import com.ilab.service.constant.DockerImageConstant;
import com.ilab.service.constant.PodInfoConstant;
import com.ilab.service.constant.PrometheusConstant;
import com.ilab.service.domain.CourseLabEnv;
import com.ilab.service.model.PodInfo;
import com.ilab.service.model.PrometheusResultInfo;
import com.ilab.service.model.TaskInfo;
import com.ilab.service.service.ICourseLabEnvService;
import com.ilab.service.util.KubernetesClientUtil;
import com.ilab.service.util.PrometheusInfoUtil;
import com.ilab.service.util.TransformerUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanlh
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiTestController extends BaseController {

    @Autowired
    private ILabSchedulerBasedTaskLife iLabSchedulerBasedTaskLife;

    @GetMapping("/createPod")
    public AjaxResult createPod() {
        PodInfo podInfo = new PodInfo();

        podInfo.setNamespace(PodInfoConstant.DEFAULT_NAMESPACE);
        podInfo.setPodName(PodInfoConstant.DEFAULT_POD_NAME + IdUtil.simpleUUID());
        podInfo.setImage(DockerImageConstant.LAB13);

        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        kubernetesClientUtil.createPod(podInfo);

        return AjaxResult.success();
    }

    @GetMapping("/getNodes")
    public AjaxResult getNodes() {
        Map<String, String> labels = new HashMap<>(8);
        labels.put("gpu", "n");
        labels.put("id", "node2");
        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        return AjaxResult.success(kubernetesClientUtil.getNodeListByLabel(labels));
    }

    @GetMapping("/deletePodByNamespace")
    public AjaxResult deletePodByNamespace(@RequestParam String namespace) {
        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        kubernetesClientUtil.deletePodByNamespace(namespace);
        return AjaxResult.success();
    }

    @Autowired
    private ICourseLabEnvService courseLabEnvService;

    @GetMapping("/testScheduler/{id}")
    public AjaxResult testScheduler(@PathVariable Integer id) {
        CourseLabEnv courseLabEnv = courseLabEnvService.selectCourseLabEnvById(id);
        if (ObjectUtils.isEmpty(courseLabEnv)) {
            return AjaxResult.error("查询任务负载信息失败");
        }
        TaskInfo taskInfo = new TaskInfo(courseLabEnv);
        return AjaxResult.success(iLabSchedulerBasedTaskLife.getNodeInfo(taskInfo));
    }

    @Value("${k8s.nodesName}")
    private String nodesName;

    @GetMapping("/getSystemInfo")
    public AjaxResult getSystemInfo() {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_GPU_USAGE, "node4");
        List<PrometheusResultInfo> systemInfo = PrometheusInfoUtil.getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return AjaxResult.success(0.0);
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return AjaxResult.success(valueStr);
    }

}
