package com.github.sparkzxl.test.interfaces.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.test.application.service.IAuthUserService;
import com.github.sparkzxl.test.infrastructure.entity.AuthUser;
import com.github.sparkzxl.test.interfaces.dto.AuthUserPageDTO;
import com.github.sparkzxl.test.interfaces.dto.AuthUserSaveDTO;
import com.github.sparkzxl.test.interfaces.dto.AuthUserUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/pageList")
    public PageInfo<AuthUser> userPageInfo(@RequestParam("name") String name){
        PageHelper.startPage(1,10);
        List<AuthUser> authUsers = super.baseService.list(new LambdaQueryWrapper<AuthUser>().eq(AuthUser::getName, name));
        return PageInfoUtils.pageInfo(authUsers);
    }
}
