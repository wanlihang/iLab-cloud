package com.ilab.service.service.impl;

import com.ilab.service.domain.CourseLabEnv;
import com.ilab.service.mapper.CourseLabEnvMapper;
import com.ilab.service.service.ICourseLabEnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-15
 */
@Service
public class CourseLabEnvServiceImpl implements ICourseLabEnvService {
    @Autowired
    private CourseLabEnvMapper courseLabEnvMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CourseLabEnv selectCourseLabEnvById(Integer id) {
        return courseLabEnvMapper.selectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CourseLabEnv> selectCourseLabEnvList(CourseLabEnv courseLabEnv) {
        return courseLabEnvMapper.selectCourseLabEnvList(courseLabEnv);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCourseLabEnv(CourseLabEnv courseLabEnv) {
        return courseLabEnvMapper.insertCourseLabEnv(courseLabEnv);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param courseLabEnv 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCourseLabEnv(CourseLabEnv courseLabEnv) {
        return courseLabEnvMapper.updateCourseLabEnv(courseLabEnv);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabEnvByIds(Integer[] ids) {
        return courseLabEnvMapper.deleteCourseLabEnvByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabEnvById(Integer id) {
        return courseLabEnvMapper.deleteCourseLabEnvById(id);
    }
}
