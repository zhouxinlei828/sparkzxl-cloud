package com.github.sparkzxl.authority.infrastructure.repository;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.sparkzxl.core.utils.MapHelper;
import com.github.sparkzxl.database.utils.PageInfoUtils;
import com.github.sparkzxl.authority.domain.repository.ICoreStationRepository;
import com.github.sparkzxl.authority.infrastructure.entity.CoreStation;
import com.github.sparkzxl.authority.infrastructure.mapper.CoreStationMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 岗位 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:32:55
 */
@Repository
public class CoreStationRepository implements ICoreStationRepository {

    private final CoreStationMapper coreStationMapper;

    public CoreStationRepository(CoreStationMapper coreStationMapper) {
        this.coreStationMapper = coreStationMapper;
    }

    @Override
    public Map<Serializable, Object> findStationNameByIds(Set<Serializable> ids) {
        List<CoreStation> stations = getStations(ids);
        return MapHelper.uniqueIndex(stations, CoreStation::getId, (station) -> station);
    }


    private List<CoreStation> getStations(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(Collectors.toList());

        List<CoreStation> list;
        int size = 1000;
        if (idList.size() <= size) {
            list = idList.stream().map(this.coreStationMapper::selectById).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            list = this.coreStationMapper.selectList(new QueryWrapper<CoreStation>().lambda().in(CoreStation::getId, idList).eq(CoreStation::getStatus, true));
        }
        return list;
    }

    @Override
    public PageInfo<CoreStation> getStationPageList(int pageNum, int pageSize, String name, Long orgId) {
        QueryWrapper<CoreStation> stationQueryWrapper = new QueryWrapper<>();
        Optional.ofNullable(name).ifPresent(value -> stationQueryWrapper.likeRight("name", value));
        Optional.ofNullable(orgId).ifPresent(value -> stationQueryWrapper.eq("org_id", value));
        PageHelper.startPage(pageNum, pageSize);
        return PageInfoUtils.pageInfo(coreStationMapper.selectList(stationQueryWrapper));
    }
}
