package com.woodpecker.config;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.woodpecker.rocket.mq.MProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ 配置
 *
 * @author Relax
 * @since 2017年03月30日
 */
@Configuration
class RocketMQConfiguration {

    @Value("${mq.producerGroup}")
    private String producerGroup;
    @Value("${mq.instanceName}")
    private String instanceName;
    @Value("${mq.nameServer}")
    private String nameServer;

    @Bean(name = "mq-producer")
    public DefaultMQProducer producer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(producerGroup);
        producer.setInstanceName(instanceName);
        producer.setNamesrvAddr(nameServer);
        return producer;
    }

    @Bean
    public MProducer mProducer() {
        return new MProducer();
    }
}
