package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicClassInfo;

import java.util.List;

/**
 * @Classname ClassService
 * @Description 班级 service 几口
 * @Date 2021/9/6 20:36
 * @Created by thx
 */
public interface ClassService extends IService<DicClassInfo> {
    IPage<DicClassInfo> listAll(Integer pageNo, Integer pageSize, String period, String className, String majorName, String instituteName);

    ResultJson deleteClass(List<Long> classIdList);

    ResultJson insertClass(DicClassInfo classInfo);

    ResultJson updateClass(DicClassInfo classInfo);
}
