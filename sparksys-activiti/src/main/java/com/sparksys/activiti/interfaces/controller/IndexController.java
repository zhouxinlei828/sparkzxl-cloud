package com.sparksys.activiti.interfaces.controller;

import com.sparksys.log.annotation.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description: 登录控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 17:32:23
 */
@Controller
@WebLog
@Slf4j
@Api(tags = "首页管理")
public class IndexController {

    @ApiOperation("跳转首页")
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request){
        this.systemInfo(request);
        return "index";
    }

    @ApiOperation("跳转编辑器页面")
    @RequestMapping(value = "/editor")
    public String editor() {
        return "modeler";
    }

    public void systemInfo(HttpServletRequest request) {
        Map<String, String> sysMap = new HashMap<>();
        try {
            InetAddress addr = InetAddress.getLocalHost();
            Properties props = System.getProperties();
            sysMap.put("ip", addr.getHostAddress());
            sysMap.put("name", addr.getHostName());
            sysMap.put("systemName", props.getProperty("os.name"));
            sysMap.put("systemUserName", props.getProperty("user.name"));
            sysMap.put("JavaVersion", "Java "+props.getProperty("java.version"));
            sysMap.put("tomcat", (request).getServletContext().getServerInfo());
            request.setAttribute("system", sysMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
