package com.ilab.service.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 【请填写功能名称】对象 course_lab_task_records
 *
 * @author ruoyi
 * @date 2022-11-16
 */
@Data
public class CourseLabTaskRecords extends BaseEntity
{
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** $column.columnComment */
    private Date gmtCreate;

    /** $column.columnComment */
    private Date gmtModified;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer labEnvId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String taskName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String startTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String endTime;

    /**
     * 分配的 node 名称
     */
    private String assignNode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long continueTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isDeleted;

    private String schedulerType;

    private String schedulerEndTime;

    private Long waitTime;

    private Long scheduleTime;

    private Long execTime;

    private String submissionTime;
}
