package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Classname StudentLessonAttendance
 * @Description 考勤表
 * @Date 2021/9/5 10:50
 * @Created by thx
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@Accessors(chain = true)
public class StudentLessonAttendance {
    @TableId(type = IdType.AUTO)
    @NotNull
    private Long id;
    @NotNull
    private Long classId;
    @NotNull
    private Long studentUserId;
    @NotNull
    private Long teacherUserId;
    @NotNull
    private Long lessonId;
    @NotNull
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String className;

    @TableField(exist = false)
    private String subject;

}
