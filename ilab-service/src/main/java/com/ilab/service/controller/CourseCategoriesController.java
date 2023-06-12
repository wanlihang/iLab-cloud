package com.ilab.service.controller;

import com.ilab.service.domain.CourseCategories;
import com.ilab.service.service.ICourseCategoriesService;
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
@RequestMapping("/categories")
public class CourseCategoriesController extends BaseController
{
    @Autowired
    private ICourseCategoriesService courseCategoriesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CourseCategories courseCategories)
    {
        startPage();
        List<CourseCategories> list = courseCategoriesService.selectCourseCategoriesList(courseCategories);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseCategories courseCategories)
    {
        List<CourseCategories> list = courseCategoriesService.selectCourseCategoriesList(courseCategories);
        ExcelUtil<CourseCategories> util = new ExcelUtil<CourseCategories>(CourseCategories.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(courseCategoriesService.selectCourseCategoriesById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody CourseCategories courseCategories)
    {
        return toAjax(courseCategoriesService.insertCourseCategories(courseCategories));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CourseCategories courseCategories)
    {
        return toAjax(courseCategoriesService.updateCourseCategories(courseCategories));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(courseCategoriesService.deleteCourseCategoriesByIds(ids));
    }
}
