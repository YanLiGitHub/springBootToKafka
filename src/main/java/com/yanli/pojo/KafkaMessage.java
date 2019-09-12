package com.yanli.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author YanLi
 * @version 1.0
 * @date 2019/9/12 2:04 下午
 */

@Data
public class KafkaMessage {
    private Long id;

    private String msg;

    private Date sendTime;

}
