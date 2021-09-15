package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLeave;
import com.demo.qcby.mapper.LeaveMapper;
import com.demo.qcby.mapper.StudentTeacherMapper;
import com.demo.qcby.service.LeaveService;
import org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname StudentLeaveImpl
 * @Description 请假实现类‘
 * @Date 2021/9/10 18:50
 * @Created by thx
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, StudentLeave> implements LeaveService {

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private StudentTeacherMapper studentTeacherMapper;
//    查看请假信息
    @Override
    public IPage<StudentLeave> listAllLeaveMsg(Integer pageNo, Integer pageSize, String studentName,
                                               String studentClassName, String startTime, String endTime,
                                               Integer status, String reason, String approveUserName,
                                               String noApprovalReason, Long studentId) {
        IPage<StudentLeave> page = new Page<>(pageNo, pageSize);
        if (studentTeacherMapper.findStudent(studentId, Constant.STUDENT_ID) == null) {
            studentId = null;
        }
        return leaveMapper.listAllLeaveMsg(page, studentName, studentClassName, startTime, endTime, status, reason,
                approveUserName, noApprovalReason, studentId);
    }

    @Override
    @Transactional
    public ResultJson updateLeave(StudentLeave studentLeave) {
        int count = leaveMapper.updateById(studentLeave);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("批假失败", null);
        }
        return ResultJson.success("假条审批成功！", null);

    }

//    学生请假
    @Override
    @Transactional
    public ResultJson studentLeave(String startTime, String endTime, String reason, Long studentId) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime1 = sdf.parse(startTime);
        Date endTime1 = sdf.parse(endTime);
        StudentLeave leave = new StudentLeave();
        leave.setStartTime(startTime1);
        leave.setEndTime(endTime1);
        leave.setReason(reason);
        leave.setStatus(Constant.LEAVE_STATUS_UNCHECK);
        leave.setStudentUserId(studentId);
        leave.setCreateTime(new Date());
        leaveMapper.insert(leave);
        return ResultJson.success("请假成功！", null);

    }
}
