package com.ilab.service.controller;

import com.ilab.service.domain.CourseLab;
import com.ilab.service.service.ICourseLabService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * labController
 *
 * @author wanlh
 * @date 2023-03-31
 */
@RestController
@CrossOrigin
@RequestMapping("/lab")
public class CourseLabController extends BaseController {
    @Autowired
    private ICourseLabService courseLabService;

    /**
     * 查询lab列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CourseLab courseLab) {
//        startPage();
        List<CourseLab> list = courseLabService.selectCourseLabList(courseLab);
        return getDataTable(list);
    }

    /**
     * 导出lab列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseLab courseLab) {
        List<CourseLab> list = courseLabService.selectCourseLabList(courseLab);
        ExcelUtil<CourseLab> util = new ExcelUtil<CourseLab>(CourseLab.class);
        util.exportExcel(response, list, "lab数据");
    }

    /**
     * 获取lab详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(courseLabService.selectCourseLabById(id));
    }

    /**
     * 新增lab
     */
    @PostMapping
    public AjaxResult add(@RequestBody CourseLab courseLab) {
        return toAjax(courseLabService.insertCourseLab(courseLab));
    }

    /**
     * 修改lab
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CourseLab courseLab) {
        return toAjax(courseLabService.updateCourseLab(courseLab));
    }

    /**
     * 删除lab
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(courseLabService.deleteCourseLabByIds(ids));
    }
}
