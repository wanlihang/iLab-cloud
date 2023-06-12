package com.ilab.service.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 courses
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public class Courses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 名 */
    @Excel(name = "名")
    private String title;

    /** slug */
    @Excel(name = "slug")
    private String slug;

    /** 封面 */
    @Excel(name = "封面")
    private String thumb;

    /** 收费 */
    @Excel(name = "收费")
    private Long charge;

    /** 简短介绍 */
    @Excel(name = "简短介绍")
    private String shortDescription;

    /** 简介原始内容 */
    @Excel(name = "简介原始内容")
    private String originalDesc;

    /** 简介渲染后的内容 */
    @Excel(name = "简介渲染后的内容")
    private String renderDesc;

    /** SEO关键字 */
    @Excel(name = "SEO关键字")
    private String seoKeywords;

    /** SEO描述 */
    @Excel(name = "SEO描述")
    private String seoDescription;

    /** 上线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishedAt;

    /** 1显示,-1隐藏 */
    @Excel(name = "1显示,-1隐藏")
    private Integer isShow;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date updatedAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date deletedAt;

    /** 分类id */
    @Excel(name = "分类id")
    private Long categoryId;

    /** 推荐,0否,1是 */
    @Excel(name = "推荐,0否,1是")
    private Integer isRec;

    /** 学习人数 */
    @Excel(name = "学习人数")
    private Long userCount;

    /** 免费,0否,1是 */
    @Excel(name = "免费,0否,1是")
    private Integer isFree;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getSlug()
    {
        return slug;
    }
    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }

    public String getThumb()
    {
        return thumb;
    }
    public void setCharge(Long charge)
    {
        this.charge = charge;
    }

    public Long getCharge()
    {
        return charge;
    }
    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription()
    {
        return shortDescription;
    }
    public void setOriginalDesc(String originalDesc)
    {
        this.originalDesc = originalDesc;
    }

    public String getOriginalDesc()
    {
        return originalDesc;
    }
    public void setRenderDesc(String renderDesc)
    {
        this.renderDesc = renderDesc;
    }

    public String getRenderDesc()
    {
        return renderDesc;
    }
    public void setSeoKeywords(String seoKeywords)
    {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoKeywords()
    {
        return seoKeywords;
    }
    public void setSeoDescription(String seoDescription)
    {
        this.seoDescription = seoDescription;
    }

    public String getSeoDescription()
    {
        return seoDescription;
    }
    public void setPublishedAt(Date publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt()
    {
        return publishedAt;
    }
    public void setIsShow(Integer isShow)
    {
        this.isShow = isShow;
    }

    public Integer getIsShow()
    {
        return isShow;
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
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setIsRec(Integer isRec)
    {
        this.isRec = isRec;
    }

    public Integer getIsRec()
    {
        return isRec;
    }
    public void setUserCount(Long userCount)
    {
        this.userCount = userCount;
    }

    public Long getUserCount()
    {
        return userCount;
    }
    public void setIsFree(Integer isFree)
    {
        this.isFree = isFree;
    }

    public Integer getIsFree()
    {
        return isFree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("slug", getSlug())
            .append("thumb", getThumb())
            .append("charge", getCharge())
            .append("shortDescription", getShortDescription())
            .append("originalDesc", getOriginalDesc())
            .append("renderDesc", getRenderDesc())
            .append("seoKeywords", getSeoKeywords())
            .append("seoDescription", getSeoDescription())
            .append("publishedAt", getPublishedAt())
            .append("isShow", getIsShow())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("deletedAt", getDeletedAt())
            .append("categoryId", getCategoryId())
            .append("isRec", getIsRec())
            .append("userCount", getUserCount())
            .append("isFree", getIsFree())
            .toString();
    }
}
