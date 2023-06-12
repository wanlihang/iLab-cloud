package com.ilab.service.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ilab.service.constant.DockerImageConstant;
import com.ilab.service.constant.EnvConstant;
import com.ilab.service.constant.PodInfoConstant;
import com.ilab.service.constant.SplitConstant;
import com.ilab.service.domain.CourseLab;
import com.ilab.service.domain.Pods;
import com.ilab.service.dto.CourseChapterDTO;
import com.ilab.service.dto.LabEnvDTO;
import com.ilab.service.mapper.CourseLabMapper;
import com.ilab.service.mapper.PodsMapper;
import com.ilab.service.model.PodInfo;
import com.ilab.service.service.ILabEnvService;
import com.ilab.service.util.KubernetesClientUtil;
import com.ilab.service.util.TransformerUtil;
import io.fabric8.kubernetes.api.model.Pod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LabEnvServiceImpl implements ILabEnvService {

    @Autowired
    private PodsMapper podsMapper;

    @Autowired
    private CourseLabMapper courseLabMapper;

    @Override
    public LabEnvDTO getLabEnv(CourseChapterDTO chapterDTO) {
        LabEnvDTO labEnvDTO = new LabEnvDTO();

        // 1. 查询环境信息参数
        LambdaQueryWrapper<Pods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pods::getPodStatus, "r");
        lambdaQueryWrapper.eq(Pods::getCourseChapterId, chapterDTO.getChapterId());
        lambdaQueryWrapper.eq(Pods::getUserId, chapterDTO.getUserId());
        List<Pods> pods = podsMapper.selectList(lambdaQueryWrapper);


        String namespace;
        String podName;

        if (CollectionUtils.isEmpty(pods)) {
            // 无则创建
            PodInfo podInfo = new PodInfo();
            podInfo.setNamespace(PodInfoConstant.DEFAULT_NAMESPACE);
            podInfo.setPodName(PodInfoConstant.DEFAULT_POD_NAME + SplitConstant.HYPHEN + IdUtil.fastSimpleUUID());
            podInfo.setImage(DockerImageConstant.LAB14);

            KubernetesClientUtil kubernetesClientUtil = new KubernetesClientUtil();
            Pod pod = new Pod();
//            kubernetesClientUtil.createPod(podInfo);
//            try {
//                pod = kubernetesClientUtil.retryUntilPodReady(podInfo);
//            } catch (InterruptedException e) {
//                log.error("[LabEnvServiceImpl] [getLabEnv] [重试等待 pod ready 出错]", e);
//                throw new RuntimeException(e);
//            }

            namespace = podInfo.getNamespace();
            podName = podInfo.getPodName();

        } else {
            // 有则使用
            Pods pod = pods.get(0);
            namespace = pod.getPodNamespace();
            podName = pod.getPodName();
        }

        LambdaQueryWrapper<CourseLab> labEnvWrapper = new LambdaQueryWrapper<>();
        labEnvWrapper.eq(CourseLab::getChapterId, chapterDTO.getChapterId());
        List<CourseLab> courseLabs = courseLabMapper.selectList(labEnvWrapper);
        CourseLab courseLab = courseLabs.get(0);


        labEnvDTO.setCoursewarePath(courseLab.getCoursewarePath());
        labEnvDTO.setEnvType(courseLab.getEnvType());

        if (StringUtils.equals(EnvConstant.TERMINAL, courseLab.getEnvType())) {
            labEnvDTO.setLabEnvPath(TransformerUtil.replaceLabEnvInfo(EnvConstant.GOTTY_PATH, namespace, podName));
        } else if (StringUtils.equals(EnvConstant.EDITOR, courseLab.getEnvType())) {


        } else if (StringUtils.equals(EnvConstant.DESKTOP, courseLab.getEnvType())) {
            labEnvDTO.setLabEnvPath(EnvConstant.VNC_PATH);

        } else if (StringUtils.equals(EnvConstant.JUYPTER, courseLab.getEnvType())) {


        }


        return labEnvDTO;
    }

}
