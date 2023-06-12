package com.ilab.service.util;

import com.ilab.service.constant.ReplaceConstant;
import com.ilab.service.constant.SplitConstant;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TransformerUtil {

    public static List<String> string2ListByComma (String text) {
        if (StringUtils.isBlank(text)) {
            log.warn("[TransformerUtil] [string2ListByComma] [空字符转换]");
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(StringUtils.split(text, SplitConstant.COMMA)));
    }

    public static String replaceNodeName (String text, String replacement) {
        if (StringUtils.isBlank(text)) {
            log.warn("[TransformerUtil] [replaceNodeName] [空字符转换]");
            return "";
        }
        return StringUtils.replace(text, ReplaceConstant.NODE_NAME, replacement);
    }

    public static String imageName2ContainerName (String imageName) {
        if (StringUtils.isBlank(imageName)) {
            log.warn("[TransformerUtil] [imageName2ContainerName] [空字符转换]");
            return "";
        }
        String[] split = StringUtils.split(imageName, SplitConstant.COLON);
        if (null == split || split.length < 1) {
            log.warn("[TransformerUtil] [imageName2ContainerName] [空字符转换]");
            return "";
        }
        return StringUtils.replace(split[0], SplitConstant.SLASH, SplitConstant.HYPHEN);
    }

    public static String replaceLabEnvInfo (String text, String namespace, String podName) {
        if (StringUtils.isBlank(text)) {
            log.warn("[TransformerUtil] [replaceLabEnvInfo] [空字符转换]");
            return "";
        }
        text = StringUtils.replace(text, ReplaceConstant.NAMESPACE, namespace);
        text = StringUtils.replace(text, ReplaceConstant.POD_NAME, podName);
        return text;
    }

}
