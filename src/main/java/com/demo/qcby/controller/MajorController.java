package com.demo.qcby.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.web.PageWeb;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.DicInstituteInfo;
import com.demo.qcby.entity.DicMajorInfo;
import com.demo.qcby.service.InstituteService;
import com.demo.qcby.service.MajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname MajorController
 * @Description 专业管理
 * @Date 2021/9/6 14:07
 * @Created by thx
 */
@RestController
@RequestMapping("major")
public class MajorController {
    @Resource
    private MajorService majorService;

    @PreAuth("major:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(Integer pageNo, Integer pageSize, String majorName, String majorDescription) {
        return ResultJson.success(PageWeb.build(majorService.listAll(pageNo, pageSize, majorName, majorDescription)));
    }

    @PreAuth("major:delete")
    @RequestMapping("delete")
    public ResultJson deleteMajor(@RequestParam List<Long> majorIdList) {
        return majorService.deleteMajor(majorIdList);
    }

    @PreAuth("major:insert")
    @RequestMapping("insert")
    public ResultJson insertMajor(DicMajorInfo major) {
        return majorService.insertMajor(major);
    }

    @PreAuth("major:update")
    @RequestMapping("update")
    public ResultJson updateMajor(DicMajorInfo major) {
        return majorService.updateMajor(major);
    }
}
