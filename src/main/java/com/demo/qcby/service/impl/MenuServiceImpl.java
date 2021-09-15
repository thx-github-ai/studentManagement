package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.mapper.MenuMapper;
import com.demo.qcby.service.MenuService;
import com.demo.qcby.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname MenuServiceImpl
 * @Description 菜单管理的实现类
 * @Date 2021/9/6 15:23
 * @Created by thx
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    /**
     * 查看菜单信息
     * @param pageNo
     * @param pageSize
     * @param menuName
     * @param menuPermission
     * @return
     */
    @Override
    public IPage<SysMenu> listAll(Integer pageNo, Integer pageSize, String menuName, String menuPermission) {
        IPage<SysMenu> page = new Page<>(pageNo, pageSize);
        return menuMapper.listAll(page, menuName, menuPermission);
    }

    /**
     * 删除菜单
     * @param menuIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteMenu(List<Long> menuIdList) {
        if (menuIdList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        menuMapper.deleteMenu(menuIdList);
        return ResultJson.success("删除菜单成功", null);
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @Override
    @Transactional
    public ResultJson insertMenu(SysMenu menu) {
        if (menu.getMenuParentId() == null || StringUtil.isOrNotEmpty(menu.getMenuPath()) ||
                StringUtil.isOrNotEmpty(menu.getMenuPermission()) || menu.getMenuType() == null ||
                menu.getOrderNum() == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = menuMapper.insert(menu);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加菜单失败", null);
        }
        return ResultJson.success("添加菜单成功", null);
    }

    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    @Override
    @Transactional
    public ResultJson updateMenu(SysMenu menu) {
        if (menu.getMenuParentId() == null || StringUtil.isOrNotEmpty(menu.getMenuPath()) ||
                StringUtil.isOrNotEmpty(menu.getMenuPermission()) || menu.getMenuType() == null ||
                menu.getOrderNum() == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = menuMapper.updateById(menu);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改菜单失败", null);
        }
        return ResultJson.success("修改菜单成功", null);
    }

}
