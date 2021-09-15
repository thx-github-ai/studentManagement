package com.demo.qcby.common.web;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
* @author thx
* @description 全局时间入参格式化
* @yyds: thx yyds
* @date 2021/9/6 19:10
*/

@ControllerAdvice
public class InitBinderDateController {


    /**
     * 将前台传递过来的日期格式的字符串，自动转化为时间类型
     *
     *      [拦截不到@RequestBody注解修饰的参数]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
//
//         Date 类型转换
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
//        System.out.println("test");
        // LocalDateTime 类型转换
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                if(!StringUtils.isEmpty(text)){
                    setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            }
        });
    }
}
