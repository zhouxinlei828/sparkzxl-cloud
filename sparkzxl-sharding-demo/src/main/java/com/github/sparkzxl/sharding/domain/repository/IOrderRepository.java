package com.github.sparkzxl.sharding.domain.repository;

import com.github.sparkzxl.sharding.infrastructure.entity.OmsOrder;

/**
 * description: 订单表 仓储层 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-10 14:23:04
 */
public interface IOrderRepository {

    /**
     * 查询用户订单数据
     *
     * @param memberId 会员id
     * @return OmsOrder
     */
    OmsOrder getByMemberId(Long memberId);
}
