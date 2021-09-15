package com.demo.qcby.service;

import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SysMenu;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname HomePageService
 * @Description 菜单树接口
 * @Date 2021/9/5 14:57
 * @Created by thx
 */
public interface HomePageService {
    ResultJson<SysMenu> menuTree(HttpServletRequest request);
}
