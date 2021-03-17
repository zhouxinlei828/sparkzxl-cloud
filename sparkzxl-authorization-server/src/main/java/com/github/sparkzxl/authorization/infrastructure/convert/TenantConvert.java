package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.TenantInfo;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.tenant.TenantUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: SpTenant 对象Convert
 *
 * @author charles.zhou
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    /**
     * tenantSaveDTO转换为SpTenant
     *
     * @param tenantSaveDTO 租户保存对象
     * @return SpTenant
     */
    TenantInfo convertTenant(TenantSaveDTO tenantSaveDTO);

    /**
     * tenantUpdateDTO转换为SpTenant
     *
     * @param tenantUpdateDTO 租户更新对象
     * @return SpTenant
     */
    TenantInfo convertTenant(TenantUpdateDTO tenantUpdateDTO);
}
