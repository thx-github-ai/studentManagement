package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.Target;
import java.util.Date;

/**
 * @Classname OperateLog
 * @Description 操作日志
 * @Date 2021/9/6 18:44
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class SysOperateLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
    private String operateModule;
    private String operateType;
}
