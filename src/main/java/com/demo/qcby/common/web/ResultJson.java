package com.demo.qcby.common.web;

import com.demo.qcby.common.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ResultJson
 * @Description 返回结果集
 * @Date 2021/9/5 11:09
 * @Created by thx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultJson<T> {
//    返回状态码
    private Integer code;
//    返回提示信息
    private String message;
//    返回结果集
    private T result;


    public static ResultJson success() {
        return success(Constant.SUCCESS_MESSAGE, null);
    }

    public static ResultJson success(Object result) {
        return success(Constant.SUCCESS_MESSAGE, result);
    }

    public static ResultJson success(String message, Object result) {
        return new ResultJson(Constant.SUCCESS_CODE, message, result);
    }


    public static ResultJson failure() {
        return success(Constant.FAILURE_MESSAGE, null);
    }

    public static ResultJson failure(Object result) {
        return success(Constant.FAILURE_MESSAGE, result);
    }

    public static ResultJson failure(String message, Object result) {
        return new ResultJson(Constant.FAILURE_CODE, message, result);
    }

}
