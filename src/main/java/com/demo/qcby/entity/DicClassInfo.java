package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname DicClassInfo
 * @Description 班级表
 * @Date 2021/9/5 10:31
 * @Created by thx
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class DicClassInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer period;
    private String className;
    private Long majorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
