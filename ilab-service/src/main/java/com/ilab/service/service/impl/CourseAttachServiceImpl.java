package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseAttach;
import com.ilab.service.mapper.CourseAttachMapper;
import com.ilab.service.service.ICourseAttachService;
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
public class CourseAttachServiceImpl implements ICourseAttachService
{
    @Autowired
    private CourseAttachMapper courseAttachMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseAttach selectCourseAttachById(Long id)
    {
        return courseAttachMapper.selectCourseAttachById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseAttach 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseAttach> selectCourseAttachList(CourseAttach courseAttach)
    {

        return courseAttachMapper.selectCourseAttachList(courseAttach);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseAttach 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseAttach(CourseAttach courseAttach)
    {
        return courseAttachMapper.insertCourseAttach(courseAttach);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseAttach 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseAttach(CourseAttach courseAttach)
    {
        return courseAttachMapper.updateCourseAttach(courseAttach);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseAttachByIds(Long[] ids)
    {
        return courseAttachMapper.deleteCourseAttachByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseAttachById(Long id)
    {
        return courseAttachMapper.deleteCourseAttachById(id);
    }
}
