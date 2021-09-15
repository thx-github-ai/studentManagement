package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.LogService;
import com.demo.qcby.service.LoginService;
import com.demo.qcby.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;

/**
 * @Classname LoginController
 * @Description 登录的 Controller 层
 * @Date 2021/9/5 10:07
 * @Created by thx
 */
@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private LogService logService;

    @RequestMapping("login")
    public ResultJson<SysUser> login(String userName, String password, HttpServletResponse response) throws UnknownHostException {
        ResultJson<SysUser> result = loginService.judgeUserExist(userName, password);
        if (result != null) {
            SysUser user = result.getResult();
            logService.insertLoginLog(user.getUserName());
            String token = JwtUtil.createToken(user.getId(), user.getUserName());
            user.setToken(token);
            Context.add(user);
            user.setAuthList(loginService.getAuthPath(user.getId()));
            response.setHeader("token", token);
            return result;
        } else {
            return result;
        }
    }

    @PreAuth("updatePassword")
    @RequestMapping("updatePassword")
    public ResultJson updatePassword(String oldPassword, String newPassword, String confirmPassword, HttpServletRequest request) {
        SysUser user = Context.getUser(request.getHeader("token"));
        return loginService.updatePassword(oldPassword, newPassword, confirmPassword, user);
    }

    @PreAuth("updatePersonalMessage")
    @RequestMapping("updatePersonalMessage")
    public ResultJson updatePersonalMessage(Integer sex, String email, String realName, String photo, HttpServletRequest request) {
        SysUser user = Context.getUser(request.getHeader("token"));
        return loginService.updatePersonalMessage(sex, email, realName, photo, user);
    }

    @PreAuth("forgetPassword")
    @RequestMapping("forgetPassword")
    public ResultJson forgetPassword(HttpServletRequest request, String email, String verCode, String newPassword, String confirmPassword) {
        SysUser user = Context.getUser(request.getHeader("token"));
        return loginService.forgetPassword(user, email, verCode, newPassword, confirmPassword);
    }






}
