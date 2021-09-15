package com.demo.qcby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.qcby.entity.StudentLeave;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname LeaveMapper
 * @Description 请假接口
 * @Date 2021/9/10 18:51
 * @Created by thx
 */
public interface LeaveMapper extends BaseMapper<StudentLeave> {
    IPage<StudentLeave> listAllLeaveMsg(IPage<StudentLeave> page,
                                        @Param("studentName") String studentName,
                                        @Param("studentClassName") String studentClassName,
                                        @Param("startTime") String startTime,
                                        @Param("endTime") String endTime,
                                        @Param("status") Integer status,
                                        @Param("reason") String reason,
                                        @Param("approveUserName") String approveUserName,
                                        @Param("noApprovalReason") String noApprovalReason,
                                        @Param("studentId") Long studentId);
}
