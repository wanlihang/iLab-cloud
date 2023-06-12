package com.ilab.service.model;

import cn.hutool.core.util.IdUtil;
import com.ilab.service.constant.PodInfoConstant;
import com.ilab.service.constant.SplitConstant;
import com.ilab.service.domain.CourseLabEnv;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import lombok.Data;


/**
 * 任务信息
 *
 * @author wanlh
 */
@Data
public class TaskInfo {

    public TaskInfo() {}

    public TaskInfo(CourseLabEnv courseLabEnv) {
        BeanUtils.copyProperties(courseLabEnv, this);
        this.setTaskName(PodInfoConstant.DEFAULT_POD_NAME + courseLabEnv.getEnvId() + SplitConstant.HYPHEN + IdUtil.fastSimpleUUID());
    }

    /**
     * 任务对应的负载类型 id
     */
    private Integer envId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 容器 tag
     */
    private String dockerImageTag;

    /**
     * 任务执行脚本
     */
    private String taskExecScript;

    /**
     * 是否需要 GPU
     */
    private String requireGpu;

    /**
     * 是否需要 CPU
     */
    private String requireCpu;

    /**
     * 是否需要 Memory
     */
    private String requireMemory;

    /**
     * 是否需要 Network
     */
    private String requireNetwork;

    private String schedulerType;
}
