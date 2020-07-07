package com.sparksys.sharding.domain.service;

import com.sparksys.sharding.infrastructure.entity.OmsOrder;
import com.sparksys.sharding.infrastructure.mapper.OmsOrderMapper;
import com.sparksys.sharding.application.service.IOmsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-07
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

}
