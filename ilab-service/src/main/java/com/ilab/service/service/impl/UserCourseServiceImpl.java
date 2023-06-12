package com.ilab.service.service.impl;

import com.ilab.service.domain.UserCourse;
import com.ilab.service.mapper.UserCourseMapper;
import com.ilab.service.service.IUserCourseService;
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
public class UserCourseServiceImpl implements IUserCourseService
{
    @Autowired
    private UserCourseMapper userCourseMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserCourse selectUserCourseByUserId(Long userId)
    {
        return userCourseMapper.selectUserCourseByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userCourse 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserCourse> selectUserCourseList(UserCourse userCourse)
    {
        return userCourseMapper.selectUserCourseList(userCourse);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userCourse 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserCourse(UserCourse userCourse)
    {
        return userCourseMapper.insertUserCourse(userCourse);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userCourse 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserCourse(UserCourse userCourse)
    {
        return userCourseMapper.updateUserCourse(userCourse);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseByUserIds(Long[] userIds)
    {
        return userCourseMapper.deleteUserCourseByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserCourseByUserId(Long userId)
    {
        return userCourseMapper.deleteUserCourseByUserId(userId);
    }
}
