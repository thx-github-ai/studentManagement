package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.StudentLessonAttendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Classname AttendanceMapper
 * @Description 考勤管理的接口
 * @Date 2021/9/10 15:11
 * @Created by thx
 */
public interface AttendanceMapper extends BaseMapper<StudentLessonAttendance> {
    IPage<StudentLessonAttendance> listAllAttendance(IPage<StudentLessonAttendance> page,
                                                     @Param("className") String className,
                                                     @Param("studentUserName") String studentUserName,
                                                     @Param("lessonName") String lessonName,
                                                     @Param("teacherUserName") String teacherUserName,
                                                     @Param("status") Integer status,
                                                     @Param("userId") Long userId);

    Integer studentSignIn(@Param("lessonId") Long lessonId, @Param("studentUserId") Long studentUserId, @Param("status")
                            Integer status,
                          @Param("updateTime") Date date);
}
