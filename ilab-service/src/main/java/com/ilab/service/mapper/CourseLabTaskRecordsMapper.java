package com.ilab.service.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilab.service.domain.CourseLabTaskRecords;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-16
 */
@Mapper
public interface CourseLabTaskRecordsMapper extends BaseMapper<CourseLabTaskRecords>
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
    public int insertCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseLabTaskRecords 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseLabTaskRecords(CourseLabTaskRecords courseLabTaskRecords);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseLabTaskRecordsById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseLabTaskRecordsByIds(Integer[] ids);
}
