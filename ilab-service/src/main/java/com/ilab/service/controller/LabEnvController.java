package com.ilab.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.ilab.service.dto.CourseChapterDTO;
import com.ilab.service.dto.LabEnvDTO;
import com.ilab.service.service.ILabEnvService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/iLabEnv")
public class LabEnvController {

    @Autowired()
    private ILabEnvService iLabEnvService;

    @PostMapping("/getEnvPath")
    public AjaxResult getEnvPath(@RequestBody JSONObject params) {
        CourseChapterDTO chapterDTO = JSONObject.toJavaObject(params, CourseChapterDTO.class);

        LabEnvDTO labEnvDTO = iLabEnvService.getLabEnv(chapterDTO);

        return AjaxResult.success(labEnvDTO);
    }


}
