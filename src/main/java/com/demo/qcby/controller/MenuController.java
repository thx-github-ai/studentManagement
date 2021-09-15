package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.entity.SysRole;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.MenuService;
import com.demo.qcby.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname MenuController
 * @Description 菜单管理
 * @Date 2021/9/6 14:01
 * @Created by thx
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @PreAuth("menu:listAll")
    @RequestMapping("listAll")
    public ResultJson<SysUser> listAll(Integer pageNo, Integer pageSize, String menuName, String menuPermission) {
        return ResultJson.success(PageWeb.build(menuService.listAll(pageNo, pageSize, menuName, menuPermission)));
    }
    @PreAuth("menu:delete")
    @RequestMapping("delete")
    public ResultJson deleteMenu(@RequestParam List<Long> menuIdList) {
        return menuService.deleteMenu(menuIdList);
    }

    @PreAuth("menu:insert")
    @RequestMapping("insert")
    public ResultJson insertMenu(SysMenu menu) {
        return menuService.insertMenu(menu);
    }

    @PreAuth("menu:update")
    @RequestMapping("update")
    public ResultJson updateMenu(SysMenu menu) {
        return menuService.updateMenu(menu);
    }

}
