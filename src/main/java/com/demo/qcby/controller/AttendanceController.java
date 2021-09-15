package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.service.AttendanceService;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname AttendanceController
 * @Description 考勤管理
 * @Date 2021/9/6 14:15
 * @Created by thx
 */
@RestController
@SelfLog
@RequestMapping("attendance")
@Validated
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;

    @Resource
    private HttpServletRequest request;

    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "attendance")
    @RequestMapping("listAll")
    @PreAuth("attendance:listAll")
    public ResultJson listAllAttendance(@NotNull Integer pageNo, @NotNull Integer pageSize, String className, String
                                        studentUserName, String teacherUserName, String lessonName, Integer status) {
        Long userId = Context.getUser(request.getHeader("token")).getId();
        return ResultJson.success(PageWeb.build(attendanceService.listAllAttendance(pageNo, pageSize, className,
                studentUserName, lessonName, teacherUserName, status, userId)));
    }

    @PreAuth("attendance:studentSignIn")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "attendance")
    @RequestMapping("studentSignIn")
    public ResultJson studentSignIn(@NotNull Long lessonId) {
        return attendanceService.studentSignIn(lessonId, Context.getUser(request.getHeader("token")).getId());
    }

}
