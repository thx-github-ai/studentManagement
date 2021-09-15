package com.demo.qcby.service.impl;

import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.mapper.LoginMapper;
import com.demo.qcby.service.LoginService;
import com.demo.qcby.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname LoginServiceImpl
 * @Description 登录 service 实现类
 * @Date 2021/9/5 10:57
 * @Created by thx
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;

    /**
     * 判断用户是否存在，如果存在，判断是否密码相同
     * @param userName
     * @param password
     * @return
     */
    @Override
    public ResultJson<SysUser> judgeUserExist(String userName, String password) {
        if (StringUtil.isOrNotEmpty(userName) || StringUtil.isOrNotEmpty(password)) {
            return ResultJson.failure("用户名或密码不能为空", null);
        }
        SysUser user = loginMapper.judgeUserExist(userName);
        if (user == null) {
            return ResultJson.failure("用户不存在", null);
        }
        if (!password.equals(user.getPassword())) {
            return ResultJson.failure("密码不正确", null);
        }
        return ResultJson.success("登陆成功", user);

    }

    /**
     * 获取权限字符串
     * @param id
     * @return
     */
    @Override
    public List<String> getAuthPath(Long id) {
        return loginMapper.getAuthPath(id);
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param
     * @return
     */
    @Override
    @Transactional
    public ResultJson updatePassword(String oldPassword, String newPassword, String confirmPassword, SysUser user) {
        if (StringUtil.isOrNotEmpty(oldPassword) || StringUtil.isOrNotEmpty(newPassword) || StringUtil.isOrNotEmpty(confirmPassword)) {
            return ResultJson.failure("输入的旧密码和新密码不能为空", null);
        }
        if (!newPassword.equals(confirmPassword)) {
            return ResultJson.failure("两次输入的密码不正确", null);
        }
        if (!oldPassword.equals(user.getPassword())) {
            return ResultJson.failure("旧密码不正确！", null);
        }
        if (oldPassword.equals(newPassword)) {
            return ResultJson.failure("新密码不能与旧密码一致", null);
        }
        int count = loginMapper.updatePassword(user.getId(), newPassword);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改密码成功", null);
    }

    /**
     * 修改个人信息
     * @param sex
     * @param email
     * @param realName
     * @param photo
     * @param user
     * @return
     */
    @Override
    @Transactional
    public ResultJson updatePersonalMessage(Integer sex, String email, String realName, String photo, SysUser user) {
        if (StringUtil.isOrNotEmpty(email) || StringUtil.isOrNotEmpty(realName) || StringUtil.isOrNotEmpty(photo)) {
            return ResultJson.failure("输入的选项不能为空", null);
        }
        if (sex != Constant.MAN && sex != Constant.WOMAN) {
            return ResultJson.failure("性别输入错误，请重新选择", null);
        }
        int count = loginMapper.updatePersonalMessage(sex, email, realName, photo, user.getId());
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改成功", null);
    }

    @Override
    @Transactional

    public ResultJson forgetPassword(SysUser user, String email, String verCode, String newPassword, String confirmPassword) {
        if (StringUtil.isOrNotEmpty(email) || StringUtil.isOrNotEmpty(verCode) || StringUtil.isOrNotEmpty(newPassword)) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        if (!verCode.equals(user.getVerCode())) {
            return ResultJson.failure("验证码错误！", null);
        }
        if (!newPassword.equals(confirmPassword)) {
            return ResultJson.failure("两次密码输入不一致", null);
        }
        int count = loginMapper.updatePassword(user.getId(), newPassword);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("设置新密码失败", null);
        }
        return ResultJson.success("密码重置成功！", null);
    }
}
