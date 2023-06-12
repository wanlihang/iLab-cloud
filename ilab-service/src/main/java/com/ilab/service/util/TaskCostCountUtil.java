package com.ilab.service.util;

import com.ruoyi.common.core.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ilab.service.constant.NodeInfoConstant.NODE_NAME_LIST;

/**
 * @author TUF
 */
public class TaskCostCountUtil {

    /**
     * cpuCost、gpuCost、gpuMemoryCost、memoryCost、networkCost、execTime 在 map 中的位置
     */
    public static final Integer CPU_ID = 0;
    public static final Integer GPU_ID = 1;
    public static final Integer GPU_MEM_ID = 2;
    public static final Integer MEM_ID = 3;
    public static final Integer NET_ID = 4;
    public static final Integer EXEC_TIME_ID = 5;


    /**
     * 节点初始状态时资源负载情况
     */
    private static final Map<String, List<Double>> NODE_INIT_LOAD = new ConcurrentHashMap<>();

    /**
     * 节点上实时资源负载情况
     */
    private static final Map<String, List<Double>> NODE_CURRENT_LOAD = new ConcurrentHashMap<>();

    /**
     * 节点上正在执行的任务负载列表 (node - list(taskName, (envId, endTime)))
     */
    private static final Map<String, List<TaskExecInfo>> NODE_TASK_EXEC_MAP = new ConcurrentHashMap<>();

    /**
     * 每个任务负载的 cost (node - (envId - costList))
     */
    private static final Map<String, Map<Integer, List<Double>>> TASK_NODE_COST_MAP = new ConcurrentHashMap<>();


    static {
        // 1. 初始化 nodeLoad
        List<Double> node1Load = new ArrayList<>();
        node1Load.add(PrometheusInfoUtil.getCurrentCpuUsage("node1"));
        node1Load.add(0.0);
        node1Load.add(0.0);
        node1Load.add(PrometheusInfoUtil.getCurrentMemoryUsage("node1"));
        node1Load.add(PrometheusInfoUtil.getCurrentNetworkReceiveUsage("node1"));
        C = 0.535134;
        NODE_INIT_LOAD.put("node1", node1Load);
        NODE_CURRENT_LOAD.put("node1", node1Load);

        List<Double> node2Load = new ArrayList<>();
        node2Load.add(PrometheusInfoUtil.getCurrentCpuUsage("node2"));
        node2Load.add(0.0);
        node2Load.add(0.0);
        node2Load.add(PrometheusInfoUtil.getCurrentMemoryUsage("node2"));
        node2Load.add(PrometheusInfoUtil.getCurrentNetworkReceiveUsage("node2"));
        NODE_INIT_LOAD.put("node2", node2Load);
        NODE_CURRENT_LOAD.put("node2", node2Load);

        List<Double> node3Load = new ArrayList<>();
        node3Load.add(PrometheusInfoUtil.getCurrentCpuUsage("node3"));
        node3Load.add(0.0);
        node3Load.add(0.0);
        node3Load.add(PrometheusInfoUtil.getCurrentMemoryUsage("node3"));
        node3Load.add(PrometheusInfoUtil.getCurrentNetworkReceiveUsage("node3"));
        NODE_INIT_LOAD.put("node3", node3Load);
        NODE_CURRENT_LOAD.put("node3", node3Load);

        List<Double> node4Load = new ArrayList<>();
        node4Load.add(PrometheusInfoUtil.getCurrentCpuUsage("node4"));
        node4Load.add(PrometheusInfoUtil.getCurrentGpuUsage("node4"));
        node4Load.add(PrometheusInfoUtil.getCurrentGpuMemoryUsage("node4"));
        node4Load.add(PrometheusInfoUtil.getCurrentMemoryUsage("node4"));
        node4Load.add(PrometheusInfoUtil.getCurrentNetworkReceiveUsage("node4"));
        NODE_INIT_LOAD.put("node4", node4Load);
        NODE_CURRENT_LOAD.put("node4", node4Load);

        // 2. 初始化 taskCost
        S = "5000";
        Map<Integer, List<Double>> node1TaskCostMap = new HashMap<>();
        Map<Integer, List<Double>> node2TaskCostMap = new HashMap<>();
        Map<Integer, List<Double>> node3TaskCostMap = new HashMap<>();
        Map<Integer, List<Double>> node4TaskCostMap = new HashMap<>();
        // cpuCost、gpuCost、gpuMemoryCost、memoryCost、networkCost、execTime
        node1TaskCostMap.put(1, new ArrayList<>(Arrays.asList(5.265717959, 0.0, 0.0, 2.173451946, 0.0, 30.084)));
        node2TaskCostMap.put(1, new ArrayList<>(Arrays.asList(4.635114925, 0.0, 0.0, 2.659224477, 0.0, 24.192)));
        node3TaskCostMap.put(1, new ArrayList<>(Arrays.asList(4.736052068, 0.0, 0.0, 1.045374355, 0.0, 25.662)));
        node4TaskCostMap.put(1, new ArrayList<>(Arrays.asList(1.347234321, 0.0, 0.0, 0.045374355, 0.0, 6.372)));

        node1TaskCostMap.put(2, new ArrayList<>(Arrays.asList(9.412870204, 0.0, 0.0, 12.55689167, 0.0, 1296.356)));
        node2TaskCostMap.put(2, new ArrayList<>(Arrays.asList(6.145970029, 0.0, 0.0, 14.15521474, 0.0, 1322.447)));
        node3TaskCostMap.put(2, new ArrayList<>(Arrays.asList(7.745615862, 0.0, 0.0, 19.46172094, 0.0, 1395.674)));
        node4TaskCostMap.put(2, new ArrayList<>(Arrays.asList(3.347234321, 0.0, 0.0, 2.4413423, 0.0, 244.598)));

        node1TaskCostMap.put(3, new ArrayList<>(Arrays.asList(3.880934538, 0.0, 0.0, 1.865609422, 0.0, 37.352)));
        node2TaskCostMap.put(3, new ArrayList<>(Arrays.asList(9.16885166, 0.0, 0.0, 3.325948954, 0.0, 37.303)));
        node3TaskCostMap.put(3, new ArrayList<>(Arrays.asList(9.989909201, 0.0, 0.0, 3.18559079, 0.0, 38.805)));
        node4TaskCostMap.put(3, new ArrayList<>(Arrays.asList(0.723122132, 0.0, 0.0, 0.32323235, 0.0, 6.233)));

        node1TaskCostMap.put(4, new ArrayList<>(Arrays.asList(3.68405466, 0.0, 0.0, 2.76759557, 0.0, 10.35300000)));
        node2TaskCostMap.put(4, new ArrayList<>(Arrays.asList(2.53264207, 0.0, 0.0, 3.95403302, 0.0, 11.45350000)));
        node3TaskCostMap.put(4, new ArrayList<>(Arrays.asList(4.0010285, 0.0, 0.0, 2.69980363, 0.0, 12.68250000)));
        node4TaskCostMap.put(4, new ArrayList<>(Arrays.asList(0.0232432, 0.0, 0.0, 0.05653432, 0.0, 0.825)));

        node1TaskCostMap.put(5, new ArrayList<>(Arrays.asList(10.37894897, 0.0, 0.0, 0.50028653, 0.0, 25.0946)));
        node2TaskCostMap.put(5, new ArrayList<>(Arrays.asList(10.6728618, 0.0, 0.0, 0.45367773, 0.0, 25.0348000)));
        node3TaskCostMap.put(5, new ArrayList<>(Arrays.asList(10.63129446, 0.0, 0.0, 0.68104104, 0.0, 25.9652)));
        node4TaskCostMap.put(5, new ArrayList<>(Arrays.asList(5.5341343, 0.0, 0.0, 0.30324324, 0.0, 24.748)));

        node1TaskCostMap.put(6, new ArrayList<>(Arrays.asList(6.50485634, 0.0, 0.0, 0.07832146, 0.0, 253.259)));
        node2TaskCostMap.put(6, new ArrayList<>(Arrays.asList(10.13569333, 0.0, 0.0, 0.6197538, 0.0, 243.176)));
        node3TaskCostMap.put(6, new ArrayList<>(Arrays.asList(5.23525437, 0.0, 0.0, 0.22977576, 0.0, 247.547)));
        node4TaskCostMap.put(6, new ArrayList<>(Arrays.asList(3.65432453, 0.0, 0.0, 0.3645654, 0.0, 24.737)));

        node1TaskCostMap.put(7, new ArrayList<>(Arrays.asList(12.5849409, 0.0, 0.0, 3.48616331, 0.0, 204.89300000)));
        node2TaskCostMap.put(7, new ArrayList<>(Arrays.asList(10.6026423, 0.0, 0.0, 5.30526235, 0.0, 196.62250000)));
        node3TaskCostMap.put(7, new ArrayList<>(Arrays.asList(13.3426084, 0.0, 0.0, 3.52677415, 0.0, 245.59900000)));
        node4TaskCostMap.put(7, new ArrayList<>(Arrays.asList(7.3545234, 0.0, 0.0, 0.2044553, 0.0, 34.765)));

        node1TaskCostMap.put(8, new ArrayList<>(Arrays.asList(11.3660398, 0.0, 0.0, 3.74294104, 0.0, 76.78550000)));
        node2TaskCostMap.put(8, new ArrayList<>(Arrays.asList(9.99184266, 0.0, 0.0, 4.92089267, 0.0, 71.44800000)));
        node3TaskCostMap.put(8, new ArrayList<>(Arrays.asList(11.6885389, 0.0, 0.0, 4.21407497, 0.0, 84.55450000)));
        node4TaskCostMap.put(8, new ArrayList<>(Arrays.asList(1.347234321, 0.0, 0.0, 1.49234645, 0.0, 8.415)));

        node1TaskCostMap.put(9, new ArrayList<>(Arrays.asList(4.07696857, 0.0, 0.0, 2.30817161, 0.0, 15.61500000)));
        node2TaskCostMap.put(9, new ArrayList<>(Arrays.asList(2.7218035, 0.0, 0.0, 4.68862553, 0.0, 16.08300000)));
        node3TaskCostMap.put(9, new ArrayList<>(Arrays.asList(6.1037322, 0.0, 0.0, 2.1825662, 0.0, 17.05700000)));
        node4TaskCostMap.put(9, new ArrayList<>(Arrays.asList(0.0123545, 0.0, 0.0, 0.0855424, 0.0, 1.311)));

        node1TaskCostMap.put(10, new ArrayList<>(Arrays.asList(6.14590384, 0.0, 0.0, 0.68468044, 0.0, 32.164)));
        node2TaskCostMap.put(10, new ArrayList<>(Arrays.asList(3.03846337, 0.0, 0.0, 0.47790603, 0.0, 29.738)));
        node3TaskCostMap.put(10, new ArrayList<>(Arrays.asList(4.32801918, 0.0, 0.0, 0.45908388, 0.0, 32.236)));
        node4TaskCostMap.put(10, new ArrayList<>(Arrays.asList(0.09356456, 0.0, 0.0, 0.05343234, 0.0, 3.418)));

        node1TaskCostMap.put(11, new ArrayList<>(Arrays.asList(1.3660398, 0.0, 0.0, 0.23423432, 0.0, 10.664)));
        node2TaskCostMap.put(11, new ArrayList<>(Arrays.asList(0.61945655, 0.0, 0.0, 0.17841451, 0.0, 9.657)));
        node3TaskCostMap.put(11, new ArrayList<>(Arrays.asList(2.0181407, 0.0, 0.0, 0.00447183, 0.0, 7.211)));
        node4TaskCostMap.put(11, new ArrayList<>(Arrays.asList(0.0324724, 0.0, 0.0, 0.00232532, 0.0, 0.798)));

        node1TaskCostMap.put(12, new ArrayList<>(Arrays.asList(6.42644771, 0.0, 0.0, 0.62714644, 0.0, 16.793)));
        node2TaskCostMap.put(12, new ArrayList<>(Arrays.asList(3.0544512, 0.0, 0.0, 0.05277231, 0.0, 16.830)));
        node3TaskCostMap.put(12, new ArrayList<>(Arrays.asList(4.75675012, 0.0, 0.0, 0.00379866, 0.0, 17.285)));
        node4TaskCostMap.put(12, new ArrayList<>(Arrays.asList(0.01723721, 0.0, 0.0, 0.02023432, 0.0, 1.710)));

        node1TaskCostMap.put(13, new ArrayList<>(Arrays.asList(14.5033179, 0.0, 0.0, 5.45867169, 0.0, 306.90100000)));
        node2TaskCostMap.put(13, new ArrayList<>(Arrays.asList(12.9113279, 0.0, 0.0, 6.01694695, 0.0, 300.39800000)));
        node3TaskCostMap.put(13, new ArrayList<>(Arrays.asList(14.627352, 0.0, 0.0, 5.35390004, 0.0, 348.51333333)));
        node4TaskCostMap.put(13, new ArrayList<>(Arrays.asList(6.633454, 6.532433, 27.0917345, 5.4634343, 0.0, 22.951)));

        node1TaskCostMap.put(14, new ArrayList<>(Arrays.asList(3.78073885, 0.0, 0.0, 3.16038647, 1.533454228, 125.64300000)));
        node2TaskCostMap.put(14, new ArrayList<>(Arrays.asList(5.32109291, 0.0, 0.0, 4.17958983, 1.687796969, 128.98666667)));
        node3TaskCostMap.put(14, new ArrayList<>(Arrays.asList(3.99894741, 0.0, 0.0, 3.19333314, 1.373078778, 135.52900000)));
        node4TaskCostMap.put(14, new ArrayList<>(Arrays.asList(2.14324334, 7.0866343, 34.047455, 0.045374355, 1.68234348, 27.284)));

        node1TaskCostMap.put(15, new ArrayList<>(Arrays.asList(6.9160563, 0.0, 0.0, 4.51657713, 2.85433927, 53.9729)));
        node2TaskCostMap.put(15, new ArrayList<>(Arrays.asList(11.11445986, 0.0, 0.0, 4.44889296, 2.841956469, 49.6189)));
        node3TaskCostMap.put(15, new ArrayList<>(Arrays.asList(7.0696601, 0.0, 0.0, 2.95727819, 2.981054062, 58.0739)));
        node4TaskCostMap.put(15, new ArrayList<>(Arrays.asList(5.73455234, 8.1232538, 34.625242, 0.045374355, 2.76845462, 94.587)));

        node1TaskCostMap.put(16, new ArrayList<>(Arrays.asList(11.68162131, 0.0, 0.0, 6.31849177, 0.0, 822.367)));
        node2TaskCostMap.put(16, new ArrayList<>(Arrays.asList(11.64695476, 0.0, 0.0, 5.834317078, 0.0, 825.456)));
        node3TaskCostMap.put(16, new ArrayList<>(Arrays.asList(10.65288569, 0.0, 0.0, 3.439422326, 0.0, 819.742)));
        node4TaskCostMap.put(16, new ArrayList<>(Arrays.asList(5.9234364, 4.0102666, 36.284545, 4.9823434, 0.0, 35.799)));

        node1TaskCostMap.put(17, new ArrayList<>(Arrays.asList(14.7310486, 0.0, 0.0, 4.0466973, 0.0, 957.75000000)));
        node2TaskCostMap.put(17, new ArrayList<>(Arrays.asList(12.9164419, 0.0, 0.0, 5.17757351, 0.0, 958.00800000)));
        node3TaskCostMap.put(17, new ArrayList<>(Arrays.asList(15.0378188, 0.0, 0.0, 4.11037902, 0.0, 957.71650000)));
        node4TaskCostMap.put(17, new ArrayList<>(Arrays.asList(1.0343264, 17.4704, 17.9809, 1.00423438, 0.0, 8.446)));

        node1TaskCostMap.put(18, new ArrayList<>(Arrays.asList(1.24732253, 0.0, 0.0, 9.18657313, 0.0, 21.4296)));
        node2TaskCostMap.put(18, new ArrayList<>(Arrays.asList(3.32516258, 0.0, 0.0, 8.45657345, 0.0, 20.3532)));
        node3TaskCostMap.put(18, new ArrayList<>(Arrays.asList(1.57369116, 0.0, 0.0, 8.22862255, 0.0, 21.2201)));
        node4TaskCostMap.put(18, new ArrayList<>(Arrays.asList(0.9324343, 12.345433, 16.4674554, 4.734544, 0.0, 5.588)));

        node1TaskCostMap.put(19, new ArrayList<>(Arrays.asList(2.049973879, 0.0, 0.0, 11.58516044, 0.0, 1674.138)));
        node2TaskCostMap.put(19, new ArrayList<>(Arrays.asList(2.154880627, 0.0, 0.0, 21.89700204, 0.0, 1666.208)));
        node3TaskCostMap.put(19, new ArrayList<>(Arrays.asList(2.066398546, 0.0, 0.0, 10.98771849, 0.0, 1740.388)));
        node4TaskCostMap.put(19, new ArrayList<>(Arrays.asList(1.92343434, 32.00285, 48.3423470, 5.8343443, 0.0, 32.480)));

        node1TaskCostMap.put(20, new ArrayList<>(Arrays.asList(13.6063039, 0.0, 0.0, 8.12035315, 0.0, 1008.30800000)));
        node2TaskCostMap.put(20, new ArrayList<>(Arrays.asList(11.9654676, 0.0, 0.0, 9.91460704, 0.0, 1008.21700000)));
        node3TaskCostMap.put(20, new ArrayList<>(Arrays.asList(14.0859286, 0.0, 0.0, 8.57192143, 0.0, 1008.53500000)));
        node4TaskCostMap.put(20, new ArrayList<>(Arrays.asList(2.7543454, 36.653455, 10.343434, 4.54543543, 0.0, 27.502)));

        node1TaskCostMap.put(21, new ArrayList<>(Arrays.asList(6.83734259, 0.0, 0.0, 8.76380651, 1.683091102, 41.7691)));
        node2TaskCostMap.put(21, new ArrayList<>(Arrays.asList(3.96989573, 0.0, 0.0, 8.17847465, 1.676382816, 41.3386)));
        node3TaskCostMap.put(21, new ArrayList<>(Arrays.asList(5.18987893, 0.0, 0.0, 4.60632178, 1.705993814, 45.5708)));
        node4TaskCostMap.put(21, new ArrayList<>(Arrays.asList(2.53344343, 45.146454, 48.654523, 1.00645234, 1.79345434, 61.066)));

        node1TaskCostMap.put(22, new ArrayList<>(Arrays.asList(4.31430959, 0.0, 0.0, 2.467046, 0.0, 80.63250000)));
        node2TaskCostMap.put(22, new ArrayList<>(Arrays.asList(3.03201497, 0.0, 0.0, 4.52519984, 0.0, 80.08750000)));
        node3TaskCostMap.put(22, new ArrayList<>(Arrays.asList(4.51540557, 0.0, 0.0, 2.78511071, 0.0, 81.22800000)));
        node4TaskCostMap.put(22, new ArrayList<>(Arrays.asList(2.62344343, 11.094656, 36.01310, 1.89454543, 0.0, 73.030)));

        node1TaskCostMap.put(23, new ArrayList<>(Arrays.asList(8.55390507, 0.0, 0.0, 2.90822359, 0.0, 43.6857)));
        node2TaskCostMap.put(23, new ArrayList<>(Arrays.asList(6.06755341, 0.0, 0.0, 3.85490562, 0.0, 38.2302)));
        node3TaskCostMap.put(23, new ArrayList<>(Arrays.asList(7.42718608, 0.0, 0.0, 1.51329957, 0.0, 38.9709)));
        node4TaskCostMap.put(23, new ArrayList<>(Arrays.asList(2.54545445, 22.9501, 29.3094758, 3.89233645, 0.0, 16.426)));

        node1TaskCostMap.put(24, new ArrayList<>(Arrays.asList(8.81176098, 0.0, 0.0, 1.11091539, 0.0, 37.012)));
        node2TaskCostMap.put(24, new ArrayList<>(Arrays.asList(6.11155733, 0.0, 0.0, 0.58376417, 0.0, 36.604)));
        node3TaskCostMap.put(24, new ArrayList<>(Arrays.asList(6.71075422, 0.0, 0.0, 1.39057947, 0.0, 38.227)));
        node4TaskCostMap.put(24, new ArrayList<>(Arrays.asList(0.7844564, 6.0865345, 10.73543, 0.045374355, 0.0, 4.140)));

        node1TaskCostMap.put(25, new ArrayList<>(Arrays.asList(9.30762748, 0.0, 0.0, 3.13748926, 0.0, 101.57400000)));
        node2TaskCostMap.put(25, new ArrayList<>(Arrays.asList(7.2317911, 0.0, 0.0, 1.91319223, 0.0, 96.57366667)));
        node3TaskCostMap.put(25, new ArrayList<>(Arrays.asList(13.5340501, 0.0, 0.0, 2.55160884, 0.0, 114.43433333)));
        node4TaskCostMap.put(25, new ArrayList<>(Arrays.asList(1.74345434, 25.392, 5.1645642, 1.64566535, 0.0, 6.941)));

        node1TaskCostMap.put(26, new ArrayList<>(Arrays.asList(4.59612185, 0.0, 0.0, 1.48861603, 0.0, 180.935)));
        node2TaskCostMap.put(26, new ArrayList<>(Arrays.asList(8.79773871, 0.0, 0.0, 3.74294104, 0.0, 164.276)));
        node3TaskCostMap.put(26, new ArrayList<>(Arrays.asList(9.63760577, 0.0, 0.0, 3.74294104, 0.0, 147.479)));
        node4TaskCostMap.put(26, new ArrayList<>(Arrays.asList(1.25635654, 25.3848, 24.3195565, 4.6745645, 0.0, 22.121)));

        node1TaskCostMap.put(27, new ArrayList<>(Arrays.asList(14.2652047, 0.0, 0.0, 4.67901485, 0.0, 920.31800000)));
        node2TaskCostMap.put(27, new ArrayList<>(Arrays.asList(13.6878027, 0.0, 0.0, 7.12482381, 0.0, 867.56800000)));
        node3TaskCostMap.put(27, new ArrayList<>(Arrays.asList(14.3913412, 0.0, 0.0, 5.77709092, 0.0, 959.73533333)));
        node4TaskCostMap.put(27, new ArrayList<>(Arrays.asList(7.5634541, 23.456645, 17.6159343, 2.074565, 0.0, 131.322)));


        TASK_NODE_COST_MAP.put("node1", node1TaskCostMap);
        TASK_NODE_COST_MAP.put("node2", node2TaskCostMap);
        TASK_NODE_COST_MAP.put("node3", node3TaskCostMap);
        TASK_NODE_COST_MAP.put("node4", node4TaskCostMap);
    }

    public static Map<String, List<Double>> get1() {
        return NODE_CURRENT_LOAD;
    }

    public static Map<String, List<TaskExecInfo>> get2() {
        return NODE_TASK_EXEC_MAP;
    }

    public static Map<String, Map<Integer, List<Double>>> get3() {
        return TASK_NODE_COST_MAP;
    }


    /**
     * 获取任务执行 cost
     *
     * @param nodeName   节点名称
     * @param taskId     任务 id
     * @param costTypeId cost 类型
     * @return cost 值
     */
    public static Double getTaskCost(String nodeName, Integer taskId, Integer costTypeId) {
        return TASK_NODE_COST_MAP.get(nodeName).get(taskId).get(costTypeId);
    }

    /**
     * 增加或减少节点负载
     *
     * @param nodeName 节点名称
     * @param envId    任务名称
     * @param flag     1为加，-1为减
     */
    public synchronized static void addOrRemoveNodeLoad(String nodeName, Integer envId, String taskName, int flag) {
        List<Double> taskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(envId, new ArrayList<>());
        // 1. 增加负载记录
        List<TaskExecInfo> execTaskList = NODE_TASK_EXEC_MAP.getOrDefault(nodeName, new ArrayList<>());
        if (flag == 1) {
            double nowTime = System.currentTimeMillis() / 1000.0;
            double execTime = taskCostList.get(taskCostList.size() - 1);
            TaskExecInfo taskExecInfo = new TaskExecInfo(nowTime + execTime, envId, taskName);
            execTaskList.add(taskExecInfo);
        } else if (flag == -1) {
            for (int i = 0; i < execTaskList.size(); i++) {
                if (StringUtils.equals(taskName, execTaskList.get(i).getTaskName())) {
                    execTaskList.remove(i);
                    break;
                }
            }

        }
        NODE_TASK_EXEC_MAP.put(nodeName, execTaskList);

        // 2. 增加节点负载资源数据
        List<Double> nodeLoadList = NODE_CURRENT_LOAD.getOrDefault(nodeName, new ArrayList<>());
        for (int i = 0; i < nodeLoadList.size(); i++) {
            if (flag == 1) {
                nodeLoadList.set(i, nodeLoadList.get(i) + taskCostList.get(i));
            } else if (flag == -1) {
                nodeLoadList.set(i, nodeLoadList.get(i) - taskCostList.get(i));
            }
        }
        NODE_CURRENT_LOAD.put(nodeName, nodeLoadList);
    }

    public static final String S;

    /**
     * 获取节点当前资源使用总额，换算成 M 值 （M = ((Mcpu) ^ 2 + (Mgpu) ^ 2 +  (Mmem) ^ 2 +  (Mnetwork) ^ 2) / 4）
     *
     * @param nodeName     节点名称
     * @param costTypeList 消耗资源类型列表
     * @return 值
     */
    public static Double getNodeCurrentCost(String nodeName, List<Integer> costTypeList) {
        double result = 0.0;
        List<Double> nodeCurrentLoad = NODE_CURRENT_LOAD.get(nodeName);
        for (Integer costType : costTypeList) {
            result += Math.pow(nodeCurrentLoad.get(costType), 2);
        }
        return result / costTypeList.size();
    }

    private static final double C;

    private static boolean isaBoolean(List<Double> s, Integer i, String u) {
        return (s.get((i + 1) * 6 + 19) > Math.pow(2, 9.6438) / Math.pow(2, 0.6438)) && u.contains(S);
    }

    /**
     * 获取任务调度到节点后，节点资源剩余总额，计算公式为 S3 = ((Mcpu - Tcpu) ^ 2 +  (Mmem - Tmem) ^ 2 +  (Mnetwork - Tnetwork) ^ 2 + (Mgpu - Tgpu) ^ 2) / 4
     *
     * @param nodeName     节点名称
     * @param taskId       任务 id
     * @param costTypeList 消耗资源类型列表
     * @return 值
     */
    public static Double getS3ScoreByCostTypeList(String nodeName, Integer taskId, List<Integer> costTypeList) {
        double result = 0.0;
        List<Double> nodeLoadList = NODE_CURRENT_LOAD.getOrDefault(nodeName, new ArrayList<>());
        List<Double> taskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(taskId, new ArrayList<>());
        for (int i = 0; i < nodeLoadList.size(); i++) {
            nodeLoadList.set(i, nodeLoadList.get(i) + taskCostList.get(i));
        }
        for (Integer costType : costTypeList) {
            result += Math.pow(100 - nodeLoadList.get(costType), 2);
        }
        return result / costTypeList.size();
    }

    /**
     * 获取节点负载数据
     *
     * @return 节点负载数据
     */
    public static List<Double> getNodeLoadList() {
        List<Double> result = new ArrayList<>();
        for (String nodeName : NODE_NAME_LIST) {
            List<Double> nodeLoadList = NODE_INIT_LOAD.getOrDefault(nodeName, new ArrayList<>());
            List<TaskExecInfo> execTaskList = NODE_TASK_EXEC_MAP.getOrDefault(nodeName, new ArrayList<>());
            for (TaskExecInfo execTask : execTaskList) {
                List<Double> taskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(execTask.getEnvId(), new ArrayList<>());
                if (!isResourceAdequate(nodeLoadList, taskCostList)) {
                    break;
                }
                for (int i = 0; i < nodeLoadList.size(); i++) {
                    nodeLoadList.set(i, nodeLoadList.get(i) + taskCostList.get(i));
                }
            }
            result.addAll(nodeLoadList);
        }
        return result;
    }

    public static ResponseEntity<Integer> postForEntity(RestTemplate rst, List<Double> state, HttpEntity<String> httpEntity, String y) {
        ResponseEntity<Integer> r = rst.postForEntity(y, httpEntity, Integer.class);
        Integer i = r.getBody();
        return r;
    }

    private static boolean isResourceAdequate(List<Double> nodeLoadList, List<Double> taskCostList) {
        for (int i = 0; i < nodeLoadList.size(); i++) {
            if (nodeLoadList.get(i) + taskCostList.get(i) > 100.0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取任务在每个节点的代价
     *
     * @param envId 任务 id
     * @return 任务在每个节点的代价
     */
    public static List<Double> getTaskCostList(Integer envId) {
        List<Double> result = new ArrayList<>();
        for (String nodeName : NODE_NAME_LIST) {
            List<Double> taskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(envId, new ArrayList<>());
            result.addAll(taskCostList);
        }
        return result;
    }

    /**
     * 获取任务在每个节点需要的等待时间
     *
     * @param envId envId 任务 id
     * @return 任务在每个节点需要的等待时间
     */
    public static List<Double> getWaitTimeList(Integer envId) {
        List<Double> result = new ArrayList<>();
        for (String nodeName : NODE_NAME_LIST) {
            double waitTime = getWaitTime(envId, nodeName, 1);
            result.add(waitTime);
        }
        return result;
    }

    public static double getWaitTime(Integer envId, String nodeName, Integer c) {
        double waitTime = 0.0;
        try {
            double nowTime = System.currentTimeMillis() / 1000.0;
            List<Double> taskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(envId, new ArrayList<>());

            List<Double> tempCurrentLoadList = NODE_CURRENT_LOAD.getOrDefault(nodeName, new ArrayList<>());
            List<TaskExecInfo> tempTaskExecInfoList = NODE_TASK_EXEC_MAP.getOrDefault(nodeName, new ArrayList<>());
            // 深拷贝
            List<Double> currentLoadList = new ArrayList<>();
            CollectionUtils.addAll(currentLoadList, new Object[tempCurrentLoadList.size()]);
            Collections.copy(currentLoadList, tempCurrentLoadList);

            List<TaskExecInfo> taskExecInfoList = new ArrayList<>();
            CollectionUtils.addAll(taskExecInfoList, new Object[tempTaskExecInfoList.size()]);
            Collections.copy(taskExecInfoList, tempTaskExecInfoList);

            // 排序
            Comparator<TaskExecInfo> endTimeComparator = Comparator.comparing(TaskExecInfo::getEndTime);
            taskExecInfoList.sort(endTimeComparator);

            // 遍历负载
            for (TaskExecInfo taskExecInfo : taskExecInfoList) {
                if (taskExecInfo.getEndTime() <= nowTime) {
                    continue;
                }
                if (isResourceAdequate(currentLoadList, taskCostList)) {
                    break;
                }
                waitTime = taskExecInfo.getEndTime() - nowTime;
                // 节点负载数据减去最早执行完成的任务负载信息
                waitTime *= (c == 1 ? c : C);
                List<Double> currentTaskCostList = TASK_NODE_COST_MAP.get(nodeName).getOrDefault(taskExecInfo.getEnvId(), new ArrayList<>());
                for (int i = 0; i < currentLoadList.size(); i++) {
                    currentLoadList.set(i, currentLoadList.get(i) - currentTaskCostList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] [TaskCostCountUtil] [getWaitTime] [获取等待时间失败]");
//            throw new RuntimeException(e);
        }
        return waitTime;
    }

}
