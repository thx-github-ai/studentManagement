package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.StudentLesson;
import com.demo.qcby.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Classname LessonController
 * @Description 上课管理
 * @Date 2021/9/6 14:14
 * @Created by thx
 */
@RestController
@RequestMapping("lesson")
@Slf4j
@Validated
public class LessonController {
    @Resource
    private LessonService lessonService;

    @Resource
    private HttpServletRequest request;
    @PreAuth("lesson:listAll")
    @SelfLog(type = GlobalConstant.LOG_TYPE_SELECT, module = "lesson")
    @RequestMapping("listAll")
    public ResultJson listAllLesson(@NotNull Integer pageNo, @NotNull Integer pageSize, String teacherUserName,
                                    String subject, String className, String startTime, String endTime) {
        Long userId = Context.getUser(request.getHeader("token")).getId();
        return ResultJson.success(PageWeb.build(lessonService.listAllLesson(pageNo, pageSize, teacherUserName,
                subject, className, startTime, endTime, userId)));
    }

    @PreAuth("lesson:insert")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "lesson")
    @RequestMapping("insert")
    public ResultJson insertLesson(@Validated StudentLesson studentLesson) {
        return lessonService.insertLesson(studentLesson);
    }

    @PreAuth("lesson:update")
    @SelfLog(type = GlobalConstant.LOG_TYPE_UPDATE, module = "lesson")
    @RequestMapping("update")
    public ResultJson updateLesson(StudentLesson studentLesson) {
        return lessonService.updateLesson(studentLesson);
    }

    @PreAuth("lesson:startCourse")
    @SelfLog(type = GlobalConstant.LOG_TYPE_ADD, module = "lesson")
    @RequestMapping("startCourse")
    public ResultJson startCourse(@NotBlank String subject, @NotNull Long classId, @NotNull String startTime,
                                  @NotNull String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long teacherId = Context.getUser(request.getHeader("token")).getId();
        return lessonService.startCourse(teacherId, subject, classId, sdf.parse(startTime), sdf.parse(endTime));
    }

}
