package com.github.sparkzxl.authorization.interfaces.controller.auth;


import cn.hutool.core.convert.Convert;
import com.github.sparkzxl.authorization.application.service.IAuthMenuService;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthMenu;
import com.github.sparkzxl.authorization.interfaces.dto.menu.AuthMenuPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.menu.AuthMenuSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.menu.AuthMenuUpdateDTO;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.entity.TreeEntity;
import com.github.sparkzxl.database.mybatis.conditions.Wraps;
import com.github.sparkzxl.database.mybatis.conditions.query.LbqWrapper;
import com.github.sparkzxl.database.utils.TreeUtil;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.core.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        model.setHidden(Convert.toBool(model.getHidden(), true));
        model.setNoKeepAlive(Convert.toBool(model.getNoKeepAlive(), true));
        model.setParentId(Convert.toLong(model.getParentId(), 0L));
        return true;
    }

    @ApiOperation(value = "保存菜单信息", notes = "保存菜单信息")
    @PostMapping("/saveMenu")
    public boolean saveMenu(@RequestBody AuthMenuSaveDTO authMenuSaveDTO) {
        return super.baseService.saveMenu(authMenuSaveDTO);
    }

    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/tree")
    public List<AuthMenu> allTree(@RequestParam(value = "label", required = false) String label) {
        LbqWrapper<AuthMenu> authMenuLbqWrapper = Wraps.lbQ();
        if (StringUtils.isNotEmpty(label)) {
            authMenuLbqWrapper.likeLeft(TreeEntity::getLabel, label);

        }
        authMenuLbqWrapper.orderByAsc(AuthMenu::getSortValue);
        List<AuthMenu> list = baseService.list(authMenuLbqWrapper);
        return TreeUtil.buildTree(list);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @DeleteMapping("/deleteMenu")
    public boolean deleteMenu(@RequestBody DeleteDTO deleteDTO) {
        return baseService.deleteMenu(deleteDTO.getIds());
    }
}
