package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.CashFlowStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.CashFlowStatisticsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 合并现金流量表指标 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-11-04
 */
public interface CashFlowStatisticsMapper extends BaseMapper<CashFlowStatistics> {

    IPage<CashFlowStatisticsVO> listPage(IPage<CashFlowStatisticsVO> iPage, @Param("vo") LiabilitiesStatisticsInputVO vo);

    List<CashFlowStatisticsVO> getList(@Param("vo") LiabilitiesStatisticsInputVO vo);
}
