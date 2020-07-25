package com.sparksys.activiti.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.activiti.domain.repository.IActReModelRepository;
import com.sparksys.activiti.infrastructure.entity.ActReModel;
import com.sparksys.activiti.infrastructure.mapper.ActReModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<ActReModel> actReModelList(String key, String name) {
        QueryWrapper<ActReModel> modelQueryWrapper = new QueryWrapper<>();
        Optional<String> keyOptional = Optional.ofNullable(key);
        Optional<String> nameOptional = Optional.ofNullable(name);
        keyOptional.ifPresent((value) -> modelQueryWrapper.eq("KEY_", value));
        nameOptional.ifPresent((value) -> modelQueryWrapper.likeRight("NAME_", value));
        return actReModelMapper.selectList(modelQueryWrapper);
    }
}
