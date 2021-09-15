package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Classname LogController
 * @Description 日志管理
 * @Date 2021/9/6 14:02
 * @Created by thx
 */
@RestController
@RequestMapping("admin")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("loginLog")
    public ResultJson loginLog(Integer pageNo, Integer pageSize, String userName, String loginIp) {
        return ResultJson.success(PageWeb.build(logService.loginLog(pageNo, pageSize, userName, loginIp)));

    }
    @PreAuth("admin:operateLog")
    @RequestMapping("operateLog")
    public ResultJson operativeLog(Integer pageNo, Integer pageSize, String userName, Date operateTime,
                                   String operateModel, String operateType) {
        return ResultJson.success(PageWeb.build(logService.operateLog
                (pageNo, pageSize, userName, operateTime, operateModel, operateType)));
    }
}
