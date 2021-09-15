package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname RoleMapper
 * @Description 角色的Mapper
 * @Date 2021/9/6 15:21
 * @Created by thx
 */
public interface RoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> listAll(IPage<SysRole> page, @Param("roleName") String roleName,
                           @Param("description") String description);

    Integer deleteRole(@Param("idList") List<Long> idList);

    Integer deleteAllRoleMenu(@Param("roleId") Long roleId);

    Integer insertRoleMenu(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);
}
