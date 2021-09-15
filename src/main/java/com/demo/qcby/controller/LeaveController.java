package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLeave;
import com.demo.qcby.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Global;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname LeaveController
 * @Description 请假管理
 * @Date 2021/9/10 18:48
 * @Created by thx
 */
@RestController
@Slf4j
@Validated
@RequestMapping("leave")
public class LeaveController {
    @Resource
    private LeaveService leaveService;
    @Resource
    private HttpServletRequest request;

    @PreAuth("leave:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "leave")
    @RequestMapping("listAll")
    public ResultJson listAllLeaveMsg(@NotNull Integer pageNo, @NotNull Integer pageSize, String studentName, String studentClassName,
                                      String startTime, String endTime, Integer status, String reason,
                                      String approveUserName, String noApprovalReason) {
        return ResultJson.success(PageWeb.build(leaveService.listAllLeaveMsg(pageNo, pageSize, studentName, studentClassName, startTime, endTime,
                status, reason, approveUserName, noApprovalReason, Context.getUser(request.getHeader("token")).getId())));
    }

    @PreAuth("leave:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "leave")
    @RequestMapping("update")
    public ResultJson updateLeave(@Validated StudentLeave studentLeave) {
        return leaveService.updateLeave(studentLeave);
    }

    @PreAuth("leave:studentLeave")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "leave")
    @RequestMapping("studentLeave")
    public ResultJson studentLeave(@NotBlank String startTime, @NotBlank String endTime, @NotBlank String reason) throws ParseException {
        return leaveService.studentLeave(startTime, endTime, reason, Context.getUser(request.getHeader("token")).getId());
    }


}
