package com.ilab.service.controller;

import com.ilab.service.domain.CourseAttach;
import com.ilab.service.service.ICourseAttachService;
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
@RequestMapping("/attach")
public class CourseAttachController extends BaseController
{
    @Autowired
    private ICourseAttachService courseAttachService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CourseAttach courseAttach)
    {
        startPage();
        List<CourseAttach> list = courseAttachService.selectCourseAttachList(courseAttach);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseAttach courseAttach)
    {
        List<CourseAttach> list = courseAttachService.selectCourseAttachList(courseAttach);
        ExcelUtil<CourseAttach> util = new ExcelUtil<CourseAttach>(CourseAttach.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courseAttachService.selectCourseAttachById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody CourseAttach courseAttach)
    {
        return toAjax(courseAttachService.insertCourseAttach(courseAttach));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CourseAttach courseAttach)
    {
        return toAjax(courseAttachService.updateCourseAttach(courseAttach));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(courseAttachService.deleteCourseAttachByIds(ids));
    }
}
