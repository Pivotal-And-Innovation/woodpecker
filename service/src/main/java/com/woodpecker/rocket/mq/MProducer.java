package com.woodpecker.rocket.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.woodpecker.common.CommonThreadFactory;
import com.woodpecker.util.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

/**
 * 消息生产者:Demo
 *
 * @author Glenn
 * @since 2017-03-29
 */
public class MProducer {

    @Resource
    private DefaultMQProducer mqProducer;

    private static ScheduledExecutorService scheduledExecutorService;
    private static final AtomicLong msgTag = new AtomicLong(1);

    /**
     * constructor
     */
    public MProducer() {
        try {
            mqProducer.start();
            startMQThread();
            /*进程结束的时候清理线程池和消息生产者*/
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Logger.info("jvm is going to closed, start to shutDown the threadPool and MQProducer. and the total num of messages have " +
                        "been produced is " + msgTag.get() + ".");
                if (!scheduledExecutorService.isShutdown()) {
                    scheduledExecutorService.shutdown();
                }
                mqProducer.shutdown();
            }));
        } catch (Exception e) {
            Logger.error("Unknown Exception.", e);
        }

    }

    /**
     * the produce message thread
     */
    private void startMQThread() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new CommonThreadFactory("singleMQ", false));
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            /*构造参数分别对应Topic、Tag、key、message body, 每隔10秒生产5条信息*/
            for(int i = 0; i < 5; i++) {
                Message msg = new Message("Broker-A" + msgTag.get(), "TagA", "OrderID002", ("Hello MetaQ" + msgTag.get()).getBytes());
                try {
                    SendResult sendResult = mqProducer.send(msg);
                    Logger.info("send the message Okay.@id=" + sendResult.getMsgId() + ", @status=" + sendResult.getSendStatus() + ".");
                    msgTag.incrementAndGet();
                } catch (Exception e) {
                    Logger.error("failed to send the message, @Topic=" + msg.getTopic(), e);
                }
            }
        }, 5, 10, TimeUnit.SECONDS);
    }

}
