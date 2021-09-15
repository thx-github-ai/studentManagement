package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicInstituteInfo;

import java.util.List;

/**
 * @Classname instituteService
 * @Description 学院 service 接口
 * @Date 2021/9/6 19:26
 * @Created by thx
 */

public interface InstituteService extends IService<DicInstituteInfo> {
    IPage<DicInstituteInfo> listAll(Integer pageNo, Integer pageSize);

    ResultJson deleteInstitute(List<Long> instituteIdList);

    ResultJson insertInstitute(DicInstituteInfo institute);

    ResultJson updateInstitute(DicInstituteInfo institute);
}
