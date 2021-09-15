package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLesson;

import java.util.Date;

/**
 * @Classname LessonService
 * @Description 上课 service 接口
 * @Date 2021/9/9 16:54
 * @Created by thx
 */
public interface LessonService extends IService<StudentLesson> {
    IPage<StudentLesson> listAllLesson(Integer pageNo, Integer pageSize, String teacherUserName, String subject,
                                       String className, String startTime, String endTime, Long userId);

    ResultJson insertLesson(StudentLesson studentLesson);

    ResultJson updateLesson(StudentLesson studentLesson);

    ResultJson startCourse(Long teacherId, String subject, Long classId, Date startTime, Date endTime);
}
