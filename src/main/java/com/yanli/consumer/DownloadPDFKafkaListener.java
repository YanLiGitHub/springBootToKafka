package com.yanli.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/11 5:46 下午
 */
@Slf4j
public class DownloadPDFKafkaListener {
    @KafkaListener(groupId = "test" , topics = "download_pdf_log")
    public void listenerOne(ConsumerRecord<String,String> data){
        log.info("消费者线程："+Thread.currentThread().getName()+"[ 消息 来自kafkatopic："+data.topic()+",分区："+data.partition()
                +" ，委托时间："+data.timestamp()+"]消息内容如下：");

        System.out.println(data.value());
    }
}
