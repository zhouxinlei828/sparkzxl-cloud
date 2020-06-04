package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;
import com.sparksys.commons.core.api.code.ResponseResultStatus;

/**
 * description: 校验异常类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:49:20
 */
public class BusinessValidationException extends BaseException {

    private static final long serialVersionUID = 2101235516840843048L;

    public BusinessValidationException(ResponseResultStatus responseResultStatus) {
        super(responseResultStatus);
    }

    public BusinessValidationException(String message) {
        super(message);
    }
}
