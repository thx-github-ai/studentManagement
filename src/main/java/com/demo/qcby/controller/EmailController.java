package com.demo.qcby.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.api.R;
import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.context.Context;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.entity.SMSParameter;
import com.demo.qcby.entity.SysMenu;
import com.demo.qcby.entity.SysUser;
import com.demo.qcby.entity.ToEmail;
import com.demo.qcby.util.VerCodeGenerateUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * @Classname EmailController
 * @Description 发送邮件
 * @Date 2021/9/7 12:15
 * @Created by thx
 */
@RestController
@RequestMapping("email")
public class EmailController {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @PreAuth("email:send")
    @RequestMapping("sendEmail")
    public ResultJson commonEmail(ToEmail toEmail, HttpServletRequest request) {
//        创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(toEmail.getTos());

        message.setSubject("您本次的验证码是");

        String verCode = VerCodeGenerateUtil.generateVerCode();

        message.setText("尊敬的 nier nier,您好:\n"
                        + "\n本次请求的邮件验证码为:" + verCode + ",本验证码永久无效，请及时输入。（请勿泄露此验证码）\n"
                        + "\n如非本人操作，请忽略该邮件。\n(这是一封通过 java 自动发送的邮件，请不要直接回复）");

        mailSender.send(message);
        SysUser user = Context.getUser(request.getHeader("token"));
        user.setVerCode(verCode);
        return ResultJson.success("发送成功");
    }


    private SMSParameter smsParameter = new SMSParameter();
    @PreAuth("email:send")
    @RequestMapping("sendText")
    public ResultJson sendText(String phone) throws ClientException, HTTPException, IOException {
        String ver = VerCodeGenerateUtil.generateVerCode();
        String[] code = new String[]{ver, "999"};
        SmsSingleSender sender = new SmsSingleSender(smsParameter.getAppId(), smsParameter.getAppKey());
//        SmsSingleSenderResult result = sender.sendWithParam("86", phone, smsParameter.getTemplateId(),
//               code, smsParameter.getSmsSign(), "", "" );

        SmsSingleSenderResult result = sender.sendWithParam("86", phone, smsParameter.getTemplateId(),
                code, smsParameter.getSmsSign(), "", "");
        if (result.result == 0) {
            return ResultJson.success(code[0]);
        } else {
            return ResultJson.failure(result.errMsg, result.sid);
        }

    }

//    @PreAuth("email:send")
//    @RequestMapping("sendText")
//    public ResultJson sendText(String phone, HttpServletRequest request) throws ClientException {
//        String code = VerCodeGenerateUtil.generateVerCode();
//        String templateParam = "{\"code\":\""+code+"\"}";
//        String templateCode = "1112029";
//        try {
//            SendSmsResponse response = SmsToolUtil.sendSms(phone, templateParam, templateCode);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        SmsToolUtil.sendSms(phone, templateParam, templateCode);
//        request.getSession().setAttribute("codePhone", code);
//        return ResultJson.success(code);
//    }



}
