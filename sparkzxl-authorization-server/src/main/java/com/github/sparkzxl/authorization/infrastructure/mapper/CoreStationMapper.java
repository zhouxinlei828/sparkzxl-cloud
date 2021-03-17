package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.sparkzxl.authorization.infrastructure.entity.CoreStation;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 岗位 Mapper 接口
 *
 * @author charles.zhou
 * @date 2020-06-07 13:30:19
 */
@Repository
public interface CoreStationMapper extends SuperMapper<CoreStation> {

    /**
     * 根据租户code删除岗位
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from core_station where tenant_code = #{tenantCode}")
    @InterceptorIgnore(tenantLine = "true")
    void deleteTenantStation(String tenantCode);
}
