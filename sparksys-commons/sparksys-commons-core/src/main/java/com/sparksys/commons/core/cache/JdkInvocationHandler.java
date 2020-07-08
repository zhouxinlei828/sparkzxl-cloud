package com.sparksys.commons.core.cache;

import com.sparksys.commons.core.utils.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: JDK动态代理实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-08 10:09:45
 */
public class JdkInvocationHandler implements InvocationHandler {

    private final ICacheAdapter cacheAdapter;

    public JdkInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter,
                args);
    }
}
