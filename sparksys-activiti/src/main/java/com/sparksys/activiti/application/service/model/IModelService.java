package com.sparksys.activiti.application.service.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-07-17 14:30:32
 */
public interface IModelService {

    /**
     * 获取流程json信息
     *
     * @param modelId 流程模型id
     * @return ObjectNode
     */
    ObjectNode getEditorJson(String modelId);

    /**
     * 保存流程
     *
     * @param modelId
     * @param name
     * @param description
     * @param json_xml
     * @param svg_xml
     * @return
     */
    boolean saveModel(String modelId, String name, String description, String json_xml, String svg_xml);

    /**
     * 获取流程json文件
     *
     * @return
     */
    String getProcessJson();

}
