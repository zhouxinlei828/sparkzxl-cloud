package com.github.sparkzxl.oauth.infrastructure.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.entity.RemoteData;
import com.github.sparkzxl.oauth.domain.model.aggregates.AuthUserBasicInfo;
import com.github.sparkzxl.oauth.domain.model.aggregates.StationBasicInfo;
import com.github.sparkzxl.oauth.domain.model.vo.AuthUserBasicVO;
import com.github.sparkzxl.oauth.infrastructure.entity.*;
import com.github.sparkzxl.oauth.infrastructure.enums.SexEnum;
import com.github.sparkzxl.oauth.interfaces.dto.user.*;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    @Mapping(target = "sex", expression = "java(convertSex(authUserPageDTO.getSex()))")
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

    /**
     * AuthUser转化为UserInfo
     *
     * @param authUser 用户
     * @return UserInfo
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "org.data", target = "org"),
            @Mapping(source = "station.data", target = "station"),
            @Mapping(source = "workDescribe", target = "signature"),
            @Mapping(source = "mobile", target = "phone")
    })
    UserInfo convertUserInfo(AuthUser authUser);

    /**
     * AuthUser转化为LoginAuthUser
     *
     * @param authUser
     * @return LoginAuthUser
     */
    LoginAuthUser convertLoginAuthUser(AuthUser authUser);

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

}
