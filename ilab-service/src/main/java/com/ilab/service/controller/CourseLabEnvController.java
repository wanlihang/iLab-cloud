package com.ilab.service.controller;

import com.ilab.service.domain.CourseLabEnv;
import com.ilab.service.service.ICourseLabEnvService;
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
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-15
 */
@RestController
@CrossOrigin
@RequestMapping("/env")
public class CourseLabEnvController extends BaseController {
    @Autowired
    private ICourseLabEnvService courseLabEnvService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CourseLabEnv courseLabEnv) {
//        startPage();
        List<CourseLabEnv> list = courseLabEnvService.selectCourseLabEnvList(courseLabEnv);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseLabEnv courseLabEnv) {
        List<CourseLabEnv> list = courseLabEnvService.selectCourseLabEnvList(courseLabEnv);
        ExcelUtil<CourseLabEnv> util = new ExcelUtil<CourseLabEnv>(CourseLabEnv.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(courseLabEnvService.selectCourseLabEnvById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody CourseLabEnv courseLabEnv) {
        return toAjax(courseLabEnvService.insertCourseLabEnv(courseLabEnv));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CourseLabEnv courseLabEnv) {
        return toAjax(courseLabEnvService.updateCourseLabEnv(courseLabEnv));
    }

    /**
     * 删除【请填写功能名称】
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(courseLabEnvService.deleteCourseLabEnvByIds(ids));
    }
}
