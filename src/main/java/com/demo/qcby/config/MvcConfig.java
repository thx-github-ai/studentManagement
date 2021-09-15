package com.demo.qcby.config;

import com.demo.qcby.interceptor.LoginInterceptor;
import com.demo.qcby.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Classname MvcConfig
 * @Description 全局配置类
 * @Date 2021/9/5 14:47
 * @Created by thx
 */
@Component
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//    添加拦截器，除了登录路径，其他全部拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/login", "/file/**");
    }
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }


    /**
     * 文件上传路径前缀
     */
    @Value("${file.path.prefix}")
    public String filePrefix;
    /**
     * 本地磁盘目录
     */
    @Value("${file.path.upload}")
    public String uploadLocalPath;
    /**
     * 映射本地磁盘为静态目录
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        FileUtil.filePrefix = filePrefix;
        FileUtil.uploadLocalPath = uploadLocalPath;
        registry.addResourceHandler(filePrefix +"/**").addResourceLocations("file:"+uploadLocalPath);
    }
}
