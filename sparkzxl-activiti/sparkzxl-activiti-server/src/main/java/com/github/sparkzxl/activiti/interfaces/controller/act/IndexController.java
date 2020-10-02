package com.github.sparkzxl.activiti.interfaces.controller.act;

import lombok.AllArgsConstructor;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-10-02 10:35:45
*/
@AllArgsConstructor
@Controller
public class IndexController {

    private final RepositoryService repositoryService;


    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("modelList", repositoryService.createModelQuery().list());
        return modelAndView;
    }

    @RequestMapping("modelList")
    public ModelAndView modelList(ModelAndView modelAndView) {
        modelAndView.setViewName("modelList");
        return modelAndView;
    }

    /**
     * 跳转编辑器页面
     *
     * @return String
     */
    @GetMapping("editor")
    public String editor() {
        return "modeler";
    }
}
