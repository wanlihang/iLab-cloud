package com.ilab.service.service;

import com.ilab.service.domain.CourseLab;

import java.util.List;


/**
 * labService接口
 *
 * @author wanlh
 * @date 2023-03-31
 */
public interface ICourseLabService {
    /**
     * 查询lab
     *
     * @param id lab主键
     * @return lab
     */
    public CourseLab selectCourseLabById(Integer id);

    /**
     * 查询lab列表
     *
     * @param courseLab lab
     * @return lab集合
     */
    public List<CourseLab> selectCourseLabList(CourseLab courseLab);

    /**
     * 新增lab
     *
     * @param courseLab lab
     * @return 结果
     */
    public int insertCourseLab(CourseLab courseLab);

    /**
     * 修改lab
     *
     * @param courseLab lab
     * @return 结果
     */
    public int updateCourseLab(CourseLab courseLab);

    /**
     * 批量删除lab
     *
     * @param ids 需要删除的lab主键集合
     * @return 结果
     */
    public int deleteCourseLabByIds(Integer[] ids);

    /**
     * 删除lab信息
     *
     * @param id lab主键
     * @return 结果
     */
    public int deleteCourseLabById(Integer id);
}
