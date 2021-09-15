package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.constant.GlobalConstant;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicMajorInfo;
import com.demo.qcby.entity.RefTeacherClass;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.MajorService;
import com.demo.qcby.service.StudentTeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Ref;
import java.util.Date;
import java.util.List;

import static com.demo.qcby.common.web.ResultJson.success;

/**
 * @Classname TeacherController
 * @Description 老师管理类
 * @Date 2021/9/6 14:07
 * @Created by thx
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Resource
    private StudentTeacherService studentTeacherService;

    @SelfLog(name = "查看老师信息", module = "teacher")
    @PreAuth("teacher:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(Integer pageNo, Integer pageSize, String userName, String realName, Integer sex,
                              Date admissionDate, String className, String instituteName,
                              String majorName) {
        Long roleId = Constant.TEACHER_ID;
        return success(PageWeb.build(studentTeacherService.listAll(pageNo, pageSize,
                userName, realName, sex, admissionDate, className, instituteName, majorName, roleId, roleId)));
    }
    @SelfLog(name = "删除老师", module = "teacher",type = GlobalConstant.LOG_TYPE_DEL)
    @PreAuth("teacher:delete")
    @RequestMapping("delete")
    public ResultJson deleteMajor(@RequestParam List<Long> idList) {
        return studentTeacherService.deleteStudentOrTeacher(idList);
    }
    @SelfLog(name = "添加老师", module = "teacher",type = GlobalConstant.LOG_TYPE_ADD)
    @PreAuth("teacher:insert")
    @RequestMapping("insert")
    public ResultJson insertMajor(SysUser user) {
        Long roleId = Constant.TEACHER_ID;
        return studentTeacherService.insertStudentOrTeacher(user, roleId);
    }

    @PreAuth("teacher:update")
    @RequestMapping("update")
    public ResultJson updateMajor(SysUser user) {
        return studentTeacherService.updateStudentOrTeacher(user);
    }

    @PreAuth("teacher_class:listAll")
    @RequestMapping("listAllTc")
    public ResultJson<RefTeacherClass> listAllTc( String teacherName, String className) {
        return studentTeacherService.listAllTc(teacherName, className);
    }

    @PreAuth("teacher_class:update")
    @RequestMapping("updateTc")
    public ResultJson updateTc(@RequestParam List<Long> classIdList, Long teacherId) {
        return studentTeacherService.updateTc(classIdList, teacherId);
    }

}
