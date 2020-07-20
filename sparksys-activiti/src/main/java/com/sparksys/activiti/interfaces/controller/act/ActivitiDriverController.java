package com.sparksys.activiti.interfaces.controller.act;

import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 流程驱动管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 16:29:54
 */
@RestController
@RequestMapping("/activiti/driver/")
@ResponseResult
@WebLog
@Api(tags = "流程驱动管理")
public class ActivitiDriverController {


}
