package com.ilab.service.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanlh
 */
public class ILabThreadPool {
    private static final int NODE_NUM = 4;
    private static volatile ILabThreadPool iLabInstance;
    private static volatile ILabThreadPool[] nodeInstance = new ILabThreadPool[NODE_NUM];
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 30;
    private static final int KEEP_ALIVE_TIME = 1;
    private ThreadPoolExecutor iLabThreadPoolExecutor;
    private final ThreadPoolExecutor[] nodeThreadPoolExecutor = new ThreadPoolExecutor[NODE_NUM];

    private ILabThreadPool() {
        iLabThreadPoolExecutor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.HOURS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private ILabThreadPool(Integer nodeId) {
        nodeThreadPoolExecutor[nodeId] = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.HOURS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ILabThreadPool getInstance() {
        if (iLabInstance == null) {
            synchronized (ILabThreadPool.class) {
                if (iLabInstance == null) {
                    iLabInstance = new ILabThreadPool();
                }
            }
        }
        return iLabInstance;
    }

    public static ILabThreadPool getInstance(Integer nodeId) {
        if (nodeInstance[nodeId] == null) {
            synchronized (ILabThreadPool.class) {
                if (nodeInstance[nodeId] == null) {
                    nodeInstance[nodeId] = new ILabThreadPool(nodeId);
                }
            }
        }
        return nodeInstance[nodeId];
    }

    public void executeILabThreadPool(Runnable runnable) {
        iLabThreadPoolExecutor.execute(runnable);
    }

    public void submitILabThreadPool(Runnable runnable) {
        iLabThreadPoolExecutor.submit(runnable);
    }

    public void executeNodeThreadPool(Runnable runnable, Integer nodeId) {
        nodeThreadPoolExecutor[nodeId].execute(runnable);
    }

    public void submitNodeThreadPool(Runnable runnable, Integer nodeId) {
        nodeThreadPoolExecutor[nodeId].submit(runnable);
    }

}
