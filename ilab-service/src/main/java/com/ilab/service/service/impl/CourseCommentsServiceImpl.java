package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseComments;
import com.ilab.service.mapper.CourseCommentsMapper;
import com.ilab.service.service.ICourseCommentsService;
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
public class CourseCommentsServiceImpl implements ICourseCommentsService
{
    @Autowired
    private CourseCommentsMapper courseCommentsMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseComments selectCourseCommentsById(Integer id)
    {
        return courseCommentsMapper.selectCourseCommentsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseComments 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseComments> selectCourseCommentsList(CourseComments courseComments)
    {
        return courseCommentsMapper.selectCourseCommentsList(courseComments);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseComments 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseComments(CourseComments courseComments)
    {
        return courseCommentsMapper.insertCourseComments(courseComments);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseComments 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseComments(CourseComments courseComments)
    {
        return courseCommentsMapper.updateCourseComments(courseComments);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseCommentsByIds(Integer[] ids)
    {
        return courseCommentsMapper.deleteCourseCommentsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseCommentsById(Integer id)
    {
        return courseCommentsMapper.deleteCourseCommentsById(id);
    }
}
