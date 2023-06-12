package com.ilab.service.model;

import lombok.Data;

/**
 * @author wanlh
 */
@Data
public class PrometheusResultInfo {


    /**
     * prometheus指标属性
     */
    private PrometheusMetricInfo metric;

    /**
     * prometheus指标值
     */
    private String[] value;

}
