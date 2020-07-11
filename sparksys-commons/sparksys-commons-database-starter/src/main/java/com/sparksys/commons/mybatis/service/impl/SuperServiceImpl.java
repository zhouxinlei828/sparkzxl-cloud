package com.sparksys.commons.mybatis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.sparksys.commons.core.support.ResponseResultStatus;
import com.sparksys.commons.mybatis.mapper.SuperMapper;
import com.sparksys.commons.mybatis.service.SuperService;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-07-07 20:14:55
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {
    public SuperServiceImpl() {
    }

    public SuperMapper getSuperMapper() {
        ResponseResultStatus.SERVICE_MAPPER_ERROR.assertNotNull(this.baseMapper);
        return this.baseMapper;
    }

    protected static String buildKey(Object... args) {
        if (args.length == 1) {
            return String.valueOf(args[0]);
        } else {
            return args.length > 0 ? StrUtil.join(":", args) : "";
        }
    }

    @Override
    public boolean save(T model) {
        return super.save(model);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean updateAllById(T model) {
        return SqlHelper.retBool(this.getSuperMapper().updateAllById(model));
    }

    @Override
    public boolean updateById(T model) {
        return super.updateById(model);
    }
}
