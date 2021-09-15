package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicClassInfo;
import com.demo.qcby.mapper.ClassMapper;
import com.demo.qcby.service.ClassService;
import com.demo.qcby.util.StringUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.ConsoleHandler;

/**
 * @Classname ClassServiceImpl
 * @Description 班级实现类
 * @Date 2021/9/6 20:37
 * @Created by thx
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, DicClassInfo> implements ClassService {
    @Resource
    private ClassMapper classMapper;
    /**
     * 查看班级信息
     * @param pageNo
     * @param pageSize
     * @param period
     * @param className
     * @param majorName
     * @param instituteName
     * @return
     */
    @Override
    public IPage<DicClassInfo> listAll(Integer pageNo, Integer pageSize, String period, String className,
                                       String majorName, String instituteName) {
        IPage<DicClassInfo> page = new Page<>(pageNo, pageSize);
        return classMapper.listAll(page, period, className, majorName, instituteName);
    }

    /**
     * 删除班级
     * @param classIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteClass(List<Long> classIdList) {
        if (classIdList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        classMapper.deleteClass(classIdList);
        return ResultJson.success("删除成功", null);
    }

    /**
     * 添加班级
     * @param classInfo
     * @return
     */
    @Override
    @Transactional
    public ResultJson insertClass(DicClassInfo classInfo) {
        if (StringUtil.isOrNotEmpty(classInfo.getClassName()) || classInfo.getPeriod() == null ||
                classInfo.getMajorId() == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = classMapper.insert(classInfo);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }
        return ResultJson.success("添加成功！", null);
    }

    /**
     * 修改班级
     * @param classInfo
     * @return
     */
    @Override
    @Transactional
    public ResultJson updateClass(DicClassInfo classInfo) {
        if (StringUtil.isOrNotEmpty(classInfo.getClassName()) || classInfo.getPeriod() == null ||
        classInfo.getId() == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = classMapper.updateById(classInfo);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改信息失败", null);
        }
        return ResultJson.success("修改信息成功！", null);
    }



}
