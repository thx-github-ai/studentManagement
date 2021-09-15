package com.demo.qcby.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.AdminNotification;
import io.swagger.models.auth.In;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Date;

/**
 * @Classname NoticeService
 * @Description 通知 service 接口
 * @Date 2021/9/9 10:05
 * @Created by thx
 */

public interface NoticeService extends IService<AdminNotification> {
     IPage<AdminNotification> noticeList(Integer pageNo, Integer pageSize);

     ResultJson insertNotice(String title, String content, Integer status, Long userId);

     ResultJson updateNotice(AdminNotification adminNotification);

     IPage<AdminNotification> listAllNotice(Integer pageNo, Integer pageSize, String title, String content,
                                            String realName, Date startTime, Date endTime);
}
