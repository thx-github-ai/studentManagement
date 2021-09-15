package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysRole;
import com.demo.qcby.mapper.RoleMapper;
import com.demo.qcby.service.RoleService;
import com.demo.qcby.util.StringUtil;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname RoleServiceImpl
 * @Description 角色的实现service
 * @Date 2021/9/6 15:20
 * @Created by thx
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    /**
     * 查询所有角色信息
     * @param pageNo
     * @param pageSize
     * @param roleName
     * @param description
     * @return
     */
    @Override
    public IPage<SysRole> listAll(Integer pageNo, Integer pageSize, String roleName, String description) {
        IPage<SysRole> page = new Page<>(pageNo, pageSize);
        return roleMapper.listAll(page, roleName, description);
    }

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @Override
    @Transactional
    public ResultJson updateRole(SysRole role) {
        if (role.getId() == null || StringUtil.isOrNotEmpty(role.getRoleName()) ||
                StringUtil.isOrNotEmpty(role.getDescription())) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = roleMapper.updateById(role);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改角色信息失败", null);
        }
        return ResultJson.success("修改角色信息成功", null);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    @Transactional
    public ResultJson insertRole(SysRole role) {
        if (StringUtil.isOrNotEmpty(role.getRoleName()) ||
                StringUtil.isOrNotEmpty(role.getDescription())) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = roleMapper.insert(role);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加角色失败", null);
        }
        return ResultJson.success("添加角色成功", null);
    }

    /**
     * 删除角色
     * @param idList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteRole(List<Long> idList) {
        if (idList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        roleMapper.deleteRole(idList);
        return ResultJson.success("删除成功", null);
    }

    /**
     * 设置角色访问权限，先删除所有，然后逐个插入
     * @param roleId
     * @param menuIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson setRoleMenu(Long roleId, List<Long> menuIdList) {
        if (roleId == null || menuIdList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        roleMapper.deleteAllRoleMenu(roleId);
        roleMapper.insertRoleMenu(roleId, menuIdList);
        return ResultJson.success("设置权限成功", null);
    }
}
