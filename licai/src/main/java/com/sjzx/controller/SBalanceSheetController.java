package com.sjzx.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SBalanceSheetUpdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.service.SBalanceSheetService;
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

@RestController
@RequestMapping("/balanceSheet")
@Slf4j
public class SBalanceSheetController {

    @Autowired
    private SBalanceSheetService sBalanceSheetService;
    
    @PostMapping("/listpage")
    public EasyUIResult<SBalanceSheetVO> listPage(SLiabilitiesSearchInputVO vo) {
        return sBalanceSheetService.listPage(vo);
    }

    @PostMapping("/update")
    public Response updateBalanceSheet(SBalanceSheetUpdVO vo){
        sBalanceSheetService.updateBalanceSheet(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response deleteBalanceSheet(SBalanceSheetVO vo){
        sBalanceSheetService.deleteBalanceSheet(vo);
        return Response.success();
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Response uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        log.info("=======>>>uploadExcel....");
        // 校验入参
        BiFunction<MultipartFile, HttpServletRequest, ExcelTypeEnum> checkParam = BaseController::checkParam;
        ExcelTypeEnum typeEnum = checkParam.apply(file, request);
        return sBalanceSheetService.uploadExcel(file,request,typeEnum);
    }
}
