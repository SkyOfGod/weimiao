package com.sjzx.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SBalanceSheetUpdVO;
import com.sjzx.model.vo.input.SChartCashFlowInputVO;
import com.sjzx.model.vo.input.SChartCashFlowUpdVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.model.vo.output.SChartCashFlowVO;
import com.sjzx.service.SChartCashFlowService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiFunction;

@Slf4j
@RestController
@RequestMapping("/chartCashFlow")
public class SChartCashFlowController {

    @Autowired
    private SChartCashFlowService sChartCashFlowService;

    @PostMapping("/listpage")
    public EasyUIResult<SChartCashFlowVO> listpage(SChartCashFlowInputVO vo) {
        return sChartCashFlowService.listpage(vo);
    }

    @PostMapping("/update")
    public Response updateChartCashFlow(SChartCashFlowUpdVO vo) {
        sChartCashFlowService.updateChartCashFlow(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response deletechartCashFlow(SChartCashFlowVO vo){
        sChartCashFlowService.deletechartCashFlow(vo);
        return Response.success();
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Response uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        log.info("=======>>>uploadExcel....");
        // 校验入参
        BiFunction<MultipartFile, HttpServletRequest, ExcelTypeEnum> checkParam = BaseController::checkParam;
        ExcelTypeEnum typeEnum = checkParam.apply(file, request);
        return sChartCashFlowService.uploadExcel(file,request,typeEnum);
    }

}