package com.ilab.service.model;

import io.fabric8.kubernetes.api.model.Quantity;
import lombok.Data;

import java.util.Map;

@Data
public class PodInfo {
    /**
     * pod名称
     */
    private String podName;

    /**
     * 节点选择器
     */
    private Map<String, String> nodeSelectorMap;

    /**
     * 容器名称
     */
    private String containerName;


    /**
     * 镜像
     */
    private String image;

    /**
     * 容器端口
     */
    private Integer containerPort;

    /**
     * 向外暴露端口
     */
    private Integer hostPort;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 容器注解
     */
    private Map<String, String> annotations;

    /**
     * 容器资源需求
     */
    private Map<String, Quantity> resourcesRequests;

    /**
     * 容器资源限制
     */
    private Map<String, Quantity> resourceLimits;

}
