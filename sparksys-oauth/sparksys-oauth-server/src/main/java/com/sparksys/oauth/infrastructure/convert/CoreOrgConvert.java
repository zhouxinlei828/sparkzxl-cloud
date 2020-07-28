package com.sparksys.oauth.infrastructure.convert;

import com.sparksys.oauth.infrastructure.entity.CoreOrg;
import com.sparksys.oauth.interfaces.dto.org.OrgSaveDTO;
import com.sparksys.oauth.interfaces.dto.org.OrgUpdateDTO;
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

    CoreOrg convertCoreOrg(OrgSaveDTO orgSaveDTO);

    CoreOrg convertCoreOrg(OrgUpdateDTO orgUpdateDTO);
}
