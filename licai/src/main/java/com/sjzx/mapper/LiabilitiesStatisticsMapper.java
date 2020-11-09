package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.LiabilitiesStatistics;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.LiabilitiesStatisticsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资产负债表统计指标 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-11-03
 */
public interface LiabilitiesStatisticsMapper extends BaseMapper<LiabilitiesStatistics> {

    IPage<LiabilitiesStatisticsVO> listPage(IPage<LiabilitiesStatisticsVO> iPage, @Param("vo") LiabilitiesStatisticsInputVO vo);

    List<LiabilitiesStatisticsVO> getList(@Param("vo") LiabilitiesStatisticsInputVO vo);
}
