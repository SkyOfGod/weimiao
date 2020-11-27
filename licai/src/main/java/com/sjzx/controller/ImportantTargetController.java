package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.ImportantTargetInputVO;
import com.sjzx.model.vo.input.LiabilitiesStatisticsAddVO;
import com.sjzx.model.vo.output.ImportantTargetVO;
import com.sjzx.service.ImportantTargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 重要指标表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
@RestController
@Api(tags = "重要指标表统计相关管理接口")
@RequestMapping("/importantTarget")
public class ImportantTargetController {

    @Autowired
    private ImportantTargetService importantTargetService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<ImportantTargetVO> listPage(ImportantTargetInputVO vo) {
        return importantTargetService.listPage(vo);
    }

    @PostMapping("/statistics")
    @ApiOperation(value = "统计")
    public Response statistics(LiabilitiesStatisticsAddVO vo) {
        importantTargetService.statistics(vo.getCompanyId(), vo.getYear(), vo.getReportType());
        return Response.success();
    }

}
