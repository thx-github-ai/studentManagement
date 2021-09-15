package com.demo.qcby.util;

/**
 * @Classname IsEmpty
 * @Description 判空工具类
 * @Date 2021/9/5 11:43
 * @Created by thx
 */
public class StringUtil {
    public static boolean isOrNotEmpty(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
