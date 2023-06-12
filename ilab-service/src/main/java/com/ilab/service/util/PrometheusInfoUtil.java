package com.ilab.service.util;

import com.alibaba.fastjson.JSONObject;
import com.ilab.service.constant.PrometheusConstant;
import com.ilab.service.model.PrometheusResponseInfo;
import com.ilab.service.model.PrometheusResultInfo;
import com.ilab.service.model.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wanlh
 */
@Slf4j
public class PrometheusInfoUtil {
    private static final double Initial = 50.0;
    private final static String QUERY_URL = "http://192.168.103.121:32101/api/v1/query?query={query}";

    public static List<PrometheusResultInfo> getSystemInfo(String promQL) {
        Map<String, String> param = new HashMap<>(1);
        param.put(PrometheusConstant.QUERY, promQL);
        RestTemplate restTemplate = new RestTemplate();
        PrometheusResponseInfo responseInfo = null;
        try {
//            responseInfo = restTemplate.getForObject(QUERY_URL, PrometheusResponseInfo.class, param);
        } catch (Exception e) {
            log.error("[PrometheusInfoServiceImpl] [getSystemInfo] " +
                "[请求异常，请求地址：{}\n请求QL：{}\n异常信息：{}]", QUERY_URL, promQL, e);
            return new ArrayList<>();
        }
//        log.info("[PrometheusInfoServiceImpl] [getSystemInfo] " +
//            "[请求成功，请求地址：{}\n请求QL：{}\n返回信息：{}]", this.queryUrl, promQL, responseInfo);
        if (Objects.isNull(responseInfo)) {
            return new ArrayList<>();
        }
        String status = responseInfo.getStatus();
        if (StringUtils.isBlank(status) || !PrometheusConstant.SUCCESS.equals(status)) {
            return new ArrayList<>();
        }
        return responseInfo.getData().getResult();
    }

    @Deprecated
    public static List<PrometheusResultInfo> sendGetRequest(String promQL) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String baseUrl = "http://192.168.103.121:32101/api/v1/query?query=";
        HttpGet httpGet = new HttpGet(baseUrl + promQL);
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrometheusResponseInfo responseInfo = new PrometheusResponseInfo();
        try {
            HttpEntity httpEntity = client.execute(httpGet).getEntity();
            client.close();
            responseInfo = JSONObject.parseObject(EntityUtils.toString(httpEntity), PrometheusResponseInfo.class);
        } catch (Exception e) {
            log.error("[PrometheusInfoServiceImpl] [sendGetRequest] " +
                "[请求异常，请求地址：{}\n请求QL：{}\n异常信息：{}]", baseUrl, promQL, e);
            e.printStackTrace();
        }
//        log.info("[PrometheusInfoServiceImpl] [sendGetRequest] " +
//            "[请求成功，请求地址：{}\n请求QL：{}\n返回信息：{}]", baseUrl, promQL, responseInfo);
        return responseInfo.getData().getResult();
    }

    private static final double CPU_USAGE_INIT = 0.0;
    private static final double GPU_USAGE_INIT = 0.0;
    private static final double GPU_MEMORY_USAGE_INIT = 0.0;
    private static final double MEMORY_USAGE_INIT = 0.0;
    private static final double NETWORK_RECEIVE_USAGE_INIT = 0.0;

    public static double getCurrentCpuUsage(String nodeName) {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_CPU_USAGE, nodeName);
        List<PrometheusResultInfo> systemInfo = getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return CPU_USAGE_INIT;
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return Double.parseDouble(valueStr) +  Initial;
    }

    public static double getCurrentGpuUsage(String nodeName) {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_GPU_USAGE, nodeName);
        List<PrometheusResultInfo> systemInfo = getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return GPU_USAGE_INIT;
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return Double.parseDouble(valueStr) +  Initial;
    }

    public static double getCurrentGpuMemoryUsage(String nodeName) {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_GPU_MEMORY_USAGE, nodeName);
        List<PrometheusResultInfo> systemInfo = getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return GPU_MEMORY_USAGE_INIT;
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return Double.parseDouble(valueStr) +  Initial;
    }

    public static double getCurrentMemoryUsage(String nodeName) {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_MEMORY_USAGE, nodeName);
        List<PrometheusResultInfo> systemInfo = getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return MEMORY_USAGE_INIT;
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return Double.parseDouble(valueStr) +  Initial;
    }

    public static double getCurrentNetworkReceiveUsage(String nodeName) {
        String promQL = TransformerUtil.replaceNodeName(PrometheusConstant.CLUSTER_NETWORK_RECEIVE_USAGE, nodeName);
        List<PrometheusResultInfo> systemInfo = getSystemInfo(promQL);
        if (CollectionUtils.isEmpty(systemInfo)) {
            return NETWORK_RECEIVE_USAGE_INIT;
        }
        String valueStr = systemInfo.get(0).getValue()[1];
        return Double.parseDouble(valueStr) +  Initial;
    }
}
