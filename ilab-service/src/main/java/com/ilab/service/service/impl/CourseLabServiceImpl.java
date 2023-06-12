package com.ilab.service.service.impl;

import java.util.List;

import com.ilab.service.domain.CourseLab;
import com.ilab.service.mapper.CourseLabMapper;
import com.ilab.service.service.ICourseLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * labService业务层处理
 *
 * @author wanlh
 * @date 2023-03-31
 */
@Service
public class CourseLabServiceImpl implements ICourseLabService {
    @Autowired
    private CourseLabMapper courseLabMapper;

    /**
     * 查询lab
     *
     * @param id lab主键
     * @return lab
     */
    @Override
    public CourseLab selectCourseLabById(Integer id) {
        return courseLabMapper.selectCourseLabById(id);
    }

    /**
     * 查询lab列表
     *
     * @param courseLab lab
     * @return lab
     */
    @Override
    public List<CourseLab> selectCourseLabList(CourseLab courseLab) {
        return courseLabMapper.selectCourseLabList(courseLab);
    }

    /**
     * 新增lab
     *
     * @param courseLab lab
     * @return 结果
     */
    @Override
    public int insertCourseLab(CourseLab courseLab) {
        return courseLabMapper.insertCourseLab(courseLab);
    }

    /**
     * 修改lab
     *
     * @param courseLab lab
     * @return 结果
     */
    @Override
    public int updateCourseLab(CourseLab courseLab) {
        return courseLabMapper.updateCourseLab(courseLab);
    }

    /**
     * 批量删除lab
     *
     * @param ids 需要删除的lab主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabByIds(Integer[] ids) {
        return courseLabMapper.deleteCourseLabByIds(ids);
    }

    /**
     * 删除lab信息
     *
     * @param id lab主键
     * @return 结果
     */
    @Override
    public int deleteCourseLabById(Integer id) {
        return courseLabMapper.deleteCourseLabById(id);
    }
}
