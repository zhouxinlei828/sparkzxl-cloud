package com.github.sparkzxl.activiti.application.service.process;

import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.infrastructure.entity.ProcessDetail;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailDTO;
import com.github.sparkzxl.activiti.interfaces.dto.process.ProcessDetailPageDTO;
import com.github.sparkzxl.database.base.service.SuperCacheService;

import java.util.List;

/**
 * description: 流程详细节点 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-21 14:22:03
 */
public interface IProcessDetailService extends SuperCacheService<ProcessDetail> {

    /**
     * 查询流程节点信息
     *
     * @param modelId 模型id
     * @return List<ProcessDetailDTO>
     */
    List<ProcessDetailDTO> getProcessDetail(String modelId);

    /**
     * 分页查询流程列表
     *
     * @param processDetailPageDTO 分页查询参数
     * @return PageInfo<ProcessDetail>
     */
    PageInfo<ProcessDetail> getProcessDetailList(ProcessDetailPageDTO processDetailPageDTO);
}
