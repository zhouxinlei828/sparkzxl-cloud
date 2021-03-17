package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionary;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 字典类型 Mapper 接口
 *
 * @author charles.zhou
 * @date   2020-07-28 19:40:29
 */
@Repository
public interface CommonDictionaryMapper extends SuperMapper<CommonDictionary> {

    /**
     * 根据租户code删除字典类型
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from common_dictionary where tenant_code = #{tenantCode}")
    @InterceptorIgnore(tenantLine = "true")
    void deleteTenantDictionary(String tenantCode);
}
