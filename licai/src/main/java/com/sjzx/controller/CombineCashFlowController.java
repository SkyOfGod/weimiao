package com.sjzx.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.entity.CombineCashFlow;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.CombineCashFlowAddVO;
import com.sjzx.model.vo.input.CombineCashFlowInputVO;
import com.sjzx.model.vo.output.CombineCashFlowVO;
import com.sjzx.service.CombineCashFlowService;
import io.swagger.annotations.Api;
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

/**
 * <p>
 * 合并现金流量表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-11-04
 */
@RestController
@Api(tags = "公司合并现金流量表管理接口")
@RequestMapping("/combineCashFlow")
@Slf4j
public class CombineCashFlowController {

    @Autowired
    private CombineCashFlowService combineCashFlowService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<CombineCashFlowVO> listPage(CombineCashFlowInputVO vo) {
        return combineCashFlowService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addCashFlow(CombineCashFlowAddVO vo) {
        combineCashFlowService.addCashFlow(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateCashFlow(CombineCashFlow vo) {
        combineCashFlowService.updateCashFlow(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteCashFlow(CombineCashFlow vo) {
        combineCashFlowService.deleteCashFlow(vo);
        return Response.success();
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Response uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        log.info("=======>>>uploadExcel....");
        // 校验入参
        BiFunction<MultipartFile, HttpServletRequest, ExcelTypeEnum> checkParam = BaseController::checkParam;
        ExcelTypeEnum typeEnum = checkParam.apply(file, request);
        return combineCashFlowService.uploadExcel(file,request,typeEnum);
    }


}
