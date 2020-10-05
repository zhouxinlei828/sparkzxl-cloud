package com.github.sparkzxl.oauth.interfaces.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.github.sparkzxl.core.enums.EnumeratorDeserializer;
import com.github.sparkzxl.database.annonation.InjectionField;
import com.github.sparkzxl.database.entity.RemoteData;
import com.github.sparkzxl.database.dto.PageDTO;
import com.github.sparkzxl.oauth.infrastructure.constant.DictionaryType;
import com.github.sparkzxl.oauth.infrastructure.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.github.sparkzxl.oauth.infrastructure.constant.InjectionFieldConstants.DICTIONARY_ITEM_CLASS;
import static com.github.sparkzxl.oauth.infrastructure.constant.InjectionFieldConstants.DICTIONARY_ITEM_METHOD;


/**
 * description: 用户信息
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AuthUserDTO分页查询对象", description = "AuthUserDTO分页查询对象")
public class AuthUserPageDTO extends PageDTO {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    private Boolean status;

    @ApiModelProperty(value = "民族")
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION)
    private RemoteData<String, String> nation;

}
