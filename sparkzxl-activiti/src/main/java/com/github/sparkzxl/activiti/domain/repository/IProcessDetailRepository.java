package com.github.sparkzxl.activiti.domain.repository;


import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessDetail;

import java.util.List;

/**
 * description: 流程详细节点 仓储类
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 15:33:56
 */
public interface IProcessDetailRepository {

    /**
     * 流程详细节点列表查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示大小
     * @param name     流程名称
     * @return List<ProcessDetail>
     */
    PageInfo<ProcessDetail> getProcessDetailList(int pageNum, int pageSize, String name);

    /**
     * 查询流程节点信息
     *
     * @param modelId 模型id
     * @return List<ProcessDetailDTO>
     */
    List<ProcessDetail> getProcessDetail(String modelId);
}
