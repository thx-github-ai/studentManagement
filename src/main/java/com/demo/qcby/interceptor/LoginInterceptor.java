package com.demo.qcby.interceptor;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.util.JwtUtil;
import com.demo.qcby.util.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LoginInterceptor
 * @Description 登录拦截接口
 * @Date 2021/9/5 14:39
 * @Created by thx
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        如果不是 controller 的方法，不拦截
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtil.isOrNotEmpty(token)) {
            return false;
        }

        String audience = JwtUtil.getAudience(token);
//        如果没有找到，说明没登录，进行拦截
        if (!JwtUtil.verifyToken(token, audience)) {
            return false;
        }
        SysUser user = Context.getUser(token);
        if (user == null) {
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PreAuth preAuth = handlerMethod.getMethodAnnotation(PreAuth.class);
//        空路径不拦截
        if (preAuth == null) {
            return true;
        }
//       无对应权限，进行拦截
        if (!user.getAuthList().contains(preAuth.value())) {
            return false;
        }
        return true;
    }
}
