package com.ilab.service.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 course_attach
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public class CourseAttach extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long courseId;

    /** 附件名 */
    @Excel(name = "附件名")
    private String name;

    /** 路径 */
    @Excel(name = "路径")
    private String path;

    /** 存储磁盘 */
    @Excel(name = "存储磁盘")
    private String disk;

    /** 单位：byte */
    @Excel(name = "单位：byte")
    private Long size;

    /** 文件格式 */
    @Excel(name = "文件格式")
    private String extension;

    /** 只有购买者可以下载,1是,0否 */
    @Excel(name = "只有购买者可以下载,1是,0否")
    private Integer onlyBuyer;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloadTimes;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updatedAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date deletedAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
    public void setDisk(String disk)
    {
        this.disk = disk;
    }

    public String getDisk()
    {
        return disk;
    }
    public void setSize(Long size)
    {
        this.size = size;
    }

    public Long getSize()
    {
        return size;
    }
    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    public String getExtension()
    {
        return extension;
    }
    public void setOnlyBuyer(Integer onlyBuyer)
    {
        this.onlyBuyer = onlyBuyer;
    }

    public Integer getOnlyBuyer()
    {
        return onlyBuyer;
    }
    public void setDownloadTimes(Long downloadTimes)
    {
        this.downloadTimes = downloadTimes;
    }

    public Long getDownloadTimes()
    {
        return downloadTimes;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("name", getName())
            .append("path", getPath())
            .append("disk", getDisk())
            .append("size", getSize())
            .append("extension", getExtension())
            .append("onlyBuyer", getOnlyBuyer())
            .append("downloadTimes", getDownloadTimes())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("deletedAt", getDeletedAt())
            .toString();
    }
}
