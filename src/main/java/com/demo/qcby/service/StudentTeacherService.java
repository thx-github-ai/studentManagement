package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.RefTeacherClass;
import com.demo.qcby.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
 * @Classname TeacherService
 * @Description 教师 service 层接口
 * @Date 2021/9/7 16:34
 * @Created by thx
 */
public interface StudentTeacherService extends IService<SysUser> {
    IPage<SysUser> listAll(Integer pageNo, Integer pageSize, String userName, String realName,
                           Integer sex, Date admissionDate, String className, String instituteName,
                           String majorName, Long roleId,Long teacherId);

    ResultJson deleteStudentOrTeacher(List<Long> idList);

    ResultJson insertStudentOrTeacher(SysUser user, Long roleId);

    ResultJson updateStudentOrTeacher(SysUser user);

    ResultJson<RefTeacherClass> listAllTc(String teacherName, String className);

    ResultJson updateTc(List<Long> classIdList, Long teacherId);
}
