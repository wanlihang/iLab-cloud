package com.ilab.service.algorithm;

import com.ilab.service.constant.CommonConstant;
import com.ilab.service.model.NodeInfo;
import com.ilab.service.model.TaskInfo;
import com.ilab.service.util.KubernetesClientUtil;
import com.ilab.service.util.MinMaxUtil;
import com.ilab.service.util.TaskCostCountUtil;
import com.ruoyi.common.core.utils.StringUtils;
import io.fabric8.kubernetes.api.model.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基于任务生命周期的调度方法
 *
 * @author wanlh
 */
@Deprecated
@Slf4j
@Component
public class ILabSchedulerBasedTaskLife {

    public NodeInfo getNodeInfo(TaskInfo taskInfo) {
        NodeInfo result = new NodeInfo();
        // 1. 预选
        List<NodeInfo> nodeInfoList = this.predicate(taskInfo);
        if (CollectionUtils.isEmpty(nodeInfoList)) {
            log.error("[ILabSchedulerBasedPodLife] [getNodeInfo] [没有能够满足任务预选条件的 node] [taskInfo: {}]", taskInfo);
            return result;
        }
        log.info("[ILabSchedulerBasedPodLife] [getNodeInfo] [预选 nodeInfoList: {}]", nodeInfoList);

        // 2. 优选
        result = this.priority(taskInfo, nodeInfoList);
        log.info("[ILabSchedulerBasedPodLife] [getNodeInfo] [优选 nodeInfo: {}]", result);

        return result;
    }

    /**
     * 筛掉不符合条件的 node
     *
     * @param taskInfo 任务信息
     * @return 节点列表
     */
    private List<NodeInfo> predicate(TaskInfo taskInfo) {
        List<NodeInfo> result = new ArrayList<>();

        // 1. 按标签筛选 node
        Map<String, String> labels = new HashMap<>(1);
        labels.put("gpu", taskInfo.getRequireGpu());
        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        List<Node> nodeList = kubernetesClientUtil.getNodeListByLabel(labels);

        // 2. 获取筛选结果，并转换类型
        for (Node node : nodeList) {
            Map<String, String> labelsMap = node.getMetadata().getLabels();
            String nodeName = labelsMap.getOrDefault("id", "master");
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.setNodeName(nodeName);
            result.add(nodeInfo);
        }

        return result;
    }

    /**
     * 给每个 node 打分，返回得分最高的 node
     *
     * @param taskInfo     任务信息
     * @param nodeInfoList 节点列表
     * @return 节点
     */
    private NodeInfo priority(TaskInfo taskInfo, List<NodeInfo> nodeInfoList) {
        NodeInfo result = new NodeInfo();
        List<String> nodeNameList = nodeInfoList.stream()
            .map(NodeInfo::getNodeName)
            .collect(Collectors.toList());

        // 2. 计算S2
        Map<String, Double> s2Score = this.getS2Score(taskInfo, nodeNameList);

        // 3. 计算S3
        Map<String, Double> s3Score = this.getS3Score(taskInfo, nodeNameList);

        // 4. 计算S4
        Map<String, Double> s4Score = this.getS4Score(taskInfo, nodeNameList);

        Map<String, Double> nodeScoreMap = new HashMap<>(nodeInfoList.size());
        for (String key : nodeNameList) {
            nodeScoreMap.put(key, s2Score.get(key) + s3Score.get(key) + s4Score.get(key));
        }
        log.info("[ILabSchedulerBasedTaskLife] [priority] [nodeScoreMap: {}]", nodeScoreMap);
        String nodeName = nodeScoreMap.entrySet()
            .stream()
            .max(Comparator.comparingDouble(Map.Entry::getValue))
            .get()
            .getKey();
        result.setNodeName(nodeName);
        return result;
    }

    /**
     * S2 = Tp(或Tp') + Tr(或Tr') +Td
     *
     * @param taskInfo     任务信息
     * @param nodeNameList 节点表
     * @return S2 Score
     */
    private Map<String, Double> getS2Score(TaskInfo taskInfo, List<String> nodeNameList) {
        Map<String, Double> scoreMap = new HashMap<>(nodeNameList.size());
        for (String nodeName : nodeNameList) {

            // 1. 等待时间 tp
            double tp = 0.0;

            // 2. 执行时间 tr
            boolean flag = true;
            double tr = 0.0;
            if (flag) {
                // 单任务状态下，执行时间
                tr = TaskCostCountUtil.getTaskCost(nodeName, taskInfo.getEnvId(), TaskCostCountUtil.EXEC_TIME_ID);
            } else {
                // 多任务状态下，执行时间
            }

            double s2 = tp + tr;
            scoreMap.put(nodeName, s2);
        }
        return MinMaxUtil.minMaxTransform(scoreMap, 70.0);

    }

    /**
     * S3 = ((Mcpu - Tcpu) ^ 2 +  (Mmem - Tmem) ^ 2 +  (Mnetwork - Tnetwork) ^ 2 + (Mgpu - Tgpu) ^ 2) / 4
     *
     * @param taskInfo     任务信息
     * @param nodeNameList 节点表
     * @return S3 Score
     */
    private Map<String, Double> getS3Score(TaskInfo taskInfo, List<String> nodeNameList) {
        // 1. 获取需要统计的资源余额指标
        List<Integer> costTypeList = getTaskCostType(taskInfo);

        Map<String, Double> scoreMap = new HashMap<>(nodeNameList.size());
        for (String nodeName : nodeNameList) {
            double s3 = TaskCostCountUtil.getS3ScoreByCostTypeList(nodeName, taskInfo.getEnvId(), costTypeList);
            scoreMap.put(nodeName, s3);
        }
        return MinMaxUtil.minMaxTransform(scoreMap, 30.0);
    }

    /**
     * S4 = 1 / (M * SystemScore)
     *
     * @param taskInfo     任务信息
     * @param nodeNameList 节点表
     * @return S4 Score
     */
    private Map<String, Double> getS4Score(TaskInfo taskInfo, List<String> nodeNameList) {
        // 1. 获取需要统计的资源余额指标
        List<Integer> costTypeList = getTaskCostType(taskInfo);

        // 2. 初始化性能分
        Map<String, Double> performanceScoreMap = new HashMap<>(3);
        performanceScoreMap.put("node1", 1039.3);
        performanceScoreMap.put("node2", 1060.4);
        performanceScoreMap.put("node3", 1208.5);

        Map<String, Double> scoreMap = new HashMap<>(nodeNameList.size());
        for (String nodeName : nodeNameList) {
            double m = TaskCostCountUtil.getNodeCurrentCost(nodeName, costTypeList);
            double performanceScore = performanceScoreMap.get(nodeName);
            double s4 = 1 / (m * performanceScore);
            scoreMap.put(nodeName, s4);
        }
        return MinMaxUtil.minMaxTransform(scoreMap, 50.0);
    }

    @Deprecated
    private List<Integer> getTaskCostType(TaskInfo taskInfo) {
        List<Integer> costTypeList = new ArrayList<>();
        if (StringUtils.equals(taskInfo.getRequireCpu(), CommonConstant.Y)) {
            costTypeList.add(TaskCostCountUtil.CPU_ID);
        }
        if (StringUtils.equals(taskInfo.getRequireGpu(), CommonConstant.Y)) {
            costTypeList.add(TaskCostCountUtil.GPU_ID);
            costTypeList.add(TaskCostCountUtil.GPU_MEM_ID);
        }
        if (StringUtils.equals(taskInfo.getRequireMemory(), CommonConstant.Y)) {
            costTypeList.add(TaskCostCountUtil.MEM_ID);
        }
        if (StringUtils.equals(taskInfo.getRequireNetwork(), CommonConstant.Y)) {
            costTypeList.add(TaskCostCountUtil.NET_ID);
        }
        return costTypeList;
    }
}
