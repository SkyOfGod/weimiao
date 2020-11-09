package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.LiabilitiesStatisticsAddVO;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.ProfitStatisticsVO;
import com.sjzx.service.ProfitStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 合并利润表统计 前端控制器
 * </p>
 *
 * @author
 * @since 202-03
 */
@RestController
@Api(tags = "合并利润表统计相关管理接口")
@RequestMapping("/profitStatistics")
public class ProfitStatisticsController {

    @Autowired
    private ProfitStatisticsService profitStatisticsService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<ProfitStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo) {
        return profitStatisticsService.listPage(vo);
    }

    @PostMapping("/statistics")
    @ApiOperation(value = "统计")
    public Response statistics(LiabilitiesStatisticsAddVO vo) {
        profitStatisticsService.statistics(vo.getCompanyId(), vo.getYear(), vo.getReportType());
        return Response.success();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出数据")
    public void exportData(LiabilitiesStatisticsInputVO vo, HttpServletResponse response) {
        profitStatisticsService.exportData(response, vo);
    }

}
