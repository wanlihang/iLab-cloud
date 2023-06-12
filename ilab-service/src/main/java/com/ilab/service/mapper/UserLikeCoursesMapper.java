package com.ilab.service.mapper;

import com.ilab.service.domain.UserLikeCourses;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Mapper
public interface UserLikeCoursesMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserLikeCourses selectUserLikeCoursesById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserLikeCourses> selectUserLikeCoursesList(UserLikeCourses userLikeCourses);

    /**
     * 新增【请填写功能名称】
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 结果
     */
    public int insertUserLikeCourses(UserLikeCourses userLikeCourses);

    /**
     * 修改【请填写功能名称】
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 结果
     */
    public int updateUserLikeCourses(UserLikeCourses userLikeCourses);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserLikeCoursesById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserLikeCoursesByIds(Long[] ids);
}
