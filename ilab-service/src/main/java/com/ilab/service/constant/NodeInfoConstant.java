package com.ilab.service.constant;

import lombok.Data;

import java.util.*;

/**
 * @author wanlh
 */
@Data
public class NodeInfoConstant {

    public static List<String> NODE_NAME_LIST = new ArrayList<>(Arrays.asList("node1", "node2", "node3", "node4"));

    public static final Map<String, Integer> NODE_NAME_2_ID_MAP = new HashMap<>(8);

    /**
     * 节点 1 名称
     */
    public static final String NODE1 = "node1";

    /**
     * 节点 2 名称
     */
    public static final String NODE2 = "node2";

    /**
     * 节点 3 名称
     */
    public static final String NODE3 = "node3";

    /**
     * 节点 3 名称
     */
    public static final String NODE4 = "node4";

    /**
     * 节点选择器标签 -
     */
    public static final String LABEL_HOSTNAME = "kubernetes.io/hostname";

    static {
        NODE_NAME_2_ID_MAP.put("node1", 0);
        NODE_NAME_2_ID_MAP.put("node2", 1);
        NODE_NAME_2_ID_MAP.put("node3", 2);
        NODE_NAME_2_ID_MAP.put("node4", 3);
    }

}
