package com.sparksys.commons.core.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-07-08 10:12:12
 */
public class JdkCacheProxy {

    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) {
        InvocationHandler invocationHandler = new JdkInvocationHandler(cacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, invocationHandler);
    }

}
