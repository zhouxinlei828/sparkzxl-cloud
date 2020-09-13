package com.github.sparkzxl.sharding.application.service;

import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.sharding.infrastructure.entity.OmsOrder;

/**
 * description: 订单 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-10 13:53:34
 */
public interface IOmsOrderService extends SuperCacheService<OmsOrder> {

    /**
     * 查询用户订单数据
     *
     * @param memberId 会员id
     * @return
     */
    OmsOrder getByMemberId(Long memberId);
}
