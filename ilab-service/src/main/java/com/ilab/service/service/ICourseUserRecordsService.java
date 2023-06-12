package com.ilab.service.service;

import com.ilab.service.domain.CourseUserRecords;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
public interface ICourseUserRecordsService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseUserRecords selectCourseUserRecordsById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseUserRecords> selectCourseUserRecordsList(CourseUserRecords courseUserRecords);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 结果
     */
    public int insertCourseUserRecords(CourseUserRecords courseUserRecords);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseUserRecords(CourseUserRecords courseUserRecords);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCourseUserRecordsByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseUserRecordsById(Integer id);
}
