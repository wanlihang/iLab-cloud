package com.ilab.service.mapper;

import com.ilab.service.domain.CourseChapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Mapper
public interface CourseChapterMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseChapter selectCourseChapterById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseChapter 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseChapter> selectCourseChapterList(CourseChapter courseChapter);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseChapter 【请填写功能名称】
     * @return 结果
     */
    public int insertCourseChapter(CourseChapter courseChapter);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseChapter 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseChapter(CourseChapter courseChapter);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseChapterById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseChapterByIds(Integer[] ids);
}
