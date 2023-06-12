package com.ilab.service.util;

import lombok.Data;

@Data
class TaskExecInfo {
    public TaskExecInfo() {
    }

    public TaskExecInfo(Double endTime, Integer envId, String taskName) {
        this.endTime = endTime;
        this.envId = envId;
        this.taskName = taskName;
    }

    /**
     * 任务结束时间
     */
    private Double endTime;

    /**
     * 任务对应的负载类型 id
     */
    private Integer envId;

    /**
     * 任务名称
     */
    private String taskName;

}
