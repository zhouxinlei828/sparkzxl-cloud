package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;

/**
 * description: 授权认证异常类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:48:54
 */
public class AuthException extends BaseException {

    private static final long serialVersionUID = -7571216052553061849L;

    public AuthException(BaseExceptionCode baseExceptionCode) {
        super(baseExceptionCode);
    }

    public AuthException(String message) {
        super(message);
    }
}
