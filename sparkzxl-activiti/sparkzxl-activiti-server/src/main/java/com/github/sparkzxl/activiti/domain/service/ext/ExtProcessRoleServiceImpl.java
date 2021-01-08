package com.github.sparkzxl.activiti.domain.service.ext;

import com.github.sparkzxl.activiti.infrastructure.entity.ExtProcessRole;
import com.github.sparkzxl.activiti.infrastructure.mapper.ExtProcessRoleMapper;
import com.github.sparkzxl.activiti.application.service.ext.IExtProcessRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程角色 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-01-08 17:08:44
 */
@Service
public class ExtProcessRoleServiceImpl extends ServiceImpl<ExtProcessRoleMapper, ExtProcessRole> implements IExtProcessRoleService {

}
