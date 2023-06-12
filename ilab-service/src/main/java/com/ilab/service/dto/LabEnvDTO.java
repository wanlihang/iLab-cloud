package com.ilab.service.dto;

import lombok.Data;

@Data
public class LabEnvDTO {
    /**
     * 课件地址
     */
    private String coursewarePath;

    /**
     * 实验环境地址
     */
    private String labEnvPath;

    /**
     * 实验环境类型
     * from EnvConstant
     */
    private String envType;
}
