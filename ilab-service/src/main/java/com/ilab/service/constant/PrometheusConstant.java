package com.ilab.service.constant;

public class PrometheusConstant {
    /**
     * prometheus-查询SUCCESS
     */
    public static final String SUCCESS = "success";


    /**
     * prometheus-查询参数
     */
    public static final String QUERY = "query";

    /**
     *  Windows主机操作系统信息
     */
    public static final String WINDOWS_OS_INFO = "windows_os_info";

    /**
     *  Windows主机空闲内存量（单位：字节，1GB=1024*1024*1024Bytes）
     */
    public static final String WINDOWS_OS_PHYSICAL_MEMORY_FREE_BYTES = "windows_os_physical_memory_free_bytes";

    /**
     *  Windows主机空闲CPU量（单位：GHZ）
     */
    public static final String WINDOWS_CPU_TIME_TOTAL_IDLE = "sum by (instance) (irate(windows_cpu_time_total{mode=\"idle\"}[1m]))";

    /**
     * Windows主机CPU量--按模式（单位：GHZ）
     */
     public static final String WINDOWS_CPU_TIME_TOTAL_MODE = "sum by (mode) (irate(windows_cpu_time_total[1m]))";


    /**
     * Windows主机空闲GPU量（单位：GB）
     */
    public static final String WINDOWS_GPU_MEMORY_FREE = "memory_free";

    /**
     * Cluster CPU usage %
     */
    public static final String CLUSTER_CPU_USAGE = "100*(1-sum by(instance)(increase(node_cpu_seconds_total{mode=\"idle\",instance=\"{nodeName}\"}[1m]))/sum by(instance)(increase(node_cpu_seconds_total{instance=\"{nodeName}\"}[1m])))";
//    public static final String CLUSTER_CPU_USAGE = "sum (rate (container_cpu_usage_seconds_total{id=\"/\",kubernetes_io_hostname=~\"^{nodeName}.*$\"}[30s])) / sum (machine_cpu_cores{kubernetes_io_hostname=~\"^{nodeName}.*$\"}) * 100";

    /**
     * Cluster GPU usage %
     */
    public static final String CLUSTER_GPU_USAGE = "sum by(node)(increase(container_gpu_utilization{gpu=\"gpu0\",node=\"{nodeName}\"}[1m]))";

    /**
     * Cluster GPU memory usage %
     */
    public static final String CLUSTER_GPU_MEMORY_USAGE = "sum(container_gpu_memory_total{node=\"{nodeName}\"})/sum(container_request_gpu_memory{node=\"{nodeName}\"})";


    /**
     * Cluster memory usage %
     */
    public static final String CLUSTER_MEMORY_USAGE = "(node_memory_MemTotal_bytes%7Binstance%3D%22{nodeName}%22%7D-(node_memory_MemFree_bytes%7Binstance%3D%22{nodeName}%22%7D%2Bnode_memory_Cached_bytes%7Binstance%3D%22{nodeName}%22%7D%2Bnode_memory_Buffers_bytes%7Binstance%3D%22{nodeName}%22%7D))%2Fnode_memory_MemTotal_bytes%7Binstance%3D%22{nodeName}%22%7D*100";
//    public static final String CLUSTER_MEMORY_USAGE = "sum (container_memory_working_set_bytes{id=\"/\",kubernetes_io_hostname=~\"^{nodeName}.*$\"}) / sum (machine_memory_bytes{kubernetes_io_hostname=~\"^{nodeName}.*$\"}) * 100";

    /**
     * Cluster memory free %
     */
    public static final String CLUSTER_MEMORY_FREE = "node_memory_MemAvailable_bytes{instance=\"{nodeName}\"}/node_memory_MemTotal_bytes{instance=\"{nodeName}\"}*100";


    /**
     * Cluster network transmit usage %
     */
    public static final String CLUSTER_NETWORK_TRANSMIT_USAGE = "(sum(rate(node_network_transmit_bytes_total{instance=\"{nodeName}\"}[1m]))*8)/(1024*1024)";

    /**
     * Cluster network receive usage %
     */
    public static final String CLUSTER_NETWORK_RECEIVE_USAGE = "(sum(rate(node_network_receive_bytes_total{instance=\"{nodeName}\"}[1m]))*8)/(1024*1024)";

}
