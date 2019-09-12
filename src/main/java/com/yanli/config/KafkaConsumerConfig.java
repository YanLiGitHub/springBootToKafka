package com.yanli.config;

import com.yanli.consumer.DownloadPDFKafkaListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/11 5:26 下午
 */

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Autowired
    PropsConfig propsConfig;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public Map<String,Object> consumerConfig() {
        Map<String, Object> propMap = new HashMap<>();
        propMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,propsConfig.getBroker());
        propMap.put(ConsumerConfig.GROUP_ID_CONFIG,propsConfig.getGroupId());
        propMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,propsConfig.getEnableAutoCommit());
        propMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,propsConfig.getAutoOffsetReset());
        propMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        propMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,propsConfig.getAutoCommitInterval());

        return propMap;
    }

    @Bean
    public DownloadPDFKafkaListener listener(){
        return new DownloadPDFKafkaListener();
    }
}
