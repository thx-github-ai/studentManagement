package com.demo.qcby.common.context;

import com.demo.qcby.entity.SysUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Context
 * @Description 全局上下文
 * @Date 2021/9/5 13:56
 * @Created by thx
 */
public class Context {
    private static Map<String, SysUser> tokenMap = new HashMap<>();

    public static void add(SysUser user) {
        if (user == null) {
            return;
        }
        tokenMap.put(user.getToken(), user);
    }

    public static SysUser getUser(String token) {
        if (token == null) {
            return null;
        }
        return tokenMap.get(token);
    }
}
