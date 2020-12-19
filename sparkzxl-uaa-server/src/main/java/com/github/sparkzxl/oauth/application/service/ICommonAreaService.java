package com.github.sparkzxl.oauth.application.service;

import com.github.sparkzxl.oauth.infrastructure.entity.CommonArea;
import com.github.sparkzxl.database.base.service.SuperCacheService;
import com.github.sparkzxl.oauth.interfaces.dto.area.AreaQueryDTO;

import java.util.List;

/**
 * description: 地区表 服务类
 *
 * @author: zhouxinlei
 * @date: 2020-07-28 19:41:37
 */
public interface ICommonAreaService extends SuperCacheService<CommonArea> {

    /**
     * 查询地区信息
     *
     * @param areaQueryDTO 地区查询入参
     * @return List<CommonArea>
     */
    List<CommonArea> getAreaList(AreaQueryDTO areaQueryDTO);
}
