package com.yanli.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/12 1:45 下午
 */
@RestController
public class TestController {

    private static final Logger logger= LoggerFactory.getLogger(TestController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @RequestMapping(value = "{topic}/send", method = RequestMethod.POST)
    public void sendMessegeTopic(@PathVariable String topic, @RequestParam( value ="partition",defaultValue = "0") int partition){
        logger.info("start send Messege to {}",topic);
        kafkaTemplate.send(topic,partition,"好","springboot kafka");

    }
}
