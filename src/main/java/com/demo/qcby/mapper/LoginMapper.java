package com.demo.qcby.mapper;

import com.demo.qcby.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname LoginMapper
 * @Description 登录的 mapper 接口
 * @Date 2021/9/5 10:57
 * @Created by thx
 */
public interface LoginMapper {
    SysUser judgeUserExist(@Param("userName") String userName);

    List<String> getAuthPath(@Param("id") Long id);

    Integer updatePassword(@Param("id") Long userId, @Param("newPassword") String newPassword);

    Integer updatePersonalMessage(@Param("sex") Integer sex, @Param("email") String email,
                                  @Param("realName") String realName, @Param("photo") String photo, @Param("id") Long id);
}
