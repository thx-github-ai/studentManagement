package com.demo.qcby.service;

import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;

import java.util.List;

/**
 * @Classname LoginService
 * @Description 登录 service 接口
 * @Date 2021/9/5 10:56
 * @Created by thx
 */
public interface LoginService {
//      判断用户是否存在
    ResultJson<SysUser> judgeUserExist(String userName, String password);
//      获得权限字符串
    List<String> getAuthPath(Long id);
//      修改密码
    ResultJson updatePassword(String oldPassword, String newPassword, String confirmPassword, SysUser user);
//      修改个人信息
    ResultJson updatePersonalMessage(Integer sex, String email, String realName, String photo, SysUser user);

    ResultJson forgetPassword(SysUser user, String email, String verCode, String newPassword, String confirmPassword);
}
