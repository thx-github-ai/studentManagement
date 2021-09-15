package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLessonAttendance;

/**
 * @Classname AttendanceService
 * @Description 考勤管理 service 接口
 * @Date 2021/9/10 15:09
 * @Created by thx
 */
public interface AttendanceService extends IService<StudentLessonAttendance> {
    IPage<StudentLessonAttendance> listAllAttendance(Integer pageNo, Integer pageSize, String className,
                                                     String studentUserName, String lessonName, String teacherUserName,
                                                     Integer status, Long userId);

    ResultJson studentSignIn(Long lessonId, Long studentUserId);
}
