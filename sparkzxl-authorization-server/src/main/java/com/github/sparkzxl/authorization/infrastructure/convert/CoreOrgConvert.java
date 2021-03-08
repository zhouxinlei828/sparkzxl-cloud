package com.github.sparkzxl.authorization.infrastructure.convert;

import com.github.sparkzxl.authorization.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.authorization.interfaces.dto.org.OrgSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.org.OrgUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description: CoreOrg对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface CoreOrgConvert {

    CoreOrgConvert INSTANCE = Mappers.getMapper(CoreOrgConvert.class);

    /**
     * OrgSaveDTO转化为CoreOrg
     *
     * @param orgSaveDTO 组织保存对象
     * @return CoreOrg
     */
    CoreOrg convertCoreOrg(OrgSaveDTO orgSaveDTO);

    /**
     * OrgUpdateDTO转化为CoreOrg
     *
     * @param orgUpdateDTO 组织更新对象
     * @return CoreOrg
     */
    CoreOrg convertCoreOrg(OrgUpdateDTO orgUpdateDTO);
}
