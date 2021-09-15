package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname UserController
 * @Description 用户管理类
 * @Date 2021/9/5 17:00
 * @Created by thx
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @SelfLog(name = "查看用户信息", module = "user", type = GlobalConstant.LOG_TYPE_SELECT)
    @PreAuth("user:listAll")
    @RequestMapping("listAll")
    public ResultJson<SysUser> listAll(Integer pageNo, Integer pageSize, String realName, Integer sex,
                                       String admissionDate, String className, String instituteName,
                                       String majorName) {

        return ResultJson.success(PageWeb.build(userService.listAll(pageNo, pageSize, realName, sex, admissionDate
                , className, instituteName, majorName)));
    }
    @SelfLog(name = "删除用户", module = "user", type = GlobalConstant.LOG_TYPE_DEL)
    @PreAuth("user:delete")
    @RequestMapping("delete")
    public ResultJson deleteUser(@RequestParam List<Long> idList) {
        return userService.deleteUser(idList);
    }

    @SelfLog(name = "添加用户", module = "user", type = GlobalConstant.LOG_TYPE_ADD)
    @PreAuth("user:insert")
    @RequestMapping("insert")
    public ResultJson insertUser(SysUser user) {
        return userService.insertUser(user);
    }

    @SelfLog(name = "更新用户信息", module = "user", type = GlobalConstant.LOG_TYPE_UPDATE)
    @PreAuth("user:update")
    @RequestMapping("update")
    public ResultJson updateUser(SysUser user) {
        return userService.updateUser(user);
    }

    @SelfLog(name = "给用户分配角色", module = "user", type = GlobalConstant.LOG_TYPE_UPDATE)
    @PreAuth("user:updateUserRole")
    @RequestMapping("updateUserRole")
    public ResultJson updateRole(Long userId, Long roleId) {
        return userService.updateUserRole(userId, roleId);
    }
}
