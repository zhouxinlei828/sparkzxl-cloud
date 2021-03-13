package com.github.sparkzxl.authorization.interfaces.controller.auth;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.authorization.application.service.IAuthUserService;
import com.github.sparkzxl.authorization.domain.model.aggregates.MenuBasicInfo;
import com.github.sparkzxl.authorization.domain.model.vo.AuthUserBasicVO;
import com.github.sparkzxl.authorization.infrastructure.entity.AuthUser;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserPageDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserSaveDTO;
import com.github.sparkzxl.authorization.interfaces.dto.user.AuthUserUpdateDTO;
import com.github.sparkzxl.core.annotation.ResponseResult;
import com.github.sparkzxl.core.entity.AuthUserInfo;
import com.github.sparkzxl.database.base.controller.SuperCacheController;
import com.github.sparkzxl.database.dto.DeleteDTO;
import com.github.sparkzxl.database.dto.PageParams;
import com.github.sparkzxl.log.annotation.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: 用户管理
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:25:32
 */

@RestController
@RequestMapping("/user")
@ResponseResult
@WebLog
@Api(tags = "用户管理")
public class AuthUserController extends SuperCacheController<IAuthUserService, Long,
        AuthUser, AuthUserPageDTO, AuthUserSaveDTO, AuthUserUpdateDTO> {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PageInfo<AuthUser> page(PageParams<AuthUserPageDTO> params) {
        return baseService.getAuthUserPage(params);
    }

    @ApiOperation("用户信息详情查询")
    @GetMapping("/detail/{id}")
    public AuthUserDTO getAuthUser(@PathVariable("id") Long id) {
        return baseService.getAuthUser(id);
    }

    @Override
    public boolean save(AuthUserSaveDTO authUserSaveDTO) {
        return baseService.saveAuthUser(authUserSaveDTO);
    }

    @Override
    public boolean update(AuthUserUpdateDTO authUserUpdateDTO) {
        return baseService.updateAuthUser(authUserUpdateDTO);
    }

    @Override
    public boolean delete(DeleteDTO<Long> deleteDTO) {
        return baseService.deleteAuthUser(deleteDTO.getIds());
    }

    @Override
    public boolean handlerSave(AuthUserSaveDTO model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return true;
    }

    @ApiOperation(value = "用户路由菜单", notes = "用户路由菜单")
    @GetMapping("/routers")
    public List<MenuBasicInfo> routers(@ApiIgnore AuthUserInfo<Long> authUserInfo) {
        return baseService.routers(authUserInfo.getId());
    }

    @ApiOperation("获取用户基本信息")
    @GetMapping("/userinfo")
    public AuthUserBasicVO getAuthUserBasicInfo(@ApiIgnore AuthUserInfo<Long> authUserInfo) {
        return baseService.getAuthUserBasicInfo(authUserInfo.getId());
    }

    @ApiOperation("生成仿真数据")
    @GetMapping("/mockData")
    public boolean mockUserData() {
        return baseService.mockUserData();
    }

    @ApiOperation("Excel导入用户数据")
    @PostMapping("/import")
    public Integer importUserData(@RequestParam("file") MultipartFile multipartFile) {
        return baseService.importUserData(multipartFile);
    }

    @ApiOperation("Excel导出用户数据")
    @GetMapping("/export")
    public void exportUserData(AuthUserPageDTO authUserPageDTO, HttpServletResponse response) throws IOException {
        baseService.exportUserData(authUserPageDTO, response);
    }

}
