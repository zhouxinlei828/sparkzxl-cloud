package com.sparksys.file.infrastructure.convert;

import com.sparksys.file.domain.model.FileMaterialDO;
import com.sparksys.file.interfaces.dto.FileMaterialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * description：FileMaterial对象Convert
 *
 * @author zhouxinlei
 * @date  2020/6/16 0016
 */
@Mapper
public interface FileMaterialConvert {

    FileMaterialConvert INSTANCE = Mappers.getMapper(FileMaterialConvert.class);

    FileMaterialDTO convertFileMaterialDTO(FileMaterialDO fileMaterialDO);
}
