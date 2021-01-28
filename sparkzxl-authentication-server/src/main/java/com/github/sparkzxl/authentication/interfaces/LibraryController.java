package com.github.sparkzxl.authentication.interfaces;

import com.github.sparkzxl.core.utils.RequestContextHolderUtils;
import com.github.sparkzxl.log.annotation.WebLog;
import com.github.sparkzxl.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@ResponseResult
@WebLog
@Api(tags = "书籍管理")
@Slf4j
public class LibraryController {

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @GetMapping(value = "/books")
    public String getBooks() {
        return "books";
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model) {
        return getKeycloakSecurityContext().getIdToken().getGivenName();
    }

    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        RequestContextHolderUtils.getRequest().logout();
        return "redirect:/";
    }


    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) RequestContextHolderUtils.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
