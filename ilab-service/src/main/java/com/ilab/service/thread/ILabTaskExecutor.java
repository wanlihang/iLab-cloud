package com.ilab.service.thread;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.ilab.service.domain.CourseLabTaskRecords;
import com.ilab.service.model.TaskInfo;
import com.ilab.service.util.KubernetesClientUtil;
import com.ilab.service.util.TaskCostCountUtil;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.ilab.service.util.ServiceContextUtil.iCourseLabTaskRecordsService;

/**
 * @author wanlh
 */
@Slf4j
public class ILabTaskExecutor implements Runnable {

    private final Pod pod;

    private final TaskInfo taskInfo;

    private final String schedulerType;

    private final Long submissionTime;

    private final Long schedulerEndTime;


    /**
     * @param pod              节点信息
     * @param taskInfo         任务信息
     * @param schedulerType    调度方法
     * @param submissionTime   任务提交时间
     * @param schedulerEndTime 任务调度结束时间
     */
    public ILabTaskExecutor(Pod pod, TaskInfo taskInfo, String schedulerType, Long submissionTime, Long schedulerEndTime) {
        this.pod = pod;
        this.taskInfo = taskInfo;
        this.schedulerType = schedulerType;
        this.submissionTime = submissionTime;
        this.schedulerEndTime = schedulerEndTime;
    }

    /**
     * 任务执行线程启动器
     */
    @SneakyThrows
    @Override
    public void run() {
        KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
        String nodeName = pod.getSpec().getNodeName();
        Integer code = KubernetesClientUtil.getCode(schedulerType);
        // 计算等待时间单位
        double waitTime1 = TaskCostCountUtil.getWaitTime(taskInfo.getEnvId(), nodeName, code);
        long waitTime = Math.round(1000 * waitTime1);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            log.error("[ILabTaskExecutor] [run] [线程等待失败]", e);
            throw new RuntimeException(e);
        }
        // 1. 执行任务
        // begin----执行前将任务代价添加到节点----begin
        try {
            TaskCostCountUtil.addOrRemoveNodeLoad(nodeName, taskInfo.getEnvId(), taskInfo.getTaskName(), 1);
        } catch (Exception e) {
            log.error("[ILabTaskExecutor] [run] [增加任务代价出错]", e);
        }
        long startTime = System.currentTimeMillis();
        long expt1 = Math.round(TaskCostCountUtil.getTaskCost(nodeName, taskInfo.getEnvId(), TaskCostCountUtil.EXEC_TIME_ID));
        long expt = Math.round(1000 * expt1);

        log.info("[ILabTaskExecutor] [run] [任务{}开始执行，预期执行时间：{}]", taskInfo.getTaskName(), expt);

        kubernetesClientUtil.execEquivalent(pod, taskInfo, expt);

        try {
            TaskCostCountUtil.addOrRemoveNodeLoad(nodeName, taskInfo.getEnvId(), taskInfo.getTaskName(), -1);
        } catch (Exception e) {
            log.error("[ILabTaskExecutor] [run] [删除任务代价出错]", e);
        }
        // end------执行后将任务代价从节点移除------end

        long endTime = System.currentTimeMillis();

        log.info("[ILabTaskExecutor] [run] [ {} 下 {} 任务执行时间: {}]", schedulerType, taskInfo.getTaskName(), (endTime - startTime) / 1000.0);

        // 2. 记录任务执行信息
        CourseLabTaskRecords courseLabTaskRecords = new CourseLabTaskRecords();

        courseLabTaskRecords.setLabEnvId(taskInfo.getEnvId());
        courseLabTaskRecords.setTaskName(taskInfo.getTaskName());
        courseLabTaskRecords.setAssignNode(nodeName);
        courseLabTaskRecords.setSchedulerType(schedulerType);

        courseLabTaskRecords.setSubmissionTime(DateUtil.format(DateUtil.date(submissionTime), "yyyy-MM-dd HH:mm:ss.SSS"));
        courseLabTaskRecords.setSchedulerEndTime(DateUtil.format(DateUtil.date(schedulerEndTime), "yyyy-MM-dd HH:mm:ss.SSS"));
        courseLabTaskRecords.setScheduleTime(schedulerEndTime - submissionTime);

        courseLabTaskRecords.setWaitTime(waitTime);

        courseLabTaskRecords.setStartTime(DateUtil.format(DateUtil.date(startTime), "yyyy-MM-dd HH:mm:ss.SSS"));
        courseLabTaskRecords.setEndTime(DateUtil.format(DateUtil.date(endTime), "yyyy-MM-dd HH:mm:ss.SSS"));
        courseLabTaskRecords.setExecTime(endTime - startTime);

        courseLabTaskRecords.setContinueTime(endTime - submissionTime);

        courseLabTaskRecords.setGmtCreate(DateUtil.date(endTime));
        courseLabTaskRecords.setGmtModified(DateUtil.date(endTime));
        iCourseLabTaskRecordsService.insertCourseLabTaskRecords(courseLabTaskRecords);
        log.info("[ILabTaskExecutor] [run] [插入数据库成功] [CourseLabTaskRecords: {}]", courseLabTaskRecords);

        // 3. 执行完成后删除 pod
        kubernetesClientUtil.deletePod(pod);
    }
}
