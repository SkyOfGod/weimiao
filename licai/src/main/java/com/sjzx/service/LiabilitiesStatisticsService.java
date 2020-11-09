package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.LiabilitiesStatistics;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.LiabilitiesStatisticsVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 资产负债表统计指标 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-03
 */
public interface LiabilitiesStatisticsService extends IService<LiabilitiesStatistics> {

    void statistics(Integer companyId, Integer year, Integer reportType);

    EasyUIResult<LiabilitiesStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo);

    void exportData(HttpServletResponse response, LiabilitiesStatisticsInputVO vo);

}
