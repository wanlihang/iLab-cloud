package com.ilab.service.service;

import java.util.List;

import com.ilab.service.domain.CourseAttach;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public interface ICourseAttachService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseAttach selectCourseAttachById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseAttach 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseAttach> selectCourseAttachList(CourseAttach courseAttach);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseAttach 【请填写功能名称】
     * @return 结果
     */
    public int insertCourseAttach(CourseAttach courseAttach);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseAttach 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseAttach(CourseAttach courseAttach);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCourseAttachByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseAttachById(Long id);
}
