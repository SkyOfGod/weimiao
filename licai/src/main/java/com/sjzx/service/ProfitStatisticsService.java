package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.ProfitStatistics;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.ProfitStatisticsVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 合并利润表统计 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-03
 */
public interface ProfitStatisticsService extends IService<ProfitStatistics> {

    EasyUIResult<ProfitStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo);

    void statistics(Integer companyId, Integer year, Integer reportType);

    void exportData(HttpServletResponse response, LiabilitiesStatisticsInputVO vo);

    ProfitStatistics getRecentYearData(Integer companyId);
}
