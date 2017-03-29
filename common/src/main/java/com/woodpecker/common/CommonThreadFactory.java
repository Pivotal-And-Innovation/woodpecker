package com.woodpecker.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂：可控的线程名字和线程守护性
 *
 * @author Glenn
 * @since 2017-03-25
 */
@SuppressWarnings("unused")
public class CommonThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUM = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger THREAD_NUM = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean daemon;

    public CommonThreadFactory() {
        this("pool-" + POOL_NUM.getAndIncrement(), false);
    }

    public CommonThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }

    public CommonThreadFactory(String namePrefix, boolean daemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix + "-thread-";
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r,
                namePrefix + THREAD_NUM.getAndIncrement(),
                0);
        thread.setDaemon(daemon);
        if (thread.getPriority() != Thread.NORM_PRIORITY)
            thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }

    public ThreadGroup getGroup() {
        return group;
    }

}
