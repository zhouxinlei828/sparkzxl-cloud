package com.sparksys.commons.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 *
 * @author: zhouxinlei
 * @date: 2020-07-07 19:25:43
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    /**
     * 更新所有字段
     *
     * @param var1
     * @return
     */
    int updateAllById(@Param("et") T var1);

    /**
     * 批量保存
     *
     * @param var1
     * @return
     */
    int insertBatchSomeColumn(List<T> var1);
}
