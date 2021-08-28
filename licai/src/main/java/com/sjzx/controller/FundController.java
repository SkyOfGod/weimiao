package com.sjzx.controller;


import com.sjzx.entity.Fund;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.FundAddVO;
import com.sjzx.model.vo.input.FundInputVO;
import com.sjzx.service.FundService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 基金表 前端控制器
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private FundService fundService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<Fund> listPage(FundInputVO vo) {
        return fundService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response<Void> addFund(FundAddVO vo) {
        fundService.addFund(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response<Void> updateFund(FundAddVO vo) {
        fundService.updateFund(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response<Void> deleteFund(FundAddVO vo) {
        fundService.deleteFund(vo);
        return Response.success();
    }

    @PostMapping("/combogrid")
    @ApiOperation(value = "下拉框")
    public List<Fund> combogrid(String q) {
        return fundService.combogrid(q);
    }

}
