package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.sparkzxl.authorization.infrastructure.entity.RoleOrg;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 角色组织关系 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 20:58:57
 */
@Repository
public interface RoleOrgMapper extends SuperMapper<RoleOrg> {

    /**
     * 根据租户code删除角色组织
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from auth_role_org where tenant_code = #{tenantCode}")
    @InterceptorIgnore(tenantLine = "true")
    void deleteRoleOrg(String tenantCode);
}
