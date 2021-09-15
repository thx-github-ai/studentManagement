package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicClassInfo;
import com.demo.qcby.entity.DicInstituteInfo;
import com.demo.qcby.service.ClassService;
import com.demo.qcby.service.InstituteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname ClassController
 * @Description 班级管理
 * @Date 2021/9/6 14:09
 * @Created by thx
 */
@RestController
@RequestMapping("class")
public class ClassController {
    @Resource
    private ClassService classService;

    @PreAuth("class:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(Integer pageNo, Integer pageSize, String period, String className,
                              String majorName, String instituteName) {
        return ResultJson.success(PageWeb.build(classService.listAll(pageNo, pageSize, period, className, majorName
        , instituteName)));
    }

    @PreAuth("class:delete")
    @RequestMapping("delete")
    public ResultJson deleteClass(@RequestParam List<Long> classIdList) {
        return classService.deleteClass(classIdList);
    }

    @PreAuth("class:insert")
    @RequestMapping("insert")
    public ResultJson insertClass(DicClassInfo classInfo) {
        return classService.insertClass(classInfo);
    }

    @PreAuth("class:update")
    @RequestMapping("update")
    public ResultJson updateClass(DicClassInfo classInfo) {
        return classService.updateClass(classInfo);
    }
}
