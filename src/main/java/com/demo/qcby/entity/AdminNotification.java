package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Classname AdminNotification
 * @Description 通知实体类
 * @Date 2021/9/9 10:07
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class AdminNotification {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    private String content;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date releaseTime;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date updateTime;

}
