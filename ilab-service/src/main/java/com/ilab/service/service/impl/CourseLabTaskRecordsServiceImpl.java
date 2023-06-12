package com.ilab.service.service.impl;

import java.util.List;

import com.ilab.service.service.ICourseLabTaskRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ilab.service.mapper.CourseLabTaskRecordsMapper;
import com.ilab.service.domain.CourseLabTaskRecords;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-16
 */
@Service
public class CourseLabTaskRecordsServiceImpl extends ServiceImpl<CourseLabTaskRecordsMapper, CourseLabTaskRecords> implements ICourseLabTaskRecordsService
{
    @Autowired
    private CourseLabTaskRecordsMapper courseLabTaskRecordsMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseLabTaskRecords selectCourseLabTaskRecordsById(Integer id)
    {
        return courseLabTaskRecordsMapper.selectCourseLabTaskRecordsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseLabTaskRecords> selectCourseLabTaskRecordsList(CourseLabTaskRecords courseLabTaskRecords)
    {
        return courseLabTaskRecordsMapper.selectCourseLabTaskRecordsList(courseLabTaskRecords);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public boolean insertCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords) {
        return saveOrUpdate(courseLabTaskRecords);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords)
    {
        return courseLabTaskRecordsMapper.updateCourseLabTaskRecords(courseLabTaskRecords);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabTaskRecordsByIds(Integer[] ids)
    {
        return courseLabTaskRecordsMapper.deleteCourseLabTaskRecordsByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabTaskRecordsById(Integer id)
    {
        return courseLabTaskRecordsMapper.deleteCourseLabTaskRecordsById(id);
    }
}
