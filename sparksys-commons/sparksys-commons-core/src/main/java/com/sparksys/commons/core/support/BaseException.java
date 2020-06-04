package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;
import com.sparksys.commons.core.api.code.ResponseResultStatus;
import lombok.Getter;

/**
 * description：BaseException
 *
 * @author zhouxinlei
 * @date  2020-06-04 12:40:33
 */
@Getter
public class BaseException extends Exception {

    private ResponseResultStatus responseResultStatus;

    public BaseException(ResponseResultStatus responseResultStatus) {
        this.responseResultStatus = responseResultStatus;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }
}
