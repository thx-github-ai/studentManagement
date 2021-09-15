package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLesson;
import com.demo.qcby.mapper.LessonMapper;
import com.demo.qcby.mapper.StudentTeacherMapper;
import com.demo.qcby.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Classname LessonServiceImpl
 * @Description 上课 service 实现类
 * @Date 2021/9/9 16:54
 * @Created by thx
 */
@Service
@Slf4j
public class LessonServiceImpl extends ServiceImpl<LessonMapper, StudentLesson> implements LessonService {

    @Resource
    private LessonMapper lessonMapper;

    @Resource
    private StudentTeacherMapper studentTeacherMapper;

    @Override
    public IPage<StudentLesson> listAllLesson(Integer pageNo, Integer pageSize, String teacherUserName, String subject,
                                              String className, String startTime, String endTime, Long userId) {
        IPage<StudentLesson> page = new Page<>(pageNo, pageSize);
        Long teacherId = userId;
        Long studentId = userId;
        if (studentTeacherMapper.findTeacher(userId, Constant.TEACHER_ID) == null) {
            teacherId = null;
        }
        if (studentTeacherMapper.findStudent(userId, Constant.STUDENT_ID) == null) {
            studentId = null;
        }
        return lessonMapper.listAll(page, teacherUserName, subject, className, startTime, endTime, teacherId, studentId);
    }
    @Transactional
    @Override
    public ResultJson insertLesson(StudentLesson studentLesson) {
        studentLesson.setCreateTime(new Date());
        int count = lessonMapper.insert(studentLesson);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }
        return ResultJson.success("添加成功", null);
    }
    @Transactional
    @Override
    public ResultJson updateLesson(StudentLesson studentLesson) {
        studentLesson.setUpdateTime(new Date());
        int count = lessonMapper.updateById(studentLesson);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改成功", null);
    }

    @Override
    @Transactional
    public ResultJson startCourse(Long teacherId, String subject, Long classId, Date startTime, Date endTime) {
        StudentLesson lesson = new StudentLesson();
        lesson.setSubject(subject);
        lesson.setTeacherUserId(teacherId);
        lesson.setStartTime(startTime);
        lesson.setEndTime(endTime);
        lesson.setClassId(classId);
        lesson.setCreateTime(new Date());
//        首先添加课程
        int count = lessonMapper.insert(lesson);
        Long lessonId = lesson.getId();
//        获取到当前班级的所有学生，添加考勤记录
        List<Long> studentIdList = lessonMapper.getStudent(classId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createDate = sdf.format(date);
        lessonMapper.insertAttendance(classId, teacherId, studentIdList, lessonId, Constant.ATTENDANCE_STATE_UNCOMPLETED,
                createDate);

        return ResultJson.success("添加课程成功!");
    }
}
