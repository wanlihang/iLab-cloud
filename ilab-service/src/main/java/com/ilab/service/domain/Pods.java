package com.ilab.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 pods
 *
 * @author ruoyi
 * @date 2023-03-29
 */
public class Pods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String podName;

    /** r为运行中；s为已停止；d为已删除 */
    @Excel(name = "r为运行中；s为已停止；d为已删除")
    private String podStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String podNode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String podNamespace;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String podLabels;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String podAnnotations;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long courseChapterId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer userId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPodName(String podName)
    {
        this.podName = podName;
    }

    public String getPodName()
    {
        return podName;
    }
    public void setPodStatus(String podStatus)
    {
        this.podStatus = podStatus;
    }

    public String getPodStatus()
    {
        return podStatus;
    }
    public void setPodNode(String podNode)
    {
        this.podNode = podNode;
    }

    public String getPodNode()
    {
        return podNode;
    }
    public void setPodNamespace(String podNamespace)
    {
        this.podNamespace = podNamespace;
    }

    public String getPodNamespace()
    {
        return podNamespace;
    }
    public void setPodLabels(String podLabels)
    {
        this.podLabels = podLabels;
    }

    public String getPodLabels()
    {
        return podLabels;
    }
    public void setPodAnnotations(String podAnnotations)
    {
        this.podAnnotations = podAnnotations;
    }

    public String getPodAnnotations()
    {
        return podAnnotations;
    }
    public void setCourseChapterId(Long courseChapterId)
    {
        this.courseChapterId = courseChapterId;
    }

    public Long getCourseChapterId()
    {
        return courseChapterId;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("podName", getPodName())
            .append("podStatus", getPodStatus())
            .append("podNode", getPodNode())
            .append("podNamespace", getPodNamespace())
            .append("podLabels", getPodLabels())
            .append("podAnnotations", getPodAnnotations())
            .append("courseChapterId", getCourseChapterId())
            .append("userId", getUserId())
            .toString();
    }
}
