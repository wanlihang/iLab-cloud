package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseUserRecords;
import com.ilab.service.mapper.CourseUserRecordsMapper;
import com.ilab.service.service.ICourseUserRecordsService;
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
public class CourseUserRecordsServiceImpl implements ICourseUserRecordsService
{
    @Autowired
    private CourseUserRecordsMapper courseUserRecordsMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseUserRecords selectCourseUserRecordsById(Integer id)
    {
        return courseUserRecordsMapper.selectCourseUserRecordsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseUserRecords> selectCourseUserRecordsList(CourseUserRecords courseUserRecords)
    {
        return courseUserRecordsMapper.selectCourseUserRecordsList(courseUserRecords);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseUserRecords(CourseUserRecords courseUserRecords)
    {
        return courseUserRecordsMapper.insertCourseUserRecords(courseUserRecords);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseUserRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseUserRecords(CourseUserRecords courseUserRecords)
    {
        return courseUserRecordsMapper.updateCourseUserRecords(courseUserRecords);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseUserRecordsByIds(Integer[] ids)
    {
        return courseUserRecordsMapper.deleteCourseUserRecordsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseUserRecordsById(Integer id)
    {
        return courseUserRecordsMapper.deleteCourseUserRecordsById(id);
    }
}
