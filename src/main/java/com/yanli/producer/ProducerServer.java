package com.yanli.producer;


/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/12 2:06 下午
 */
public interface ProducerServer {

    void send(String topic, String str,int count);
    void sendJson(String topic,String json,int count);
}
