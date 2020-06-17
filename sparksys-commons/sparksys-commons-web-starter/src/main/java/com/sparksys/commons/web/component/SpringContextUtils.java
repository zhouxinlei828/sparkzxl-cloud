package com.sparksys.commons.web.component;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * description: 获取bean公用方法
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:42:56
 */
public class SpringContextUtils {

    private static ApplicationContext applicationContext;
    private static ApplicationContext parentApplicationContext;

    public SpringContextUtils() {

    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        Assert.notNull(ctx, "SpringUtil injection ApplicationContext is null");
        applicationContext = ctx;
        parentApplicationContext = ctx.getParent();
    }

    public static Object getBean(String name) {
        Assert.hasText(name, "SpringUtil name is null or empty");

        try {
            return applicationContext.getBean(name);
        } catch (Exception var2) {
            return parentApplicationContext.getBean(name);
        }
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        Assert.notNull(type, "SpringUtil type is null");

        try {
            return applicationContext.getBean(name, type);
        } catch (Exception var3) {
            return parentApplicationContext.getBean(name, type);
        }
    }

    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "SpringUtil type is null");

        try {
            return applicationContext.getBean(type);
        } catch (Exception var2) {
            return parentApplicationContext.getBean(type);
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        Assert.notNull(type, "SpringUtil type is null");

        try {
            return applicationContext.getBeansOfType(type);
        } catch (Exception var2) {
            return parentApplicationContext.getBeansOfType(type);
        }
    }

    public static void publishEvent(Object event) {
        applicationContext.publishEvent(event);
    }

}
