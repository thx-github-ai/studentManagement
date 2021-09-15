package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;

import java.util.List;

/**
 * @Classname MenuService
 * @Description 菜单管理的Service 接口
 * @Date 2021/9/6 15:23
 * @Created by thx
 */
public interface MenuService extends IService<SysMenu> {
    IPage<SysMenu> listAll(Integer pageNo, Integer pageSize, String menuName, String menuPermission);

    ResultJson deleteMenu(List<Long> menuIdList);

    ResultJson insertMenu(SysMenu menu);

    ResultJson updateMenu(SysMenu menu);
}
