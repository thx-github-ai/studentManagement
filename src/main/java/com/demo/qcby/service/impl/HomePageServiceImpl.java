package com.demo.qcby.service.impl;

import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.mapper.HomePageMapper;
import com.demo.qcby.service.HomePageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname HomePageServiceImpl
 * @Description 菜单树实现
 * @Date 2021/9/5 14:59
 * @Created by thx
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Resource
    private HomePageMapper homePageMapper;
//    菜单树实现
    @Override
    public ResultJson<SysMenu> menuTree(HttpServletRequest request) {
        String token = request.getHeader("token");
//        获取用户对象
        SysUser user = Context.getUser(token);
//       获取该用户的权限集合
        List<SysMenu> menus = homePageMapper.getMenuList(user.getId());
        List<SysMenu> results = getTreeMenu(menus);
        return ResultJson.success(results);
    }

    /**
     * 获取菜单树
     * @param menus
     * @return
     */
    private List<SysMenu> getTreeMenu(List<SysMenu> menus) {
        List<SysMenu> parents = new ArrayList<>();
        for (SysMenu sysMenu : menus) {
            if (sysMenu.getMenuParentId() == 0) {
                parents.add(sysMenu);
            }
        }
        for (SysMenu parent : parents) {
            childMenu(parent, menus);
        }
        return parents;
    }

    /**
     * 获取子菜单
     * @param parent
     * @param menus
     */
    private void childMenu(SysMenu parent, List<SysMenu> menus) {
        for (SysMenu menu : menus) {
            if (menu.getMenuParentId().equals(parent.getId())) {
                parent.getChildMenu().add(menu);
                childMenu(menu, menus);
            }
        }
    }
}
