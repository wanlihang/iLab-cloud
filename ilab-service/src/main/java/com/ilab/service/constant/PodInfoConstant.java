package com.ilab.service.constant;

import lombok.Data;

@Data
public class PodInfoConstant {
    /**
     * 默认容器名称
     */
    public static final String DEFAULT_POD_NAME = "pod";

    /**
     * 默认命名空间
     */
    public static final String DEFAULT_NAMESPACE = "ilab";

    /**
     * 默认端口
     */
    public static final Integer DEFAULT_CONTAINER_PORT = 12345;

    /**
     * 默认对外暴露端口
     */
    public static final Integer DEFAULT_HOST_PORT = 12345;


}
