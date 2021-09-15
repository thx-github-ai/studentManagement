package com.demo.qcby.aspect;

import com.demo.qcby.anno.SelfLog;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.entity.SysOperateLog;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.service.LogService;
import com.demo.qcby.util.JwtUtil;
import com.demo.qcby.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @Classname SelfLogApsect
 * @Description 操作切面日志
 * @Date 2021/9/9 16:00
 * @Created by thx
 */
@Aspect
@Component
@Slf4j
public class SelfLogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    //    切入点:
    @Pointcut("@annotation(com.demo.qcby.anno.SelfLog)")
    public void selfLogPointCut() {

    }

    //     /**
//     * 可控制目标函数是否执行
//     */
    @Around("selfLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("Around................通知");
//        log.info("进入Around通知....");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        String token = request.getHeader("token");
//        log.info("请求令牌：token:{}", token);
        SysUser user = null;
        if (!StringUtil.isOrNotEmpty(token)) {
            String audience = JwtUtil.getAudience(token);
            if (!StringUtil.isOrNotEmpty(audience)) {
                // 获取到用户id
                user = Context.getUser(token);
            }
        }
        //获取注解信息
        SelfLog selfLog = method.getAnnotation(SelfLog.class);
//        log.info("selfLog：{}", selfLog);
        SysOperateLog operateLog = new SysOperateLog();
        operateLog.setOperateTime(new Date());
        operateLog.setOperateType(String.valueOf(selfLog.type()));
        operateLog.setOperateModule(selfLog.module());
        operateLog.setUserName(user.getUserName());
        // 操作日志入库
//        log.info("操作日志：operateLog=>{}", operateLog);

        logService.insertOperateLog(operateLog);

        String nameValue = selfLog.name();
//        log.info("nameValue的值为：{}", nameValue);
        String name = null;

        //获取切入点方法参数
        Object[] objects = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (Objects.equals(nameValue, paramNames[i]) && objects[i] != null) {
                name = objects[i].toString();
            }
        }
        /**
         *  这是目标方法执行的代码
         */
        Object r = joinPoint.proceed();

        log.info("结束Around通知....");
        return r;
    }
}
