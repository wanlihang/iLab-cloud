package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseChapter;
import com.ilab.service.mapper.CourseChapterMapper;
import com.ilab.service.service.ICourseChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Service
public class CourseChapterServiceImpl implements ICourseChapterService
{
    @Autowired
    private CourseChapterMapper courseChapterMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseChapter selectCourseChapterById(Integer id)
    {
        return courseChapterMapper.selectCourseChapterById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseChapter 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseChapter> selectCourseChapterList(CourseChapter courseChapter)
    {
        return courseChapterMapper.selectCourseChapterList(courseChapter);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseChapter 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseChapter(CourseChapter courseChapter)
    {
        return courseChapterMapper.insertCourseChapter(courseChapter);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseChapter 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseChapter(CourseChapter courseChapter)
    {
        return courseChapterMapper.updateCourseChapter(courseChapter);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseChapterByIds(Integer[] ids)
    {
        return courseChapterMapper.deleteCourseChapterByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseChapterById(Integer id)
    {
        return courseChapterMapper.deleteCourseChapterById(id);
    }
}
