package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.ProfitStatistics;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.ProfitStatisticsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 合并利润表统计 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-11-03
 */
public interface ProfitStatisticsMapper extends BaseMapper<ProfitStatistics> {

    IPage<ProfitStatisticsVO> listPage(IPage<ProfitStatisticsVO> iPage,@Param("vo")  LiabilitiesStatisticsInputVO vo);

    List<ProfitStatisticsVO> getList(@Param("vo") LiabilitiesStatisticsInputVO vo);
}
