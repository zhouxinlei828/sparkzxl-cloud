package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;
import com.sparksys.commons.core.api.code.ResponseResultStatus;

/**
 * description: 业务异常类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:49:04
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -2803534562798384761L;

    public BusinessException(ResponseResultStatus responseResultStatus) {
        super(responseResultStatus);
    }

    public BusinessException(String message) {
        super(message);
    }
}
