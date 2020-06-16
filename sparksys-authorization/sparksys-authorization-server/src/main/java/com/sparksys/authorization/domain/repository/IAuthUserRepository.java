package com.sparksys.authorization.domain.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.authorization.infrastructure.po.AuthUserDO;

/**
 * description: 用户仓储层
 *
 * @Author zhouxinlei
 * @Date 2020-06-05 20:39:15
 */
public interface IAuthUserRepository {


    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    AuthUserDO selectById(Long id);

    /**
     * 根据账户查询用户信息
     *
     * @param username
     * @return
     */
    AuthUserDO selectByUserName(String username);

    /**
     * 保存用户信息
     *
     * @param authUserDO
     * @return
     */
    boolean saveAuthUser(AuthUserDO authUserDO);

    /**
     * 更新用户信息
     *
     * @param authUserDO
     * @return
     */
    boolean updateAuthUser(AuthUserDO authUserDO);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    boolean deleteAuthUser(Long id);

    /**
     * 分页查询用户信息
     *
     * @param authUserDOPage
     * @param name
     * @return
     */
    Page<AuthUserDO> listByPage(Page authUserDOPage,String name);
}
