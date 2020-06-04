package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;
import lombok.Getter;

/**
 * description: 业务异常类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:49:04
 */
@Getter
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -2803534562798384761L;

    public BusinessException(BaseExceptionCode baseExceptionCode, Object[] args, String message) {
        super(baseExceptionCode, args, message);
    }

    public BusinessException(BaseExceptionCode baseExceptionCode, Object[] args, String message, Throwable cause) {
        super(baseExceptionCode, args, message, cause);
    }

}
