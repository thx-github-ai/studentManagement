package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.SysLoginLog;
import com.demo.qcby.entity.SysOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Classname LogMapper
 * @Description 日志的mapper接口
 * @Date 2021/9/6 18:48
 * @Created by thx
 */
public interface LogMapper extends BaseMapper<SysOperateLog> {
    IPage<SysOperateLog> operateLog(IPage<SysOperateLog> page, @Param("userName") String userName,
                                    @Param("operateTime") Date operateTime, @Param("operateModel") String operateModel,
                                    @Param("operateType") String operateType);

    IPage<SysLoginLog> loginLog(IPage<SysLoginLog> page, @Param("userName") String userName,
                                @Param("loginIp") String loginIp);

    int insertLoginLog(@Param("sysLoginLog") SysLoginLog sysLoginLog);
}
