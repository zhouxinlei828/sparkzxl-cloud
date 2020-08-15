package com.sparksys.authority.interfaces.controller.auth;


import cn.hutool.core.convert.Convert;
import com.sparksys.authority.application.service.IAuthMenuService;
import com.sparksys.authority.infrastructure.entity.AuthMenu;
import com.sparksys.authority.interfaces.dto.menu.AuthMenuPageDTO;
import com.sparksys.authority.interfaces.dto.menu.AuthMenuSaveDTO;
import com.sparksys.authority.interfaces.dto.menu.AuthMenuUpdateDTO;
import com.sparksys.database.base.controller.SuperCacheController;
import com.sparksys.database.mybatis.conditions.Wraps;
import com.sparksys.database.utils.TreeUtil;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 资源管理
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:39:30
 */
@RestController
@RequestMapping("/menu")
@ResponseResult
@WebLog
@Api(tags = "菜单管理")
public class AuthMenuController extends SuperCacheController<IAuthMenuService, Long,
        AuthMenu, AuthMenuPageDTO, AuthMenuSaveDTO, AuthMenuUpdateDTO> {


    @Override
    public boolean handlerSave(AuthMenuSaveDTO model) {
        model.setIsEnable(Convert.toBool(model.getIsEnable(), true));
        model.setIsPublic(Convert.toBool(model.getIsPublic(), false));
        model.setParentId(Convert.toLong(model.getParentId(), 0L));
        return true;
    }

    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/tree")
    public List<AuthMenu> allTree() {
        List<AuthMenu> list = baseService.list(Wraps.<AuthMenu>lbQ().orderByAsc(AuthMenu::getSortValue));
        return TreeUtil.buildTree(list);
    }

}
