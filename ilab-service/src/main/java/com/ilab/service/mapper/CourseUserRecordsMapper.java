package com.ilab.service.mapper;

import com.ilab.service.domain.CourseUserRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Mapper
public interface CourseUserRecordsMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseUserRecordsById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseUserRecordsByIds(Integer[] ids);
}
