package com.demo.qcby.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ToEmail
 * @Description 邮件类
 * @Date 2021/9/7 12:13
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToEmail implements Serializable {
//    邮件接收方
    private String[] tos;
//    邮件主题
    private String subject;
//    邮件内容
    private String content;
}
