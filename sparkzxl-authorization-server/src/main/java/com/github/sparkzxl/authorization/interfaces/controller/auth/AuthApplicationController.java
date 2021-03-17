package com.github.sparkzxl.authorization.interfaces.controller.auth;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.IAuthApplicationService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthApplication;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationQueryDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.application.AuthApplicationUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.dto.PageParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 应用客户端管理
 *
 * @author charles.zhou
 * @date   2021-02-02 11:31:18
 */
@RestController
@Api(tags = "应用客户端管理")
@ResponseResult
@RequestMapping("/application")
public class AuthApplicationController extends SuperCacheController<IAuthApplicationService, Long,
        AuthApplication, AuthApplicationSaveDTO, AuthApplicationUpdateDTO, AuthApplicationQueryDTO, Object> {

    @Override
    public boolean save(AuthApplicationSaveDTO authApplicationSaveDTO) {
        return baseService.saveApplication(authApplicationSaveDTO);
    }

    @Override
    public boolean update(AuthApplicationUpdateDTO authApplicationUpdateDTO) {
        return baseService.updateApplication(authApplicationUpdateDTO);
    }

    @Override
    public boolean delete(DeleteDTO<Long> deleteDTO) {
        return baseService.deleteApplication(deleteDTO.getIds());
    }

    @Override
    public PageInfo<AuthApplication> page(PageParams<AuthApplicationQueryDTO> params) {
        return baseService.listPage(params);
    }
}
