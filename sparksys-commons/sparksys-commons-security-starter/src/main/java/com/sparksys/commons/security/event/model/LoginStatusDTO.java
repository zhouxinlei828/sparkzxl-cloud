package com.sparksys.commons.security.event.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.sparksys.commons.core.entity.AddressInfo;
import com.sparksys.commons.core.utils.AddressUtil;
import com.sparksys.commons.security.entity.AuthToken;
import com.sparksys.commons.web.utils.HttpUtils;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 登录状态DTO
 *
 * @author zuihou
 * @date 2020年03月18日17:25:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LoginStatusDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    /***
     * 用户id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;

    /**
     * 登录类型
     */
    private Type type;
    /**
     * 登录描述
     */
    private String description;

    /**
     * 登录浏览器
     */
    private String ua;
    /**
     * 登录IP
     */
    private String ip;
    /**
     * 登录地址
     */
    private String location;

    public static LoginStatusDTO success(Long id) {
        LoginStatusDTO loginStatus = LoginStatusDTO.builder()
                .id(id)
                .type(Type.SUCCESS).description("登录成功")
                .build().setInfo();
        return loginStatus;
    }

    public static LoginStatusDTO success(String account) {
        LoginStatusDTO loginStatus = LoginStatusDTO.builder()
                .account(account)
                .type(Type.SUCCESS).description("登录成功")
                .build().setInfo();
        return loginStatus;
    }

    public static LoginStatusDTO fail(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id)
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO fail(String account, String description) {
        return LoginStatusDTO.builder()
                .account(account)
                .type(Type.FAIL).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO pwdError(Long id, String description) {
        return LoginStatusDTO.builder()
                .id(id)
                .type(Type.PWD_ERROR).description(description)
                .build().setInfo();
    }

    private LoginStatusDTO setInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return this;
        }
        HttpServletRequest request = HttpUtils.getRequest();
        String ua = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String ip = ServletUtil.getClientIP(request);
        AddressInfo addressInfo = AddressUtil.getAddress(ip);
        this.ua = ua;
        this.ip = ip;
        this.location = addressInfo.getRegion().concat(" ").concat(addressInfo.getCity());
        return this;
    }

    @Getter
    public enum Type {
        SUCCESS,
        PWD_ERROR,
        FAIL;
    }

}
