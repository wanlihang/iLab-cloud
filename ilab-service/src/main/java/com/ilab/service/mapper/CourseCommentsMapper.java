package com.ilab.service.mapper;

import com.ilab.service.domain.CourseComments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Mapper
public interface CourseCommentsMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseComments selectCourseCommentsById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseComments 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseComments> selectCourseCommentsList(CourseComments courseComments);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseComments 【请填写功能名称】
     * @return 结果
     */
    public int insertCourseComments(CourseComments courseComments);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseComments 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseComments(CourseComments courseComments);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseCommentsById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseCommentsByIds(Integer[] ids);
}
