package com.ilab.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilab.service.domain.CourseLab;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * labMapper接口
 *
 * @author wanlh
 * @date 2023-03-31
 */
@Mapper
public interface CourseLabMapper extends BaseMapper<CourseLab> {
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
     * 删除lab
     *
     * @param id lab主键
     * @return 结果
     */
    public int deleteCourseLabById(Integer id);

    /**
     * 批量删除lab
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseLabByIds(Integer[] ids);
}
