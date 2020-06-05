package com.sparksys.authorization.application.service;

import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import com.sparksys.authorization.infrastructure.po.AuthUserDetail;
import com.sparksys.authorization.interfaces.dto.AuthUserDTO;
import com.sparksys.authorization.interfaces.dto.AuthUserLoginDTO;

/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService {

    /**
     * 获取授权认证用户
     *
     * @param username 用户名
     * @return
     */
    AuthUserDetail getAuthUserDetail(String username);


    /**
     * 获取用户信息
     *
     * @param authUserLoginDTO
     * @return
     */
    AuthUserDTO getAuthUserDetail(AuthUserLoginDTO authUserLoginDTO);

}
