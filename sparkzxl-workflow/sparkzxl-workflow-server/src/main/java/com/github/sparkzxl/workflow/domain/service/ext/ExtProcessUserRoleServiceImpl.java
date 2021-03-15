package com.github.sparkzxl.workflow.domain.service.ext;

import com.github.sparkzxl.workflow.infrastructure.entity.ExtProcessUserRole;
import com.github.sparkzxl.workflow.infrastructure.mapper.ExtProcessUserRoleMapper;
import com.github.sparkzxl.workflow.application.service.ext.IExtProcessUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 流程用户角色关系 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2021-01-08 17:09:22
 */
@Service
public class ExtProcessUserRoleServiceImpl extends ServiceImpl<ExtProcessUserRoleMapper, ExtProcessUserRole> implements IExtProcessUserRoleService {

}
