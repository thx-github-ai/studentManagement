package com.demo.qcby.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsConfigProperties
 * @Description 测试用的
 * @Date 2021/9/8 10:38
 * @Created by thx
 */
@Data
public class SMSParameter {

    private int appId = 1400569316;

    private String appKey = "bd9c5ed23c04519942538912a568405b";

    private String[] phoneNumbers;

    private int templateId = 1112897;

    private String smsSign = "她与虚空皆是空";
}
