package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysRole;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.RoleService;
import com.demo.qcby.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname RoleController
 * @Description 角色管理
 * @Date 2021/9/6 14:00
 * @Created by thx
 */
@RestController
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Resource
    private RoleService roleService;

    @PreAuth("role:listAll")
    @RequestMapping("listAll")
    public ResultJson<SysUser> listAll(Integer pageNo, Integer pageSize, String roleName, String description) {

        return ResultJson.success(PageWeb.build(roleService.listAll(pageNo, pageSize, roleName, description)));
    }
    @PreAuth("role:delete")
    @RequestMapping("delete")
    public ResultJson deleteRole(@RequestParam List<Long> idList) {
        return roleService.deleteRole(idList);
    }

    @PreAuth("role:insert")
    @RequestMapping("insert")
    public ResultJson insertRole(SysRole role) {
        return roleService.insertRole(role);
    }

    @PreAuth("role:update")
    @RequestMapping("update")
    public ResultJson updateRole(SysRole role) {
        return roleService.updateRole(role);
    }

    @PreAuth("role:setRoleMenu")
    @RequestMapping("setMenu")
    public ResultJson setRoleMenu(Long roleId, @RequestParam List<Long> menuIdList) {
        return roleService.setRoleMenu(roleId, menuIdList);
    }

}
