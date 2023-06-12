package com.ilab.service.model;

import lombok.Data;

@Data
public class PrometheusResponseInfo {

    /**
     * 状态
     * 成功-- success
     */
    private String status;

    /**
     * prometheus指标属性和值
     */
    private PrometheusDataInfo data;


}
