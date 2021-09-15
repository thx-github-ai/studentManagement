package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLessonAttendance;
import com.demo.qcby.mapper.AttendanceMapper;
import com.demo.qcby.mapper.StudentTeacherMapper;
import com.demo.qcby.service.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Classname AttendanceServiceImpl
 * @Description 考勤管理 实现类
 * @Date 2021/9/10 15:10
 * @Created by thx
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, StudentLessonAttendance>
        implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private StudentTeacherMapper studentTeacherMapper;
//    查看考勤状态
    @Override
    public IPage<StudentLessonAttendance> listAllAttendance(Integer pageNo, Integer pageSize, String className,
                                                            String studentUserName, String lessonName,
                                                            String teacherUserName,
                                                            Integer status, Long userId) {
        IPage<StudentLessonAttendance> page = new Page<>(pageNo, pageSize);
        if (studentTeacherMapper.findStudent(userId, Constant.STUDENT_ID) == null) {
            userId = null;
        }
        return attendanceMapper.listAllAttendance(page, className, studentUserName, teacherUserName, lessonName, status
        , userId);
    }
//  学生签到
    @Override
    public ResultJson studentSignIn(Long lessonId, Long studentUserId) {
        int count = attendanceMapper.studentSignIn(lessonId, studentUserId, Constant.ATTENDANCE_STATE_COMPLETE ,new Date());
        return ResultJson.success("签到成功！");
    }
}
