package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.StudentLesson;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Classname LessonMapper
 * @Description 上课的mapper层
 * @Date 2021/9/9 16:53
 * @Created by thx
 */

public interface LessonMapper extends BaseMapper<StudentLesson> {
    IPage<StudentLesson> listAll(IPage<StudentLesson> page,
                                 @Param("teacherUserName") String teacherUserName,
                                 @Param("subject") String subject,
                                 @Param("className") String className,
                                 @Param("startTime") String startTime,
                                 @Param("endTime") String endTime,
                                 @Param("teacherId") Long teacherId,
                                 @Param("studentId") Long studentId);

    Integer insertAttendance(@Param("classId") Long classId, @Param("teacherId") Long teacherId,
                             @Param("studentIdList") List<Long> studentIdList, @Param("lessonId") Long lessonId,
                             @Param("status") Integer attendanceStateUncompleted, @Param("createTime") String date);

    List<Long> getStudent(@Param("classId") Long classId);
}
