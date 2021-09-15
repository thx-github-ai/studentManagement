package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.AdminNotification;
import com.demo.qcby.service.NoticeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Classname NoticeController
 * @Description 通知管理
 * @Date 2021/9/9 10:03
 * @Created by thx
 */
@RestController
@Validated
@RequestMapping("notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @PreAuth("notice:noticeList")
    @RequestMapping("noticeList")
    public ResultJson noticeList(Integer pageNo, Integer pageSize) {
        return ResultJson.success(PageWeb.build(noticeService.noticeList(pageNo, pageSize)));
    }

    @PreAuth("notice:insert")
    @RequestMapping("insert")
    public ResultJson insertNotice(HttpServletRequest request, @NotBlank String title, @NotBlank String content,
                                     @NotNull Integer status) {
        Long userId = Context.getUser(request.getHeader("token")).getId();
        return noticeService.insertNotice(title, content, status, userId);
    }

    @PreAuth("notice:update")
    @RequestMapping("update")
    public ResultJson updateNotice( AdminNotification adminNotification) {
        return noticeService.updateNotice(adminNotification);
    }

    @PreAuth("notice:listAll")
    @RequestMapping("listAll")
    public ResultJson listAllNotice(@NotBlank Integer pageNo, Integer pageSize, String title, String content,
                                    String realName, Date startTime, Date endTime) {
        return ResultJson.success(PageWeb.build(noticeService.listAllNotice(pageNo, pageSize, title, content, realName
        , startTime, endTime)));
    }



}
