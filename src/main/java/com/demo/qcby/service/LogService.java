package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.entity.SysLoginLog;
import com.demo.qcby.entity.SysOperateLog;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Classname LogService
 * @Description 日志的 service 接口
 * @Date 2021/9/6 18:47
 * @Created by thx
 */
public interface LogService extends IService<SysOperateLog> {
    IPage<SysOperateLog> operateLog(Integer pageNo, Integer pageSize, String userName, Date operateTime, String operateModel, String operateType);

    IPage<SysLoginLog> loginLog(Integer pageNo, Integer pageSize, String userName, String loginIp);

    Integer insertOperateLog(SysOperateLog operateLog);

    void insertLoginLog(String userName) throws UnknownHostException;
}
