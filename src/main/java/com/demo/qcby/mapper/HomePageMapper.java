package com.demo.qcby.mapper;

import com.demo.qcby.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname HomePageMapper
 * @Description 菜单树 Mapper 层
 * @Date 2021/9/5 15:02
 * @Created by thx
 */

public interface HomePageMapper {
    List<SysMenu> getMenuList(@Param("id") Long id);
}
