package com.github.sparkzxl.sharding.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.sparkzxl.sharding.domain.repository.IOrderRepository;
import com.github.sparkzxl.sharding.infrastructure.entity.OmsOrder;
import com.github.sparkzxl.sharding.infrastructure.mapper.OmsOrderMapper;
import org.springframework.stereotype.Repository;

/**
 * description: 订单表 仓储层 接口实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-10 14:28:49
 */
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private final OmsOrderMapper omsOrderMapper;

    public OrderRepositoryImpl(OmsOrderMapper omsOrderMapper) {
        this.omsOrderMapper = omsOrderMapper;
    }


    @Override
    public OmsOrder getByMemberId(Long memberId) {
        QueryWrapper<OmsOrder> omsOrderQueryWrapper = new QueryWrapper<>();
        omsOrderQueryWrapper.eq("member_id", memberId);
        return omsOrderMapper.selectOne(omsOrderQueryWrapper);
    }
}
