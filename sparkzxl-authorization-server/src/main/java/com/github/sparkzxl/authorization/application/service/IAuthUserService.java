package com.github.sparkzxl.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.domain.model.vo.AuthUserBasicVO;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.database.dto.PageParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: 用户查询 服务类
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:22:23
 */
public interface IAuthUserService extends SuperCacheService<AuthUser> {

    /**
     * 获取全局用户信息
     *
     * @param username 用户账户
     * @return AuthUserInfo<Long>
     */
    AuthUserInfo<Long> getAuthUserInfo(String username);

    /**
     * 根据账户查询用户信息
     *
     * @param username 账户
     * @return AuthUser
     */
    AuthUser getByAccount(String username);


    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return AuthUserDTO
     */
    AuthUserDTO getAuthUser(Long id);

    /**
     * 更新用户组织
     *
     * @param id 组织id
     */
    void deleteOrgId(Long id);


    /**
     * 获取用户分页
     *
     * @param params 分页入参
     * @return PageInfo<AuthUser>
     */
    PageInfo<AuthUser> getAuthUserPage(PageParams<AuthUserPageDTO> params);

    /**
     * 保存用户信息
     *
     * @param authUserSaveDTO AuthUserSaveDTO保存对象
     * @return boolean
     */
    boolean saveAuthUser(AuthUserSaveDTO authUserSaveDTO);

    /**
     * 修改用户信息
     *
     * @param authUserUpdateDTO AuthUserSaveDTO修改对象
     * @return boolean
     */
    boolean updateAuthUser(AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 批量更新用户组织
     *
     * @param orgIds 组织ids
     */
    void deleteOrgIds(List<Long> orgIds);

    /**
     * 生成仿真数据
     *
     * @return boolean
     */
    boolean mockUserData();

    /**
     * 获取登录用户全量信息
     *
     * @param userId 用户id
     * @return AuthUserBasicVO
     */
    AuthUserBasicVO getAuthUserBasicInfo(Long userId);

    /**
     * 用户菜单
     *
     * @param userId 用户id
     * @return List<MenuBasicInfo>
     */
    List<MenuBasicInfo> routers(Long userId);

    /**
     * Excel导入用户数据
     *
     * @param multipartFile 文件
     * @return Integer
     */
    Integer importUserData(MultipartFile multipartFile);

    /**
     * 导出Excel用户数据
     *
     * @param authUserPageDTO 用户查询参数
     * @param response        response
     * @throws IOException 异常
     */
    void exportUserData(AuthUserPageDTO authUserPageDTO, HttpServletResponse response) throws IOException;

    /**
     * 删除用户信息
     *
     * @param ids ids
     * @return boolean
     */
    boolean deleteAuthUser(List<Long> ids);
}
