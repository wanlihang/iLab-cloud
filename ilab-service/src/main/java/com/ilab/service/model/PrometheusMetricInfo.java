package com.ilab.service.model;

import lombok.Data;

@Data
public class PrometheusMetricInfo {

    /**
     * prometheus指标名称
     */
    private String __name__;

    /**
     * prometheus实例名称
     */
    private String instance;

    /**
     * prometheus任务名称
     */
    private String job;

}
