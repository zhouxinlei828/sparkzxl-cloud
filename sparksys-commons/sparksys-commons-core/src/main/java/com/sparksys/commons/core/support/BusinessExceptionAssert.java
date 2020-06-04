package com.sparksys.commons.core.support;

import com.sparksys.commons.core.api.code.BaseExceptionCode;

import java.text.MessageFormat;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/4 0004
 */
public interface BusinessExceptionAssert extends BaseExceptionCode, SparkAssert{

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        System.out.println(msg);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }
    public static void main(String[] args) {

    }
}
