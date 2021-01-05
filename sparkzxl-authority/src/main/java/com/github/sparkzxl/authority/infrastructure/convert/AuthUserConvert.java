package com.github.sparkzxl.authority.infrastructure.convert;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authority.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authority.infrastructure.entity.CoreOrg;
import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authority.interfaces.dto.user.*;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.entity.RemoteData;
import com.github.sparkzxl.authority.domain.model.aggregates.AuthUserBasicInfo;
import com.github.sparkzxl.authority.domain.model.aggregates.StationBasicInfo;
import com.github.sparkzxl.authority.domain.model.vo.AuthUserBasicVO;
import com.github.sparkzxl.authority.infrastructure.enums.SexEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * description: AuthUser对象Convert
 *
 * @author zhouxinlei
 * @date 2020-06-05 21:28:06
 */
@Mapper
public interface AuthUserConvert {

    AuthUserConvert INSTANCE = Mappers.getMapper(AuthUserConvert.class);

    /**
     * AuthUser转化为AuthUserInfo
     *
     * @param authUser
     * @return AuthUserInfo
     */
    AuthUserInfo<Long> convertAuthUserInfo(AuthUser authUser);

    /**
     * AuthUserSaveDTO转化为 AuthUser
     *
     * @param authUserSaveDTO AuthUserSaveDTO保存对象
     * @return AuthUser
     */
    @Mapping(target = "sex", expression = "java(convertSex(authUserSaveDTO.getSex()))")
    AuthUser convertAuthUser(AuthUserSaveDTO authUserSaveDTO);

    /**
     * AuthUserUpdateDTO转化为AuthUser
     *
     * @param authUserUpdateDTO AuthUserUpdateDTO更新对象
     * @return AuthUser
     */
    AuthUser convertAuthUser(AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * AuthUserStatusDTO转化为AuthUser
     *
     * @param authUserStatusDTO AuthUserStatusDTO状态修改对象
     * @return AuthUser
     */
    AuthUser convertAuthUser(AuthUserStatusDTO authUserStatusDTO);

    /**
     * AuthUserPageDTO转化为AuthUser
     *
     * @param authUserPageDTO AuthUserDTO分页查询对象
     * @return AuthUser
     */
    @Mappings({
            @Mapping(target = "sex", expression = "java(convertSex(authUserPageDTO.getSex()))"),
            @Mapping(target = "org", expression = "java(convertOrgRemoteData(authUserPageDTO.getOrgId()))")
    })
    AuthUser convertAuthUser(AuthUserPageDTO authUserPageDTO);

    /**
     * 转换sex枚举
     *
     * @param sex
     * @return
     */
    default SexEnum convertSex(Integer sex) {
        if (ObjectUtils.isNotEmpty(sex)) {
            return SexEnum.getEnum(sex);
        }
        return null;
    }

    /**
     * AuthUser转化为AuthUserDTO
     *
     * @param authUser
     * @return AuthUserDTO
     */
    AuthUserDTO convertAuthUserDTO(AuthUser authUser);

    /**
     * PageInfo<AuthUser> 转化为PageInfo<AuthUserDTO>
     *
     * @param authUserPageInfo 分页对象
     * @return PageInfo<AuthUserDTO>
     */
    PageInfo<AuthUserDTO> convertAuthUserDTO(PageInfo<AuthUser> authUserPageInfo);

    AuthUserBasicVO convertAuthUserBasicVO(AuthUserBasicInfo authUserBasicInfo);

    /**
     * 转换AuthUser为AuthUserBasicInfo
     * @param authUser 用户实体类
     * @return AuthUserBasicInfo
     */
    @Mappings({
            @Mapping(target = "org", ignore = true),
            @Mapping(target = "station", expression = "java(buildStation(authUser.getStation()))")
    })
    AuthUserBasicInfo convertAuthUserBasicInfo(AuthUser authUser);

    /**
     * 构建职位
     * @param station 职位实体类
     * @return StationBasicInfo
     */
    default StationBasicInfo buildStation(RemoteData<Long, CoreStation> station) {
        if (ObjectUtils.isNotEmpty(station) && ObjectUtils.isNotEmpty(station.getData())) {
            StationBasicInfo stationBasicInfo = new StationBasicInfo();
            stationBasicInfo.setId(station.getKey());
            stationBasicInfo.setName(station.getData().getName());
            return stationBasicInfo;
        }
        return null;
    }

    default RemoteData<Long,CoreOrg> convertOrgRemoteData(Long orgId) {
        if (ObjectUtils.isNotEmpty(orgId)) {
            return new RemoteData<>(orgId);
        }
        return null;
    }

}
