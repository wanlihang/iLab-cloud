package com.ilab.service.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ilab.service.domain.CourseLabTaskRecords;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-11-16
 */
public interface ICourseLabTaskRecordsService extends IService<CourseLabTaskRecords>
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseLabTaskRecords selectCourseLabTaskRecordsById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseLabTaskRecords> selectCourseLabTaskRecordsList(CourseLabTaskRecords courseLabTaskRecords);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 结果
     */
    public boolean insertCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCourseLabTaskRecordsByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseLabTaskRecordsById(Integer id);
}
