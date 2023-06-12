package com.ilab.service.converter;

import com.ilab.service.dto.PodInfoDTO;
import com.ilab.service.model.PodInfo;
import com.ruoyi.common.core.utils.bean.BeanUtils;

public class PodInfoConverter {
    public static PodInfo convertDTO2Model(PodInfoDTO podInfoDTO) {
        PodInfo podInfo = new PodInfo();
        BeanUtils.copyProperties(podInfoDTO, podInfo);
        return podInfo;
    }

    public static PodInfoDTO convertModel2DTO(PodInfo podInfo) {
        PodInfoDTO podInfoDTO = new PodInfoDTO();
        BeanUtils.copyProperties(podInfo, podInfoDTO);
        return podInfoDTO;
    }
}
