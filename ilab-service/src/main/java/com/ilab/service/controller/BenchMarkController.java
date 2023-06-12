package com.ilab.service.controller;

import com.ilab.service.domain.CourseLabEnv;
import com.ilab.service.model.NodeInfo;
import com.ilab.service.model.PodInfo;
import com.ilab.service.model.TaskInfo;
import com.ilab.service.service.ICourseLabEnvService;
import com.ilab.service.thread.ILabScheduleExecutor;
import com.ilab.service.thread.ILabTaskExecutor;
import com.ilab.service.thread.ILabThreadPool;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/bench")
public class BenchMarkController extends BaseController {

    @Autowired
    private ICourseLabEnvService courseLabEnvService;

    @GetMapping("/exec/{id}")
    public AjaxResult execBench(@PathVariable("id") Integer id, @RequestParam String schedulerType){
        CourseLabEnv courseLabEnv = courseLabEnvService.selectCourseLabEnvById(id);
        if (ObjectUtils.isEmpty(courseLabEnv)) {
            log.warn("[BenchMarkController] [execBench] [查询任务负载信息失败]");
            return AjaxResult.error("查询任务负载信息失败");
        }
        TaskInfo taskInfo = new TaskInfo(courseLabEnv);
        taskInfo.setSchedulerType(schedulerType);
        ILabThreadPool.getInstance().submitILabThreadPool(new ILabScheduleExecutor(taskInfo));
        return AjaxResult.success();
    }

}
