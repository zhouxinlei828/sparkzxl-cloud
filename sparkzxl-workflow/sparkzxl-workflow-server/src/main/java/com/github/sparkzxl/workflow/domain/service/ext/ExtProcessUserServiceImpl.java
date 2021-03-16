package com.github.sparkzxl.workflow.domain.service.ext;

import com.github.sparkzxl.workflow.infrastructure.entity.ExtProcessUser;
import com.github.sparkzxl.workflow.infrastructure.mapper.ExtProcessUserMapper;
import com.github.sparkzxl.workflow.application.service.ext.IExtProcessUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程用户信息 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-01-08 17:09:59
 */
@Service
public class ExtProcessUserServiceImpl extends ServiceImpl<ExtProcessUserMapper, ExtProcessUser> implements IExtProcessUserService {

}