package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicInstituteInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname InstituteMapper
 * @Description 学院 mapper 接口
 * @Date 2021/9/6 19:29
 * @Created by thx
 */
public interface InstituteMapper extends BaseMapper<DicInstituteInfo> {
    IPage<DicInstituteInfo> listAll(IPage<DicInstituteInfo> page);

    ResultJson deleteInstitute(@Param("instituteIdList") List<Long> instituteIdList);
}
