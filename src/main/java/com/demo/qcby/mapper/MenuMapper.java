package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname MenuMapper
 * @Description 菜单管理的Mapper
 * @Date 2021/9/6 15:22
 * @Created by thx
 */
public interface MenuMapper extends BaseMapper<SysMenu> {
    Integer deleteMenu(@Param("menuIdList") List<Long> menuIdList);

    IPage<SysMenu> listAll(IPage<SysMenu> page, @Param("menuName") String menuName,
                           @Param("menuPermission") String menuPermission);
}
