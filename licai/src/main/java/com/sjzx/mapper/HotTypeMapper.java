package com.sjzx.mapper;

import com.sjzx.entity.HotType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 热点类型 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
public interface HotTypeMapper extends BaseMapper<HotType> {

    void updateUpdateTimeByDataDate(@Param("dataDate") String dataDate);
}
