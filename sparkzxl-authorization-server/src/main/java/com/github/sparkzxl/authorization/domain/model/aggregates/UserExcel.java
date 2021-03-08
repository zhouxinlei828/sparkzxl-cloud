package com.github.sparkzxl.authorization.domain.model.aggregates;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * description: 用户Excel实体类
 *
 * @author: zhouxinlei
 * @date: 2021-01-04 15:24:30
 */
@Data
public class UserExcel {

    @ExcelProperty("账号")
    private String account;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("组织")
    private String orgName;

    @ExcelProperty("岗位")
    private String stationName;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("电话")
    private String mobile;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("民族")
    private String nation;

    @ExcelProperty("学历")
    private String education;

    @ExcelProperty("职位状态")
    private String positionStatus;
}
