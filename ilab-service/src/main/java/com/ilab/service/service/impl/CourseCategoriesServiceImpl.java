package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseCategories;
import com.ilab.service.mapper.CourseCategoriesMapper;
import com.ilab.service.service.ICourseCategoriesService;
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
public class CourseCategoriesServiceImpl implements ICourseCategoriesService
{
    @Autowired
    private CourseCategoriesMapper courseCategoriesMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseCategories selectCourseCategoriesById(Integer id)
    {
        return courseCategoriesMapper.selectCourseCategoriesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseCategories 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseCategories> selectCourseCategoriesList(CourseCategories courseCategories)
    {
        return courseCategoriesMapper.selectCourseCategoriesList(courseCategories);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseCategories 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseCategories(CourseCategories courseCategories)
    {
        return courseCategoriesMapper.insertCourseCategories(courseCategories);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseCategories 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseCategories(CourseCategories courseCategories)
    {
        return courseCategoriesMapper.updateCourseCategories(courseCategories);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseCategoriesByIds(Integer[] ids)
    {
        return courseCategoriesMapper.deleteCourseCategoriesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseCategoriesById(Integer id)
    {
        return courseCategoriesMapper.deleteCourseCategoriesById(id);
    }
}
