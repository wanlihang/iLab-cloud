package com.ilab.service.service;

import java.util.List;

import com.ilab.service.domain.CourseLabEnv;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-11-15
 */
public interface ICourseLabEnvService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CourseLabEnv selectCourseLabEnvById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CourseLabEnv> selectCourseLabEnvList(CourseLabEnv courseLabEnv);

    /**
     * 新增【请填写功能名称】
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 结果
     */
    public int insertCourseLabEnv(CourseLabEnv courseLabEnv);

    /**
     * 修改【请填写功能名称】
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 结果
     */
    public int updateCourseLabEnv(CourseLabEnv courseLabEnv);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCourseLabEnvByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCourseLabEnvById(Integer id);
}
