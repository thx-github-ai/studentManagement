package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.StudentTeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Classname StudentController
 * @Description 学生管理
 * @Date 2021/9/6 14:06
 * @Created by thx
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentTeacherService studentTeacherService;

    @Resource
    private HttpServletRequest request;
    @PreAuth("student:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(Integer pageNo, Integer pageSize, String userName, String realName, Integer sex,
                              Date admissionDate, String className, String instituteName,
                              String majorName) {
        Long teacherId = Context.getUser(request.getHeader("token")).getId();
        Long roleId = Constant.STUDENT_ID;
        return ResultJson.success(PageWeb.build(studentTeacherService.listAll(pageNo, pageSize,
                userName, realName, sex, admissionDate, className, instituteName, majorName, roleId, teacherId)));
    }

    @PreAuth("student:delete")
    @RequestMapping("delete")
    public ResultJson deleteMajor(@RequestParam List<Long> idList) {
        return studentTeacherService.deleteStudentOrTeacher(idList);
    }

    @PreAuth("student:insert")
    @RequestMapping("insert")
    public ResultJson insertMajor(SysUser user) {
        Long roleId = Constant.STUDENT_ID;
        return studentTeacherService.insertStudentOrTeacher(user, roleId);
    }

    @PreAuth("student:update")
    @RequestMapping("update")
    public ResultJson updateMajor(SysUser user) {
        return studentTeacherService.updateStudentOrTeacher(user);
    }
}
