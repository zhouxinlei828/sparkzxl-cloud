package com.sparksys.activiti.application.service.act;

import com.github.pagehelper.PageInfo;
import com.sparksys.activiti.infrastructure.entity.ActReModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sparksys.activiti.interfaces.dto.act.ModelPageDTO;
import com.sparksys.database.service.SuperCacheService;

/**
 * description: 模型 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-25 11:14:40
 */
public interface IActReModelService extends SuperCacheService<ActReModel> {


    /**
     * 分页查询模型列表
     *
     * @param modelPageDTO 模型分页参数
     * @return PageInfo<ActReModel>
     */
    PageInfo<ActReModel> actReModelList(ModelPageDTO modelPageDTO);
}
