package com.sparksys.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;
import org.springframework.stereotype.Repository;

/**
 * description: 用户 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:50
 */
@Repository
public interface AuthUserMapper extends BaseMapper<AuthUserDO> {

}
