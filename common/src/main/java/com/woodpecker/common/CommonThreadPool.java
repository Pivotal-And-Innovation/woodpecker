package com.woodpecker.common;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

/**
 * 包装过的线程池
 *
 * @author Glenn
 * @since 2017-03-28
 */
public class CommonThreadPool {
    /**
     * 线程池核心大小
     */
    private int corePoolSize = 10;
    /**
     * 线程池队列大小
     */
    private int queueSize = 10;
    /**
     * 线程存活时间
     */
    private int keepAliveTime = 600;

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 项目启动时初始化线程池
     */
    @PostConstruct
    public void init() {
        threadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
        threadPoolExecutor.setKeepAliveTime(keepAliveTime, TimeUnit.SECONDS);
    }

    /**
     * 判断线程池是否可用
     *
     * @return if it's available it will return true, else will return false
     */
    public boolean available() {
        return threadPoolExecutor.getQueue().size() <= queueSize;
    }

    /**
     * 关闭线程池，不直接调用该方法或者在XML配置文件中不destroy，则可以在钩子函数中关闭，如下：
     *
     * <pre>{@code
     *     @Resource
     *     private ComboPooledDataSource dataSource;
     *     Runtime.getRuntime().addShutdownHook(() -> {
     *     dataSource.close();
     *     });
     *     }
     * </pre>
     */
    public void shutDown() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }

    /**
     * 线程池中添加任务
     *
     * @param task 任务 {@code Runnable}
     */
    public void addTask(Runnable task) {
        threadPoolExecutor.execute(task);
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

}
