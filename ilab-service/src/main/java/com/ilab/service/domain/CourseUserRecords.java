package com.ilab.service.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 course_user_records
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public class CourseUserRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long courseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updatedAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date deletedAt;

    /** 看完,0否,1是 */
    @Excel(name = "看完,0否,1是")
    private Integer isWatched;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date watchedAt;

    /** 进度 */
    @Excel(name = "进度")
    private Integer progress;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }
    public void setDeletedAt(Date deletedAt)
    {
        this.deletedAt = deletedAt;
    }

    public Date getDeletedAt()
    {
        return deletedAt;
    }
    public void setIsWatched(Integer isWatched)
    {
        this.isWatched = isWatched;
    }

    public Integer getIsWatched()
    {
        return isWatched;
    }
    public void setWatchedAt(Date watchedAt)
    {
        this.watchedAt = watchedAt;
    }

    public Date getWatchedAt()
    {
        return watchedAt;
    }
    public void setProgress(Integer progress)
    {
        this.progress = progress;
    }

    public Integer getProgress()
    {
        return progress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("userId", getUserId())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("deletedAt", getDeletedAt())
            .append("isWatched", getIsWatched())
            .append("watchedAt", getWatchedAt())
            .append("progress", getProgress())
            .toString();
    }
}
