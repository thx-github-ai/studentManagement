package com.demo.qcby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicInstituteInfo;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.service.InstituteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname InstituteController
 * @Description 学院管理
 * @Date 2021/9/6 14:08
 * @Created by thx
 */
@RestController
@RequestMapping("institute")
public class InstituteController {
    @Resource
    private InstituteService instituteService;

    @PreAuth("institute:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(Integer pageNo, Integer pageSize) {
        return ResultJson.success(PageWeb.build(instituteService.listAll(pageNo, pageSize)));
    }

    @PreAuth("institute:delete")
    @RequestMapping("delete")
    public ResultJson deleteMenu(@RequestParam List<Long> instituteIdList) {
        return instituteService.deleteInstitute(instituteIdList);
    }

    @PreAuth("institute:insert")
    @RequestMapping("insert")
    public ResultJson insertMenu(DicInstituteInfo institute) {
        return instituteService.insertInstitute(institute);
    }

    @PreAuth("institute:update")
    @RequestMapping("update")
    public ResultJson updateMenu(DicInstituteInfo institute) {
        return instituteService.updateInstitute(institute);
    }
}
