package com.github.sparkzxl.authorization.infrastructure.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.sparkzxl.authorization.infrastructure.entity.CommonDictionaryItem;
import com.github.sparkzxl.database.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * description: 字典项 Mapper 接口
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:39:58
 */
@Repository
public interface CommonDictionaryItemMapper extends SuperMapper<CommonDictionaryItem> {

    /**
     * 根据租户code删除字典项
     *
     * @param tenantCode 租户code
     */
    @Delete("delete from common_dictionary_item where tenant_code = #{tenantCode}")
    @InterceptorIgnore(tenantLine = "true")
    void deleteTenantDictionaryItem(String tenantCode);
}
