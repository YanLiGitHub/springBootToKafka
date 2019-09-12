package com.yanli.producer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanli.producer.ProducerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;




/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/12 2:09 下午
 */
@Service
public class ProducerServerImpl implements ProducerServer {

    private static final Logger logger = LoggerFactory.getLogger(ProducerServerImpl.class);

    @Autowired
    private KafkaTemplate template;

    @Override
    public void send(String topic, String str, int count) {
        for (int i=0;i<count;i++){
            template.send(topic,str);
        }
    }

    @Override
    public void sendJson(String topic, String json, int count) {
        for (int i = 0;i < count; i++){
            sendJson(topic,json);
        }
    }


    private void sendJson(String topic,String json){
        JSONObject jsonObj = JSON.parseObject(json);

        jsonObj.put("topic",topic);
        jsonObj.put("ts",System.currentTimeMillis()+"");

        logger.info("json+++++++++++++++++++++  message = {}", jsonObj.toJSONString());

        ListenableFuture<SendResult<String,String>> future = template.send(topic, jsonObj.toJSONString());

        future.addCallback(new ListenableFutureCallback<SendResult<String,String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("msg send faild.",throwable);
            }

            @Override
            public void onSuccess(SendResult<String,String> stringStringSendResult) {
                logger.info("msg : OK "+ stringStringSendResult.toString());
            }
        });
    }
}
