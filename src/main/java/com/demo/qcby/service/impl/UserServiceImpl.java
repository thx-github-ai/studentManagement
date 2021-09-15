package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.mapper.UserMapper;
import com.demo.qcby.service.UserService;
import com.demo.qcby.util.StringUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Description 用户 service 实现类
 * @Date 2021/9/5 10:16
 * @Created by thx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 展示用户信息
     * @return
     */
    @Override
    public IPage<SysUser> listAll(Integer pageNo, Integer pageSize, String realName, Integer sex,
                                  String admissionDate, String className, String instituteName, String majorName) {
        IPage<SysUser> page = new Page<>(pageNo, pageSize);
        return userMapper.listAll(page, realName, sex, admissionDate, className, instituteName, majorName);
    }

    /**
     * 批量删除用户信息
     * @param idList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteUser(List<Long> idList) {
        if (idList == null) {
            return ResultJson.failure("删除的 id 不能为空!", null);
        }
        userMapper.deleteUser(idList);
        return ResultJson.success("删除成功", null);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    public ResultJson insertUser(SysUser user) {
        if (StringUtil.isOrNotEmpty(user.getUserName()) || StringUtil.isOrNotEmpty(user.getPassword()) ||
        StringUtil.isOrNotEmpty(user.getEmail()) || StringUtil.isOrNotEmpty(user.getRealName()) ||
                user.getSex() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = userMapper.insert(user);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }


        return ResultJson.success("添加成功", null);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    @Transactional
    public ResultJson updateUser(SysUser user) {
        if (StringUtil.isOrNotEmpty(user.getUserName()) || StringUtil.isOrNotEmpty(user.getPassword()) ||
                StringUtil.isOrNotEmpty(user.getEmail()) || StringUtil.isOrNotEmpty(user.getRealName()) ||
                user.getSex() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = userMapper.updateById(user);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败了", null);
        }
        return ResultJson.success("修改成功", null);
    }

    /**
     * 分配用户角色
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    @Transactional

    public ResultJson updateUserRole(Long userId, Long roleId) {
        if (userId == null || roleId == null) {
            return ResultJson.failure("用户 id 和角色 id 不能为空", null);
        }
        int count = userMapper.updateRole(userId, roleId);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("分配失败", null);
        }
        return ResultJson.success("分配成功", null);
    }
}
