package com.ilab.service.controller;

import com.ilab.service.domain.UserLikeCourses;
import com.ilab.service.service.IUserLikeCoursesService;
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
@RequestMapping("/userLikeCourses")
public class UserLikeCoursesController extends BaseController
{
    @Autowired
    private IUserLikeCoursesService userLikeCoursesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserLikeCourses userLikeCourses)
    {
        startPage();
        List<UserLikeCourses> list = userLikeCoursesService.selectUserLikeCoursesList(userLikeCourses);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLikeCourses userLikeCourses)
    {
        List<UserLikeCourses> list = userLikeCoursesService.selectUserLikeCoursesList(userLikeCourses);
        ExcelUtil<UserLikeCourses> util = new ExcelUtil<UserLikeCourses>(UserLikeCourses.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userLikeCoursesService.selectUserLikeCoursesById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody UserLikeCourses userLikeCourses)
    {
        return toAjax(userLikeCoursesService.insertUserLikeCourses(userLikeCourses));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody UserLikeCourses userLikeCourses)
    {
        return toAjax(userLikeCoursesService.updateUserLikeCourses(userLikeCourses));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userLikeCoursesService.deleteUserLikeCoursesByIds(ids));
    }
}
