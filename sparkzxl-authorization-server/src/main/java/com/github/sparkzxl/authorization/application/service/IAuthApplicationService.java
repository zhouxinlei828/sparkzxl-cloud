package com.github.sparkzxl.authorization.application.service;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthApplication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationUpdateDTO;
import com.github.sparkzxl.database.dto.DeleteDTO;

/**
 * description: 租户客户端服务类
 *
 * @author: zhouxinlei
 * @date: 2021-02-20 09:44:35
 */
public interface IAuthApplicationService extends IService<AuthApplication> {

    /**
     * 保存应用客户端信息
     *
     * @param authApplicationSaveDTO 应用保存DTO
     * @return boolean
     */
    boolean saveApplication(AuthApplicationSaveDTO authApplicationSaveDTO);

    /**
     * 获取客户端分页
     *
     * @param authApplicationPageDTO 应用分页DTO
     * @return PageInfo<OauthClientDetails>
     */
    PageInfo<AuthApplication> listPage(AuthApplicationPageDTO authApplicationPageDTO);

    /**
     * 删除客户端
     *
     * @param deleteDTO 删除对象
     * @return boolean
     */
    boolean deleteApplication(DeleteDTO deleteDTO);

    /**
     * 更新客户端信息
     *
     * @param authApplicationUpdateDTO 应用客户端更新DTO
     * @return boolean
     */
    boolean updateApplication(AuthApplicationUpdateDTO authApplicationUpdateDTO);

}
