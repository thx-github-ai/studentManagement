package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.entity.AdminNotification;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Classname NoticeMapper
 * @Description 通知mapper
 * @Date 2021/9/9 10:06
 * @Created by thx
 */
public interface NoticeMapper extends BaseMapper<AdminNotification> {
     IPage<AdminNotification> noticeList(IPage<AdminNotification> page, @Param("status") Integer publishStateComplete);

     IPage<AdminNotification> listAllNotice(IPage<AdminNotification> page,
                                            @Param("title") String title, @Param("content") String content,
                                            @Param("realName") String realName, @Param("startTime")Date startTime,
                                            @Param("endTime") Date endTime);
}
