package com.sjzx.service;

import com.sjzx.entity.CashFlowStatistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.CashFlowStatisticsVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 合并现金流量表指标 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-04
 */
public interface CashFlowStatisticsService extends IService<CashFlowStatistics> {

    EasyUIResult<CashFlowStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo);

    void statistics(Integer companyId, Integer year, Integer reportType);

    void exportData(HttpServletResponse response, LiabilitiesStatisticsInputVO vo);
}
