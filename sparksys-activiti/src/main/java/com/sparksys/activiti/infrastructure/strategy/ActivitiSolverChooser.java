package com.sparksys.activiti.infrastructure.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: 流程驱动选择器
 *
 * @author: zhouxinlei
 * @date: 2020-07-20 16:14:39
 */
@Component
public class ActivitiSolverChooser implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<Integer, AbstractActivitiSolver> activitiSolverMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void register() {
        Map<String, AbstractActivitiSolver> solverMap = applicationContext.getBeansOfType(AbstractActivitiSolver.class);
        for (AbstractActivitiSolver solver : solverMap.values()) {
            activitiSolverMap.put(solver.support(), solver);
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
