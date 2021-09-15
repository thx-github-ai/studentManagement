package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserService
 * @Description 用户service层接口
 * @Date 2021/9/5 10:15
 * @Created by thx
 */

public interface UserService extends IService<SysUser> {

    IPage<SysUser> listAll(Integer pageNo, Integer pageSize, String realName, Integer sex, String admissionDate,
                  String className, String instituteName, String majorName);

    ResultJson deleteUser(List<Long> idList);

    ResultJson insertUser(SysUser user);

    ResultJson updateUser(SysUser user);

    ResultJson updateUserRole(Long userId, Long roleId);
}
