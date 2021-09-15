package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.service.HomePageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname HomePageController
 * @Description 菜单树的 Controller 层
 * @Date 2021/9/5 14:53
 * @Created by thx
 */
@RestController
@RequestMapping("homePage")
public class HomePageController {
    @Resource
    private HomePageService homePageService;

    @PreAuth("menuTree")
    @RequestMapping("menuTree")
    public ResultJson<SysMenu> menuTree(HttpServletRequest request) {
        return homePageService.menuTree(request);
    }


}
