package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.entity.RefTeacherClass;
import com.demo.qcby.entity.SysUser;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.sql.Ref;
import java.util.Date;
import java.util.List;

/**
 * @Classname StudentTeacherMapper
 * @Description 老师学生mapper
 * @Date 2021/9/7 17:03
 * @Created by thx
 */
public interface StudentTeacherMapper extends BaseMapper<SysUser> {

    IPage<SysUser> listAll(IPage<SysUser> page, @Param("userName") String userName,
                           @Param("realName") String realName, @Param("sex") Integer sex,
                           @Param("admissionDate") Date admissionDate,
                           @Param("className") String className,
                           @Param("instituteName") String instituteName,
                           @Param("majorName") String majorName,
                           @Param("roleId") Long roleId,
                           @Param("teacherId")Long teacherId);

    Integer deleteAll(@Param("idList") List<Long> idList);

    Integer insertRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

//    IPage<RefTeacherClass> listAllTc(IPage<RefTeacherClass> page,
//                                     @Param("teacherName") String teacherName, @Param("className") String className);

//    List<String> selectTeacherClass(@Param("teacherName") String teacherName, @Param("className") String className);

    List<RefTeacherClass> selectTeacher(@Param("teacherId") Long teacherId, @Param("teacherName") String teacherName);

    List<String> selectTeacherClass(@Param("id") Long id, @Param("className") String className);

    Integer deleteTc(@Param("teacherId") Long teacherId);

    Integer insertClass(@Param("teacherId") Long teacherId, @Param("classIdList") List<Long> classIdList);

    Long findTeacher(@Param("teacherId") Long teacherId, @Param("teacherRoleId") Long teacherRoleId);

    Long findStudent(@Param("studentId") Long studentId, @Param("studentRoleId") Long studentRoleId);
}
