package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description 用户的 mapper 层
 * @Date 2021/9/5 10:17
 * @Created by thx
 */

public interface UserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> listAll(IPage<SysUser> page, @Param("realName") String realName,
                           @Param("sex")Integer sex,  @Param("admissionDate")String admissionDate,
                           @Param("className")String className,  @Param("instituteName")String instituteName,
                           @Param("majorName")String majorName);

    Integer deleteUser(@Param("idList") List<Long> idList);

    Integer insertDefaultRole(@Param("id") Long id);

    Integer updateRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
