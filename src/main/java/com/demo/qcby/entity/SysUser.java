package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Classname User
 * @Description 用户表
 * @Date 2021/9/5 10:18
 * @Created by thx
 */
@Data
@TableName("sys_user")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String realName;
    private String password;
    private String userName;
    private Integer sex;
    private String photo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date admissionDate;
    private String email;
    private Long studentClassId;
    private Long instituteId;
    private Long majorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
//  令牌
    @TableField(exist = false)
    private String token;
//    权限字符串
    @TableField(exist = false)
    private List<String> authList;

    @TableField(exist = false)
    private String verCode;

}
