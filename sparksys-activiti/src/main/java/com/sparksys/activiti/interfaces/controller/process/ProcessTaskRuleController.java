package com.sparksys.activiti.interfaces.controller.process;

import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:流程流向管理
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 15:44:59
 */
@RestController
@ResponseResult
@WebLog
@RequestMapping("/process/rule")
@Api(tags = "流程流向管理")
public class ProcessTaskRuleController {

}
