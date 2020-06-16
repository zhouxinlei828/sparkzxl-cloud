package com.sparksys.commons.security.component;

import com.sparksys.commons.core.entity.AuthUser;
import com.sparksys.commons.security.entity.AdminUserDetails;
import com.sparksys.commons.security.registry.SecurityRegistry;
import com.sparksys.commons.security.service.AbstractSecurityAuthDetailService;
import com.sparksys.commons.core.utils.jwt.JwtTokenUtil;
import com.sparksys.commons.web.utils.HttpResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: JWT登录授权过滤器
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:34:44
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AbstractSecurityAuthDetailService abstractSecurityAuthDetailService;

    @Autowired
    private SecurityRegistry securityRegistry;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) {
        try {
            log.info("请求地址：{}", request.getRequestURI());
            if (!securityRegistry.isIgnoreToken(request.getRequestURI())) {
                String accessToken = HttpResponseUtils.getAuthHeader(request);
                if (StringUtils.isNotEmpty(accessToken)) {
                    String username = JwtTokenUtil.getUserNameFromToken(accessToken);
                    log.info("checking username:{}", username);
                    AuthUser authUser = abstractSecurityAuthDetailService.getUserInfo(accessToken);
                    if (StringUtils.equals(authUser.getAccount(), username)) {
                        AdminUserDetails adminUserDetails = abstractSecurityAuthDetailService.getAdminUserDetail(username);
                        if (JwtTokenUtil.validateToken(accessToken, adminUserDetails.getUsername())) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(adminUserDetails, null, adminUserDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            log.info("authenticated user:{}", username);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            HttpResponseUtils.unauthorized(response);
        }
    }
}
