package com.ilab.service.util;

import com.ilab.service.algorithm.ILabSchedulerBasedTaskLife;
import com.ilab.service.service.ICourseLabTaskRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanlh
 */
@Component
public class ServiceContextUtil {

    public static ILabSchedulerBasedTaskLife iLabSchedulerBasedTaskLife;

    public static ICourseLabTaskRecordsService iCourseLabTaskRecordsService;

    @Autowired
    public void setILabSchedulerBasedTaskLife(ILabSchedulerBasedTaskLife service){
        iLabSchedulerBasedTaskLife = service;
    }

    @Autowired
    public void setICourseLabTaskRecordsService(ICourseLabTaskRecordsService service){
        iCourseLabTaskRecordsService = service;
    }
}
