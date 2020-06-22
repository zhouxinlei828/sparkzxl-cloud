package com.sparksys.oauth.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * description: 用户 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:50
 */
@Repository
public interface AuthUserMapper extends BaseMapper<AuthUser> {

    /**
     * 密码输错自增
     *
     * @param id
     * @return
     */
    @Update("update c_auth_user set password_error_num = password_error_num + 1, password_error_last_time = SYSDATE() "
            + " where id = #{id}")
    int incrPasswordErrorNumById(Long id);
}
