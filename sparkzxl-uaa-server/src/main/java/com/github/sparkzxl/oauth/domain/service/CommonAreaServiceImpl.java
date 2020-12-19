package com.github.sparkzxl.oauth.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.sparkzxl.database.utils.TreeUtil;
import com.github.sparkzxl.oauth.application.service.ICommonAreaService;
import com.github.sparkzxl.oauth.infrastructure.constant.CacheConstant;
import com.github.sparkzxl.oauth.infrastructure.entity.CommonArea;
import com.github.sparkzxl.oauth.infrastructure.mapper.CommonAreaMapper;
import com.github.sparkzxl.database.base.service.impl.AbstractSuperCacheServiceImpl;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaQueryDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 地区表 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:43:36
 */
@Service
public class CommonAreaServiceImpl extends AbstractSuperCacheServiceImpl<CommonAreaMapper, CommonArea> implements ICommonAreaService {

    @Override
    public List<CommonArea> getAreaList(AreaQueryDTO areaQueryDTO) {
        LambdaQueryWrapper<CommonArea> areaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(areaQueryDTO.getCode())) {
            areaLambdaQueryWrapper.eq(CommonArea::getCode, areaQueryDTO.getCode());
        }
        if (StringUtils.isNotEmpty(areaQueryDTO.getLabel())){
            areaLambdaQueryWrapper.likeRight(CommonArea::getLabel, areaQueryDTO.getLabel());
        }
        List<CommonArea> commonAreaList = list(areaLambdaQueryWrapper);
        return TreeUtil.buildTree(commonAreaList);
    }

    @Override
    protected String getRegion() {
        return CacheConstant.AREA;
    }
}
