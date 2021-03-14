package com.github.sparkzxl.authorization.interfaces.controller.auth;


import com.github.sparkzxl.authorization.application.service.IAuthResourceService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthResource;
import com.github.sparkzxl.authorization.interfaces.dto.resource.ResourceQueryDTO;
import com.github.sparkzxl.authorization.interfaces.dto.resource.ResourceSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.resource.ResourceUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.dto.PageParams;
import com.github.sparkzxl.log.annotation.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * description: 资源管理
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:39:30
 */
@RestController
@RequestMapping("/resource")
@ResponseResult
@WebLog
@Api(tags = "资源管理")
public class AuthResourceController extends SuperCacheController<IAuthResourceService, Long,
        AuthResource, ResourceSaveDTO, ResourceUpdateDTO, ResourceQueryDTO, Object> {


    @ApiOperation("查询用户可用的所有资源")
    @GetMapping("/visible")
    public List<AuthResource> visible(@ApiIgnore AuthUserInfo<Long> authUserInfo, ResourceQueryDTO resourceQueryDTO) {
        return baseService.findVisibleResource(authUserInfo.getId(), resourceQueryDTO);
    }

    @Override
    public boolean update(ResourceUpdateDTO authResourceUpdateDTO) {
        return baseService.updateResource(authResourceUpdateDTO);
    }

    @Override
    public void handlerQueryParams(PageParams<ResourceQueryDTO> params) {
        ResourceQueryDTO paramsModel = params.getModel();
        if (StringUtils.isEmpty(paramsModel.getCode())) {
            paramsModel.setCode(null);
        }
        if (StringUtils.isEmpty(paramsModel.getName())) {
            paramsModel.setName(null);
        }
    }

    @Override
    public boolean delete(DeleteDTO<Long> deleteDTO) {
        return baseService.deleteResource(deleteDTO.getIds());
    }
}
