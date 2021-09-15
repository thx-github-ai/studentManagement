package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Classname RefTeacherClass
 * @Description 老师管理班级表
 * @Date 2021/9/7 18:49
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefTeacherClass {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    @TableField(exist = false)
    private List<String> className;
}
