package com.demo.qcby.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname SysLoginLog
 * @Description 登录日志
 * @Date 2021/9/6 19:11
 * @Created by thx
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginLog {
    private Long id;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    private String loginIp;
}
