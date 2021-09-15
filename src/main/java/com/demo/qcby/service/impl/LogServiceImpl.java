package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.entity.SysLoginLog;
import com.demo.qcby.entity.SysOperateLog;
import com.demo.qcby.mapper.LogMapper;
import com.demo.qcby.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Classname LogServiceImpl
 * @Description 日志接口实现类
 * @Date 2021/9/6 18:48
 * @Created by thx
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysOperateLog> implements LogService {
    @Resource
    private LogMapper logMapper;
    /**
     *
     * @param pageNo
     * @param pageSize
     * @param userName
     * @param operateTime
     * @param operateModel
     * @param operateType
     * @return
     */
    @Override
    public IPage<SysOperateLog> operateLog(Integer pageNo, Integer pageSize, String userName, Date operateTime,
                                           String operateModel, String operateType) {
        IPage<SysOperateLog> page = new Page<>(pageNo, pageSize);
        return logMapper.operateLog(page, userName, operateTime, operateModel, operateType);
    }

    @Override
    public IPage<SysLoginLog> loginLog(Integer pageNo, Integer pageSize, String userName, String loginIp) {
        IPage<SysLoginLog> page = new Page<>(pageNo, pageSize);
        return logMapper.loginLog(page, userName, loginIp);

    }

    /**
     * 添加操作记录
     * @param operateLog
     * @return
     */
    @Override
    public Integer insertOperateLog(SysOperateLog operateLog) {
        return logMapper.insert(operateLog);
    }

    /**
     * 添加登录记录
     * @param userName
     */
    @Override
    @Transactional
    public void insertLoginLog(String userName) throws UnknownHostException {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserName(userName);
//        设置本机地址
        sysLoginLog.setLoginIp(InetAddress.getLocalHost().getHostAddress());
        sysLoginLog.setLoginTime(new Date());
        int count = logMapper.insertLoginLog(sysLoginLog);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return;
        }

    }
}
