package com.github.sparkzxl.authorization.infrastructure.filter;

import com.github.sparkzxl.core.context.BaseContextHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 多租户模式登录前置过滤器
 *
 * @author: zhouxinlei
 * @date: 2021-02-25 10:53:03
 */
@Component
public class TenantLoginPreFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authenticationFormUrl = "/authentication/form";
        if (StringUtils.equals(request.getRequestURI(), authenticationFormUrl)) {
            String tenantCode = request.getParameter("tenantCode");
            BaseContextHandler.setTenant(tenantCode);
        }
        chain.doFilter(request, response);
    }
}
