package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicMajorInfo;

import java.util.List;

/**
 * @Classname majorService
 * @Description 专业 service 接口
 * @Date 2021/9/6 20:32
 * @Created by thx
 */

public interface MajorService extends IService<DicMajorInfo> {
    IPage<DicMajorInfo> listAll(Integer pageNo, Integer pageSize, String majorName, String majorDescription);

    ResultJson deleteMajor(List<Long> majorIdList);

    ResultJson insertMajor(DicMajorInfo major);

    ResultJson updateMajor(DicMajorInfo major);
}
