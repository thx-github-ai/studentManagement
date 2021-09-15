package com.demo.qcby.common.constant;

/**
 * @Classname Constant
 * @Description 常量类
 * @Date 2021/9/5 11:15
 * @Created by thx
 */
public class Constant {
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAILURE_CODE = 500;
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAILURE_MESSAGE = "操作失败";
    public static final Integer MAN = 0;
    public static final Integer WOMAN = 1;
//    请假状态，不批准为 2
    public static final Integer LEAVE_STATUS_DENIAL = 2;
    //    请假状态，批准为 1
    public static final Integer LEAVE_STATUS_ALLOW = 1;
    //    请假状态，未批准为 0
    public static final Integer LEAVE_STATUS_UNCHECK = 0;
//签到状态，已签到为 1
    public static final Integer ATTENDANCE_STATE_COMPLETE = 1;
//签到状态，未签到为 0
    public static final Integer ATTENDANCE_STATE_UNCOMPLETED = 0;
    //已发布，已发布为 1
    public static final Integer PUBLISH_STATE_COMPLETE = 1;
    //未发布，未发布为 0
    public static final Integer PUBLISH_STATE_UNCOMPLETED = 0;
//    影响的行数
    public static final Integer AFFECT_ROW_SINGLE = 1;
//    学生 id
    public static final Long STUDENT_ID = 1L;
//    教师 id
    public static final Long TEACHER_ID = 2L;
//    管理员 id
    public static final Long MANAGER_ID = 3L;

}
