package com.ilab.service.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 course_lab
 *
 * @author ruoyi
 * @date 2023-03-30
 */
@Data
public class CourseLab extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer labId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date gmtCreate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date gmtModified;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer chapterId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String coursewarePath;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer labEnvId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String labEnvPath;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String envType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isDeleted;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setLabId(Integer labId)
    {
        this.labId = labId;
    }

    public Integer getLabId()
    {
        return labId;
    }
    public void setGmtCreate(Date gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate()
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified()
    {
        return gmtModified;
    }
    public void setChapterId(Integer chapterId)
    {
        this.chapterId = chapterId;
    }

    public Integer getChapterId()
    {
        return chapterId;
    }
    public void setCoursewarePath(String coursewarePath)
    {
        this.coursewarePath = coursewarePath;
    }

    public String getCoursewarePath()
    {
        return coursewarePath;
    }
    public void setLabEnvId(Integer labEnvId)
    {
        this.labEnvId = labEnvId;
    }

    public Integer getLabEnvId()
    {
        return labEnvId;
    }
    public void setLabEnvPath(String labEnvPath)
    {
        this.labEnvPath = labEnvPath;
    }

    public String getLabEnvPath()
    {
        return labEnvPath;
    }
    public void setEnvType(String envType)
    {
        this.envType = envType;
    }

    public String getEnvType()
    {
        return envType;
    }
    public void setIsDeleted(String isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted()
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("labId", getLabId())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("chapterId", getChapterId())
            .append("coursewarePath", getCoursewarePath())
            .append("labEnvId", getLabEnvId())
            .append("labEnvPath", getLabEnvPath())
            .append("envType", getEnvType())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
