package com.ilab.service.enums;

/**
 * 数据库通用值枚举类
 *
 * @author wanlh
 */

public enum DataSourceEnum {

    /**
     * 逻辑删除标志位 y
     */
    IS_DELETED("逻辑删除标志位", "y"),

    /**
     * 逻辑删除标志位 n
     */
    IS_NOT_DELETED("逻辑删除标志位", "n"),

    /**
     * 使用 gpu 标志位 y
     */
    REQUIRE_GPU("使用 gpu 标志位", "y"),

    /**
     * 使用 gpu 标志位 n
     */
    NOT_REQUIRE_GPU("使用 gpu 标志位", "n"),
    ;

    private final String label;
    private final String value;

    DataSourceEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

}
