package com.sjzx.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SProfitStatementInputVO;
import com.sjzx.model.vo.input.SProfitStatementUpdVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.model.vo.output.SProfitStatementVO;
import com.sjzx.service.SProfitStatementService;
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
@RequestMapping("/profitStatement")
@Slf4j
public class SProfitStatementController {

    @Autowired
    private SProfitStatementService sProfitStatementService;

    @PostMapping("/listpage")
    public EasyUIResult<SProfitStatementVO> listpage(SProfitStatementInputVO vo) {
        return sProfitStatementService.listPage(vo);
    }

    @PostMapping("/update")
    public Response updateProfitStatement(SProfitStatementUpdVO vo) {
        sProfitStatementService.updateProfitStatement(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response deleteProfitStatement(SProfitStatementVO vo) {
        sProfitStatementService.deleteProfitStatement(vo);
        return Response.success();
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Response uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        log.info("=======>>>uploadExcel....");
        // 校验入参
        BiFunction<MultipartFile, HttpServletRequest, ExcelTypeEnum> checkParam = BaseController::checkParam;
        ExcelTypeEnum typeEnum = checkParam.apply(file, request);
        return sProfitStatementService.uploadExcel(file,request,typeEnum);
    }
}