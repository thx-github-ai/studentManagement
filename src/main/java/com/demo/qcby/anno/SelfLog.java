package com.demo.qcby.anno;

import com.demo.qcby.common.constant.GlobalConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SelfLog
 * @Description 日志注解
 * @Date 2021/9/9 15:57
 * @Created by thx
 */
@Target({ElementType.METHOD, ElementType.TYPE})  // 该注解可以作用于那些类型元素上：类、方法、字段
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface SelfLog {
    /**
     * 操作类型
     * @return
     */

    int type() default GlobalConstant.LOG_TYPE_SELECT;

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 名称参数
     * @return
     */
    String name() default "name";
}
