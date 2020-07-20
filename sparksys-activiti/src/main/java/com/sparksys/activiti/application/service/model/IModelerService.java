package com.sparksys.activiti.application.service.model;

/**
 * description: 模型控制 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:49:49
 */
public interface IModelerService {

    /**
     * 创建模型
     *
     * @param name 模型名称
     * @param key  模型key
     * @return String
     */
    String createModel(String name, String key);

    /**
     * 发布流程
     *
     * @param modelId 模型id
     * @return boolean
     */
    boolean publishProcess(String modelId);

    /**
     * 撤销流程定义
     *
     * @param modelId 模型id
     * @return boolean
     */
    boolean revokePublish(String modelId);

    /**
     * 删除流程实例
     *
     * @param modelId 模型id
     * @return boolean
     */
    boolean deleteProcessInstance(String modelId);
}
