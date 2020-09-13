package com.github.sparkzxl.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.activiti.domain.repository.IActReModelRepository;
import com.github.sparkzxl.activiti.infrastructure.entity.ActReModel;
import com.github.sparkzxl.activiti.infrastructure.mapper.ActReModelMapper;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * description: 模型 仓储实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-25 11:17:47
 */
@Repository
public class ActReModelRepositoryImpl implements IActReModelRepository {

    @Autowired
    private ActReModelMapper actReModelMapper;

    @Override
    public PageInfo<ActReModel> actReModelList(String key, String name) {
        QueryWrapper<ActReModel> modelQueryWrapper = new QueryWrapper<>();
        Optional.ofNullable(key).ifPresent((value) -> modelQueryWrapper.lambda().eq(ActReModel::getKey, value));
        Optional.ofNullable(name).ifPresent((value) -> modelQueryWrapper.lambda().likeRight(ActReModel::getName, value));
        return PageInfoUtils.pageInfo(actReModelMapper.selectList(modelQueryWrapper));
    }
}
