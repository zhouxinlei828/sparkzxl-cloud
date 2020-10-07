package com.github.sparkzxl.oauth.infrastructure.mapper;

import com.github.sparkzxl.oauth.infrastructure.entity.AuthUser;
import com.github.sparkzxl.oauth.infrastructure.entity.RoleResource;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
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
     * 查询用户所拥有的资源权限
     *
     * @param id 主键
     * @return List<String>
     */
    List<String> getAuthUserPermissions(Long id);

    /**
     * 查询角色路径
     *
     * @param id 用户id
     * @return List<String>
     */
    List<String> getAuthUserRoles(Long id);


    /**
     * 查询角色路径
     *
     * @return List<RoleResource>
     */
    List<RoleResource> getRoleResourceList();

    /**
     * 根据id查询用户信息
     *
     * @param id 主键
     * @return AuthUser
     */
    AuthUser getById(@Param("id") Long id);
}
