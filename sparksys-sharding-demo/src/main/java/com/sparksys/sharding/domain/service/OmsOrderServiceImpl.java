package com.sparksys.sharding.domain.service;

import com.sparksys.core.constant.CacheKey;
import com.sparksys.database.service.impl.AbstractSuperCacheServiceImpl;
import com.sparksys.sharding.domain.repository.IOrderRepository;
import com.sparksys.sharding.infrastructure.entity.OmsOrder;
import com.sparksys.sharding.infrastructure.mapper.OmsOrderMapper;
import com.sparksys.sharding.application.service.IOmsOrderService;
import org.springframework.stereotype.Service;

/**
 * description: 订单 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-10 13:53:23
*/
@Service
public class OmsOrderServiceImpl extends AbstractSuperCacheServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    private final IOrderRepository orderRepository;

    public OmsOrderServiceImpl(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    protected String getRegion() {
        return CacheKey.OMS_ORDER;
    }

    @Override
    public OmsOrder getByMemberId(Long memberId) {
        return orderRepository.getByMemberId(memberId);
    }
}
