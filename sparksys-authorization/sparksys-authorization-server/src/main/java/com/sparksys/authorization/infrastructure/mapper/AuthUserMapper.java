package com.sparksys.authorization.infrastructure.mapper;

import com.sparksys.commons.mybatis.mapper.SuperMapper;
import com.sparksys.authorization.infrastructure.entity.AuthUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 用户 Mapper 接口
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:23:50
 */
@Repository
public interface AuthUserMapper extends SuperMapper<AuthUser> {

    /**
     * 密码输错自增
     *
     * @param id
     * @return
     */
    @Update("update c_auth_user set password_error_num = password_error_num + 1, password_error_last_time = SYSDATE() "
            + " where id = #{id}")
    int incrPasswordErrorNumById(Long id);

    /**
     * 查询用户所拥有的资源权限
     *
     * @param id
     * @return List<String>
     */
    @Select("SELECT "
            + "DISTINCT ar.request_url "
            + "FROM c_auth_user_role aur "
            + "INNER JOIN c_auth_role_authority aua ON aua.role_id = aur.role_id "
            + "INNER JOIN c_auth_resource ar ON ar.id = aua.authority_id "
            + "WHERE aur.user_id = #{id} and ar.request_url IS NOT NULL")
    List<String> getAuthUserPermissions(Long id);


    /**
     * 密码输错自增
     *
     * @param account
     * @return
     */
    @Update("update c_auth_user set password_error_num = password_error_num + 1, password_error_last_time = SYSDATE() "
            + " where account = #{account}")
    int incrPasswordErrorNumByAccount(String account);
}
