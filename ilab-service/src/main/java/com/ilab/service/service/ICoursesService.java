package com.ilab.service.service;

import com.ilab.service.domain.Courses;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public interface ICoursesService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Courses selectCoursesById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courses 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Courses> selectCoursesList(Courses courses);

    /**
     * 新增【请填写功能名称】
     *
     * @param courses 【请填写功能名称】
     * @return 结果
     */
    public int insertCourses(Courses courses);

    /**
     * 修改【请填写功能名称】
     *
     * @param courses 【请填写功能名称】
     * @return 结果
     */
    public int updateCourses(Courses courses);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCoursesByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCoursesById(Integer id);
}
