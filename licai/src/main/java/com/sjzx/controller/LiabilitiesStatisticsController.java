package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.LiabilitiesStatisticsAddVO;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.LiabilitiesStatisticsVO;
import com.sjzx.service.LiabilitiesStatisticsService;
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
 * 资产负债表统计指标 前端控制器
 * </p>
 *
 * @author
 * @since 2020-11-03
 */
@RestController
@Api(tags = "资产负债表统计相关管理接口")
@RequestMapping("/liabilitiesStatistics")
public class LiabilitiesStatisticsController {

    @Autowired
    private LiabilitiesStatisticsService liabilitiesStatisticsService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<LiabilitiesStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo) {
        return liabilitiesStatisticsService.listPage(vo);
    }

    @PostMapping("/statistics")
    @ApiOperation(value = "统计")
    public Response statistics(LiabilitiesStatisticsAddVO vo) {
        liabilitiesStatisticsService.statistics(vo.getCompanyId(), vo.getYear(), vo.getReportType());
        return Response.success();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出数据")
    public void exportData(LiabilitiesStatisticsInputVO vo, HttpServletResponse response) {
        liabilitiesStatisticsService.exportData(response, vo);
    }

}
