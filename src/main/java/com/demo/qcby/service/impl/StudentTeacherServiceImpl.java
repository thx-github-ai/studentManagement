package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.RefTeacherClass;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.mapper.StudentTeacherMapper;
import com.demo.qcby.service.StudentTeacherService;
import com.demo.qcby.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Ref;
import java.util.Date;
import java.util.List;

/**
 * @Classname TeacherServiceImpl
 * @Description 教师实现类
 * @Date 2021/9/7 16:35
 * @Created by thx
 */
@Service
public class StudentTeacherServiceImpl extends ServiceImpl<StudentTeacherMapper, SysUser> implements StudentTeacherService {
    @Resource
    private StudentTeacherMapper studentTeacherMapper;

    @Override
    public IPage<SysUser> listAll(Integer pageNo, Integer pageSize, String userName,
                                  String realName, Integer sex, Date admissionDate,
                                  String className, String instituteName, String majorName, Long roleId, Long teacherId) {
        IPage<SysUser> page = new Page<>(pageNo, pageSize);

        if (studentTeacherMapper.findTeacher(teacherId, Constant.TEACHER_ID) == null) {
            teacherId = null;
        }
        return studentTeacherMapper.listAll(page, userName, realName, sex, admissionDate, className, instituteName,
                majorName, roleId, teacherId);
    }

    @Override
    @Transactional
    public ResultJson deleteStudentOrTeacher(List<Long> idList) {
        if (idList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        studentTeacherMapper.deleteAll(idList);
        return ResultJson.success("删除成功", null);
    }

    @Override
    @Transactional
    public ResultJson insertStudentOrTeacher(SysUser user, Long roleId) {
        if (StringUtil.isOrNotEmpty(user.getUserName()) || StringUtil.isOrNotEmpty(user.getPassword()) ||
                StringUtil.isOrNotEmpty(user.getEmail()) || StringUtil.isOrNotEmpty(user.getRealName()) ||
                user.getSex() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = studentTeacherMapper.insert(user);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }
        Long userId = user.getId();
        studentTeacherMapper.insertRole(userId, roleId);
        return ResultJson.success("添加成功", null);
    }

    @Override
    @Transactional
    public ResultJson updateStudentOrTeacher(SysUser user) {
        if (user.getId() == null || StringUtil.isOrNotEmpty(user.getUserName()) ||
                StringUtil.isOrNotEmpty(user.getPassword()) ||
                StringUtil.isOrNotEmpty(user.getEmail()) ||
                StringUtil.isOrNotEmpty(user.getRealName()) ||
                user.getSex() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = studentTeacherMapper.updateById(user);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改成功", null);
    }

    @Override
    public ResultJson<RefTeacherClass> listAllTc(String teacherName, String className) {
//        先模糊查找，看符合条件的老师，选择出老师 id，然后
        Long teacherId = Constant.TEACHER_ID;
        List<RefTeacherClass> teachers = studentTeacherMapper.selectTeacher(teacherId, teacherName);

        for (RefTeacherClass teacher : teachers) {
            List<String> classes = studentTeacherMapper.selectTeacherClass(teacher.getId(), className);
            teacher.setClassName(classes);
        }
        return ResultJson.success(teachers);

    }

    @Override
    @Transactional
    public ResultJson updateTc(List<Long> classIdList, Long teacherId) {
        if (classIdList == null || teacherId == null) {
            return ResultJson.failure("教师 id 和班级 id 不能为空", null);
        }
        studentTeacherMapper.deleteTc(teacherId);

        studentTeacherMapper.insertClass(teacherId, classIdList);
        return ResultJson.success("分配成功", null);
    }
}
