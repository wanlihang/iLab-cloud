package com.ilab.service.service.impl;

import com.ilab.service.domain.UserLikeCourses;
import com.ilab.service.mapper.UserLikeCoursesMapper;
import com.ilab.service.service.IUserLikeCoursesService;
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
public class UserLikeCoursesServiceImpl implements IUserLikeCoursesService
{
    @Autowired
    private UserLikeCoursesMapper userLikeCoursesMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserLikeCourses selectUserLikeCoursesById(Long id)
    {
        return userLikeCoursesMapper.selectUserLikeCoursesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserLikeCourses> selectUserLikeCoursesList(UserLikeCourses userLikeCourses)
    {
        return userLikeCoursesMapper.selectUserLikeCoursesList(userLikeCourses);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserLikeCourses(UserLikeCourses userLikeCourses)
    {
        return userLikeCoursesMapper.insertUserLikeCourses(userLikeCourses);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userLikeCourses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserLikeCourses(UserLikeCourses userLikeCourses)
    {
        return userLikeCoursesMapper.updateUserLikeCourses(userLikeCourses);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserLikeCoursesByIds(Long[] ids)
    {
        return userLikeCoursesMapper.deleteUserLikeCoursesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserLikeCoursesById(Long id)
    {
        return userLikeCoursesMapper.deleteUserLikeCoursesById(id);
    }
}
