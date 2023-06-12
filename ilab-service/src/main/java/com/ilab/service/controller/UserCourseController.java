package com.ilab.service.controller;

import com.ilab.service.domain.UserCourse;
import com.ilab.service.service.IUserCourseService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@RestController
@RequestMapping("/course")
public class UserCourseController extends BaseController
{
    @Autowired
    private IUserCourseService userCourseService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserCourse userCourse)
    {
        startPage();
        List<UserCourse> list = userCourseService.selectUserCourseList(userCourse);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserCourse userCourse)
    {
        List<UserCourse> list = userCourseService.selectUserCourseList(userCourse);
        ExcelUtil<UserCourse> util = new ExcelUtil<UserCourse>(UserCourse.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(userCourseService.selectUserCourseByUserId(userId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody UserCourse userCourse)
    {
        return toAjax(userCourseService.insertUserCourse(userCourse));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody UserCourse userCourse)
    {
        return toAjax(userCourseService.updateUserCourse(userCourse));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userCourseService.deleteUserCourseByUserIds(userIds));
    }
}
