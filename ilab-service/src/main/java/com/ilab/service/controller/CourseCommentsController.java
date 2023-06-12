package com.ilab.service.controller;

import com.ilab.service.domain.CourseComments;
import com.ilab.service.service.ICourseCommentsService;
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
@RequestMapping("/comments")
public class CourseCommentsController extends BaseController
{
    @Autowired
    private ICourseCommentsService courseCommentsService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CourseComments courseComments)
    {
        startPage();
        List<CourseComments> list = courseCommentsService.selectCourseCommentsList(courseComments);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseComments courseComments)
    {
        List<CourseComments> list = courseCommentsService.selectCourseCommentsList(courseComments);
        ExcelUtil<CourseComments> util = new ExcelUtil<CourseComments>(CourseComments.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(courseCommentsService.selectCourseCommentsById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody CourseComments courseComments)
    {
        return toAjax(courseCommentsService.insertCourseComments(courseComments));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CourseComments courseComments)
    {
        return toAjax(courseCommentsService.updateCourseComments(courseComments));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(courseCommentsService.deleteCourseCommentsByIds(ids));
    }
}
