package com.ilab.service.paperTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ilab.service.algorithm.D2SACAlgorithm;
import com.ilab.service.model.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author TUF
 */
@Slf4j
public class JustInTimeMain {

    public static void main(String[] args) {
        try {
//            testTaskCostCountUtil();
            generateCsvFiles();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testTaskCostCountUtil() {
//        log.info("TaskCostCountUtil.get1(): {}", TaskCostCountUtil.get1());
//        log.info("TaskCostCountUtil.get2(): {}", TaskCostCountUtil.get2());
//        log.info("TaskCostCountUtil.get3(): {}", TaskCostCountUtil.get3());
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 4, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 4, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 4, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        TaskCostCountUtil.addOrRemoveNodeLoad("node1", 13, "sada12132", 1);
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("TaskCostCountUtil.getWaitTimeList(): {}", TaskCostCountUtil.getWaitTimeList(13));
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setEnvId(13);

        log.info("DERLAlgorithm.getNodeInfo(): {}", D2SACAlgorithm.getNodeInfo(taskInfo));
    }

    public static void generateCsvFiles() throws Exception {
        String lab = "18";
        String startTime = "1678879387.914000";
        String endTime = "1678879393.581000";

        String cpuUrl = "http://192.168.103.121:32101/api/v1/query_range?query=100*(1-sum%20by(instance)(increase(node_cpu_seconds_total%7Bmode%3D%22idle%22%7D%5B1m%5D))%2Fsum%20by(instance)(increase(node_cpu_seconds_total%5B1m%5D)))&start=" + startTime + "&end=" + endTime + "&step=1";
        String gpuUrl = "http://192.168.103.121:32101/api/v1/query_range?query=sum+by%28node%29%28increase%28container_gpu_utilization%7Bgpu%3D%22gpu0%22%2Cnode%3D%22node4%22%7D%5B1m%5D%29%29&start=" + startTime + "&end=" + endTime + "&step=6";
        String gpuMemUrl = "http://192.168.103.121:32101/api/v1/query_range?query=sum+by%28node%29%28container_gpu_memory_total%29%2Fsum+by%28node%29%28container_request_gpu_memory%29&start=" + startTime + "&end=" + endTime + "&step=14";
        String memoryUrl = "http://192.168.103.121:32101/api/v1/query_range?query=100*(node_memory_MemTotal_bytes-(node_memory_MemFree_bytes%2Bnode_memory_Cached_bytes%2Bnode_memory_Buffers_bytes))%2Fnode_memory_MemTotal_bytes&start=" + startTime + "&end=" + endTime + "&step=1";
        String networkUrl = "http://192.168.103.121:32101/api/v1/query_range?query=(sum%20by(instance)(rate(node_network_receive_bytes_total%5B1m%5D))*8)%2F(1024*1024)&start=" + startTime + "&end=" + endTime + "&step=1";

        CloseableHttpClient client = HttpClientBuilder.create().build();

        // cpu
        HttpGet httpGet1 = new HttpGet(cpuUrl);
        httpGet1.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONArray cpuJsonArray = JSONObject.parseObject(EntityUtils.toString(client.execute(httpGet1).getEntity())).getJSONObject("data").getJSONArray("result");

        // gpu
        HttpGet httpGet2 = new HttpGet(gpuUrl);
        httpGet2.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONArray gpuJsonArray = JSONObject.parseObject(EntityUtils.toString(client.execute(httpGet2).getEntity())).getJSONObject("data").getJSONArray("result");

        // gpuMem
        HttpGet httpGet3 = new HttpGet(gpuMemUrl);
        httpGet3.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONArray gpuMemJsonArray = JSONObject.parseObject(EntityUtils.toString(client.execute(httpGet3).getEntity())).getJSONObject("data").getJSONArray("result");

        // memory
        HttpGet httpGet4 = new HttpGet(memoryUrl);
        httpGet4.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONArray memoryJsonArray = JSONObject.parseObject(EntityUtils.toString(client.execute(httpGet4).getEntity())).getJSONObject("data").getJSONArray("result");

        // network
        HttpGet httpGet5 = new HttpGet(networkUrl);
        httpGet5.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONArray networkJsonArray = JSONObject.parseObject(EntityUtils.toString(client.execute(httpGet5).getEntity())).getJSONObject("data").getJSONArray("result");

        client.close();

        HashMap<String, ArrayList<Double>> cpu = getHash(cpuJsonArray, "instance");
        HashMap<String, ArrayList<Double>> gpu = getHash(gpuJsonArray, "node");
        HashMap<String, ArrayList<Double>> gpuMem = getHash(gpuMemJsonArray, "node");
        HashMap<String, ArrayList<Double>> mem = getHash(memoryJsonArray, "instance");
        HashMap<String, ArrayList<Double>> net = getHash(networkJsonArray, "instance");


        writeFile(cpu, "C:\\Users\\TUF\\Desktop\\新建文件夹\\" + lab + "\\" + lab + "_cpu.csv");
        writeFile(gpu, "C:\\Users\\TUF\\Desktop\\新建文件夹\\" + lab + "\\" + lab + "_gpu.csv");
        writeFile(gpuMem, "C:\\Users\\TUF\\Desktop\\新建文件夹\\" + lab + "\\" + lab + "_gpuMem.csv");
        writeFile(mem, "C:\\Users\\TUF\\Desktop\\新建文件夹\\" + lab + "\\" + lab + "_mem.csv");
        writeFile(net, "C:\\Users\\TUF\\Desktop\\新建文件夹\\" + lab + "\\" + lab + "_net.csv");
    }

    public static void writeFile(HashMap<String, ArrayList<Double>> resource, String path) throws Exception {
        StringBuilder builder;
        builder = new StringBuilder();
//        builder.append("master").append(",")
//                .append("node1").append(",")
//                .append("node2").append(",")
//                .append("node3").append(",")
//                .append("\n");
        builder.append("node4").append(",")
            .append("\n");
        if(!resource.isEmpty()){
            for (int i = 0; i < resource.get("node4").size(); i++) {
//            Double master = resource.get("master").get(i);
//            Double node1 = resource.get("node1").get(i);
//            Double node2 = resource.get("node2").get(i);
//            Double node3 = resource.get("node3").get(i);
                Double node4 = resource.get("node4").get(i);
//            builder.append(master).append(",")
//                    .append(node1).append(",")
//                    .append(node2).append(",")
//                    .append(node3).append(",")
//                    .append("\n");
                builder.append(node4).append(",")
                    .append("\n");
            }
        }
        File newfile = new File(path);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile), "UTF-8"));
        bufferedWriter.write(builder.toString());
        bufferedWriter.close();
    }

    public static HashMap<String, ArrayList<Double>> getHash(JSONArray jsonArr, String machine_attr) throws IOException {
        HashMap<String, ArrayList<Double>> cpuHashMap = new HashMap<>();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String k = jsonObject.getJSONObject("metric").getString(machine_attr);
            ArrayList<Double> v = new ArrayList<>();
            JSONArray values = jsonObject.getJSONArray("values");
            for (int j = 0; j < values.size(); j++) {
                JSONArray jsonArray = values.getJSONArray(j);
                Double aDouble = jsonArray.getDouble(1);
                v.add(aDouble);
            }
            cpuHashMap.put(k, v);
        }
        return cpuHashMap;
    }

    public static void outputRes(JSONObject ocrResult) {
        ArrayList<String> list = new ArrayList<>();
        JSONArray res = ocrResult.getJSONArray("res");
        for (int i = 0; i < res.size(); i++) {
            JSONObject object = res.getObject(i, JSONObject.class);
            list.add(object.getString("text"));
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    private static void getAttributeValue(JSONArray jsonArray) {
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        for (String key : jsonObject.keySet()) {
            JSONArray jsonArray1 = jsonObject.getJSONArray(key);
            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
            JSONObject o = new JSONObject();
            o.put(key, jsonObject1.getString("text"));
            System.out.println(o);
        }
    }
}
