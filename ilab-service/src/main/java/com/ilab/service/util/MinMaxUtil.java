package com.ilab.service.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wanlh
 */
public class MinMaxUtil {

    /**
     * atan 函数标准化
     *
     * @param scoreMap 原数据组
     * @param multiple  倍数
     * @return 归一化输出数组
     */
    public static Map<String, Double> minMaxTransform(Map<String, Double> scoreMap, Double multiple) {
        Map<String, Double> result = new HashMap<>(scoreMap.size());
        double sum = scoreMap.values()
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();

        for (String key : scoreMap.keySet()) {
            double score = scoreMap.get(key) / sum;
            result.put(key, score * multiple);
        }
        return result;
    }

}
