package com.sparksys.oauth.infrastructure.mapper;

import com.sparksys.database.base.mapper.SuperMapper;
import com.sparksys.oauth.infrastructure.entity.AuthUser;
import com.sparksys.oauth.infrastructure.entity.RoleResource;
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
     * @param account 账户
     */
    @Update("update c_auth_user set password_error_num = password_error_num + 1, password_error_last_time = SYSDATE() "
            + " where account = #{account}")
    void incrPasswordErrorNumByAccount(String account);

    /**
     * 查询角色路径
     *
     * @param id 用户id
     * @return
     */
    @Select("SELECT DISTINCT arr.`code`"
            + " FROM c_auth_user_role aur"
            + " INNER JOIN c_auth_role arr ON aur.role_id = arr.id"
            + " WHERE aur.user_id = #{id}")
    List<String> getAuthUserRoles(Long id);


    /**
     * 查询角色路径
     *
     * @return
     */
    @Select("SELECT arr.`code` roleCode,ar.request_url path"
            + " FROM c_auth_user_role aur"
            + " INNER JOIN c_auth_role arr ON aur.role_id = arr.id"
            + " INNER JOIN c_auth_role_authority aua ON aua.role_id = aur.role_id"
            + " INNER JOIN c_auth_resource ar ON ar.id = aua.authority_id"
            + " WHERE ar.request_url IS NOT NULL")
    List<RoleResource> getRoleResourceList();

}
