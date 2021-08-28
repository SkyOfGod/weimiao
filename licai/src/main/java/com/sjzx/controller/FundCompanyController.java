package com.sjzx.controller;


import com.sjzx.entity.FundCompany;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.FundCompanyInputVO;
import com.sjzx.model.vo.output.FundCompanyCountVO;
import com.sjzx.model.vo.output.FundCompanyVO;
import com.sjzx.service.FundCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 基金持股表 前端控制器
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/fundCompany")
public class FundCompanyController {

    @Autowired
    private FundCompanyService fundCompanyService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<FundCompanyVO> listPage(FundCompanyInputVO vo) {
        return fundCompanyService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response<Void> addFund(FundCompany vo) {
        fundCompanyService.addFundCompany(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response<Void> updateFund(FundCompany vo) {
        fundCompanyService.updateFundCompany(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response<Void> deleteFund(FundCompany vo) {
        fundCompanyService.deleteFundCompany(vo);
        return Response.success();
    }

    @PostMapping("/combogrid")
    @ApiOperation(value = "统计公司被多家基金持有排名下拉框")
    public List<FundCompanyCountVO> combogrid(String q) {
        return fundCompanyService.combogrid(q);
    }

}
