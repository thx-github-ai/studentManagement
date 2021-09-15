package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLeave;

import java.text.ParseException;
import java.util.Date;

/**
 * @Classname LeaveService
 * @Description
 * @Date 2021/9/10 18:49
 * @Created by thx
 */
public interface LeaveService extends IService<StudentLeave> {
    IPage<StudentLeave> listAllLeaveMsg(Integer pageNo, Integer pageSize,
                                        String studentName, String studentClassName,
                                        String startTime, String endTime, Integer status,
                                        String reason, String approveUserName, String noApprovalReason,
                                        Long studentId);

    ResultJson updateLeave(StudentLeave studentLeave);

    ResultJson studentLeave(String startTime, String endTime, String reason, Long token) throws ParseException;
}
