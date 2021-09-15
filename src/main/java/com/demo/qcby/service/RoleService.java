package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysRole;

import java.util.List;

/**
 * @Classname RoleService
 * @Description 角色service接口
 * @Date 2021/9/6 15:19
 * @Created by thx
 */
public interface RoleService extends IService<SysRole> {
    IPage<SysRole> listAll(Integer pageNo, Integer pageSize, String roleName, String description);

    ResultJson updateRole(SysRole role);

    ResultJson insertRole(SysRole role);

    ResultJson deleteRole(List<Long> idList);

    ResultJson setRoleMenu(Long roleId, List<Long> menuIdList);
}
