package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicInstituteInfo;
import com.demo.qcby.mapper.InstituteMapper;
import com.demo.qcby.service.InstituteService;
import com.demo.qcby.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname instituteServiceImpl
 * @Description 学院实现类
 * @Date 2021/9/6 19:27
 * @Created by thx
 */
@Service
public class InstituteServiceImpl extends ServiceImpl<InstituteMapper, DicInstituteInfo> implements InstituteService {

    @Resource
    private InstituteMapper instituteMapper;
    /**
     * 查看所有学院
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public IPage<DicInstituteInfo> listAll(Integer pageNo, Integer pageSize) {
        IPage<DicInstituteInfo> page = new Page<>(pageNo, pageSize);
        return instituteMapper.listAll(page);
    }

    /**
     * 批量删除学院
     * @param instituteIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteInstitute(List<Long> instituteIdList) {
        if (instituteIdList == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        instituteMapper.deleteInstitute(instituteIdList);
        return ResultJson.success("删除成功", null);
    }

    /**
     * 添加学院
     * @param institute
     * @return
     */
    @Override
    @Transactional
    public ResultJson insertInstitute(DicInstituteInfo institute) {
        if (StringUtil.isOrNotEmpty(institute.getInstituteName()) ||
                StringUtil.isOrNotEmpty(institute.getInstituteDescription())) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = instituteMapper.insert(institute);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }
        return ResultJson.success("添加成功！", null);
    }

    @Override
    @Transactional
    public ResultJson updateInstitute(DicInstituteInfo institute) {
        if (institute.getId() == null) {
            return ResultJson.failure("必填项目不能为空", null);
        }
        int count = instituteMapper.updateById(institute);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改成功！", null);
    }
}
