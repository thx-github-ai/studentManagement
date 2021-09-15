package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.DicMajorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname MajorMapper
 * @Description 专业管理 Mapper
 * @Date 2021/9/6 20:33
 * @Created by thx
 */

public interface MajorMapper extends BaseMapper<DicMajorInfo> {

    IPage<DicMajorInfo> listAll(IPage<DicMajorInfo> page, @Param("majorName") String majorName,
                                @Param("majorDescription") String majorDescription);

    Integer deleteMajor(@Param("majorIdList") List<Long> majorIdList);
}
