package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicMajorInfo;
import com.demo.qcby.mapper.MajorMapper;
import com.demo.qcby.service.MajorService;
import com.demo.qcby.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname majorServiceImpl
 * @Description 专业管理实现类
 * @Date 2021/9/6 20:32
 * @Created by thx
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, DicMajorInfo> implements MajorService {
    @Resource
    private MajorMapper majorMapper;
    /**
     * 查看专业信息
     * @param pageNo
     * @param pageSize
     * @param majorName
     * @param majorDescription
     * @return
     */
    @Override
    public IPage<DicMajorInfo> listAll(Integer pageNo, Integer pageSize, String majorName, String majorDescription) {
        IPage<DicMajorInfo> page = new Page<>(pageNo, pageSize);
        return majorMapper.listAll(page, majorName, majorDescription);
    }

    /**
     * 删除专业信息
     * @param majorIdList
     * @return
     */
    @Override
    @Transactional
    public ResultJson deleteMajor(List<Long> majorIdList) {
        if (majorIdList == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        majorMapper.deleteMajor(majorIdList);
        return ResultJson.success("删除成功", null);
    }

    @Override
    @Transactional
    public ResultJson insertMajor(DicMajorInfo major) {
        if (StringUtil.isOrNotEmpty(major.getMajorName()) || StringUtil.isOrNotEmpty(major.getMajorDescription()) ||
        major.getInstituteId() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = majorMapper.insert(major);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("添加失败", null);
        }
        return ResultJson.success("添加成功", null);
    }

    /**
     * 修改专业
     * @param major
     * @return
     */
    @Override
    @Transactional
    public ResultJson updateMajor(DicMajorInfo major) {
        if (StringUtil.isOrNotEmpty(major.getMajorName()) || StringUtil.isOrNotEmpty(major.getMajorDescription()) ||
                major.getInstituteId() == null || major.getId() == null) {
            return ResultJson.failure("必填信息不能为空", null);
        }
        int count = majorMapper.updateById(major);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("修改失败", null);
        }
        return ResultJson.success("修改成功", null);
    }


}
