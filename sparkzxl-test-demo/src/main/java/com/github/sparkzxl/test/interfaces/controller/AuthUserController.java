package com.github.sparkzxl.test.interfaces.controller;

import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.test.application.service.IAuthUserService;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.test.interfaces.dto.AuthUserPageDTO;
import com.github.sparkzxl.test.interfaces.dto.AuthUserSaveDTO;
import com.github.sparkzxl.test.interfaces.dto.AuthUserUpdateDTO;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 用户管理
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:32
 */

@RestController
@RequestMapping("/user")
@ResponseResult
@WebLog
@Api(tags = "用户管理")
public class AuthUserController extends SuperCacheController<IAuthUserService, Long,
        AuthUser, AuthUserPageDTO, AuthUserSaveDTO, AuthUserUpdateDTO> {

}
