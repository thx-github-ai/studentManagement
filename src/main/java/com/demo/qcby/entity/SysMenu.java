package com.demo.qcby.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname Menu
 * @Description 菜单实体类
 * @Date 2021/9/5 10:27
 * @Created by thx
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class SysMenu {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String menuName;
    private String menuPermission;
    private String menuPath;
    private Long menuParentId;
    private Integer menuType;
    private Integer orderNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
//    不映射数据库字段
    @TableField(exist = false)
    private List<SysMenu> childMenu = new ArrayList<>();
}
