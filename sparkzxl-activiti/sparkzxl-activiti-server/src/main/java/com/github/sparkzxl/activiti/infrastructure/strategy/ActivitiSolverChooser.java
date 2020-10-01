package com.github.sparkzxl.activiti.infrastructure.strategy;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * description: 流程驱动选择器
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:14:39
 */
@Component
public class ActivitiSolverChooser implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Map<Integer, AbstractActivitiSolver> activitiSolverMap = Maps.newConcurrentMap();


    @PostConstruct
    public void register() {
        Map<String, AbstractActivitiSolver> solverMap = applicationContext.getBeansOfType(AbstractActivitiSolver.class);
        for (AbstractActivitiSolver solver : solverMap.values()) {
            for (Integer support : solver.supports()) {
                activitiSolverMap.put(support, solver);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public AbstractActivitiSolver chooser(Integer actType) {
        return activitiSolverMap.get(actType);
    }
}
