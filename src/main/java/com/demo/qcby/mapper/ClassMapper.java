package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.DicClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname ClassMapper
 * @Description 班级 Mapper 类
 * @Date 2021/9/6 20:35
 * @Created by thx
 */

public interface ClassMapper extends BaseMapper<DicClassInfo> {

    IPage<DicClassInfo> listAll(IPage<DicClassInfo> page,
                                @Param("period") String period,
                                @Param("className") String className,
                                @Param("majorName") String majorName,
                                @Param("instituteName") String instituteName);

    Integer deleteClass(@Param("classIdList") List<Long> classIdList);
}
