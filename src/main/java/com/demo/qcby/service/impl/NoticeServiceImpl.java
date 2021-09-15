package com.demo.qcby.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.AdminNotification;
import com.demo.qcby.mapper.NoticeMapper;
import com.demo.qcby.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Classname NoticeServiceImpl
 * @Description 通知实现类
 * @Date 2021/9/9 10:05
 * @Created by thx
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, AdminNotification> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public IPage<AdminNotification> noticeList(Integer pageNo, Integer pageSize) {
        IPage<AdminNotification> page = new Page<>(pageNo, pageSize);
        return noticeMapper.noticeList(page, Constant.PUBLISH_STATE_COMPLETE);
    }

    @Override
    public ResultJson insertNotice(String title, String content, Integer status, Long userId) {
        AdminNotification adminNotification = new AdminNotification();
        adminNotification.setStatus(status);
        adminNotification.setContent(content);
        adminNotification.setTitle(title);
        adminNotification.setUserId(userId);
        if (status == Constant.PUBLISH_STATE_COMPLETE) {
            adminNotification.setReleaseTime(new Date());
        }
        adminNotification.setCreateTime(new Date());
        int count = noticeMapper.insert(adminNotification);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.failure("出错了！", null);
        }
        return ResultJson.success("添加成功", null);

    }

    @Override
    public ResultJson updateNotice(AdminNotification adminNotification) {
        if (adminNotification.getStatus() == Constant.PUBLISH_STATE_COMPLETE) {
            adminNotification.setReleaseTime(new Date());
            adminNotification.setUpdateTime(new Date());
        }
        int count = noticeMapper.updateById(adminNotification);
        if (count != Constant.AFFECT_ROW_SINGLE) {
            return ResultJson.success("发布失败", null);
        }
        return ResultJson.success("发布成功", null);

    }

    @Override
    public IPage<AdminNotification> listAllNotice(Integer pageNo, Integer pageSize, String title, String content,
                                                  String realName, Date startTime, Date endTime) {
        IPage<AdminNotification> page = new Page<>(pageNo, pageSize);
        return noticeMapper.listAllNotice(page, title, content, realName, startTime, endTime);
    }
}
