package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname DicInstituteInfo
 * @Description 学院表
 * @Date 2021/9/5 10:35
 * @Created by thx
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class DicInstituteInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String instituteName;
    private String instituteDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
