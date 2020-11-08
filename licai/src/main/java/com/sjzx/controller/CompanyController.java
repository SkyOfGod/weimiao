package com.sjzx.controller;

import com.sjzx.entity.Company;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.enums.CompanyTypeEnum;
import com.sjzx.model.vo.input.CompanyInputVO;
import com.sjzx.model.vo.input.CompanyUpdVO;
import com.sjzx.model.vo.output.CompanyVO;
import com.sjzx.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : CompanyController
 * @Description : 公司
 * @Author : Horus
 * @Date: 2020-11-02 11:09
 */
@RestController
@Api(tags = "公司管理接口")
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/companyPage")
    @ApiOperation(value = "公司分页")
    public EasyUIResult<CompanyVO> companyPage(CompanyInputVO vo) {
        return companyService.companyPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addCompany(Company vo) {
        companyService.addCompany(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateCompany(CompanyUpdVO vo) {
        companyService.updateCompany(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteCompany(Company vo) {
        companyService.deleteCompany(vo);
        return Response.success();
    }

    @PostMapping("/combogrid")
    @ApiOperation(value = "下拉框")
    public List<Company> combogrid(String q) {
        return companyService.combogrid(q);
    }

    @PostMapping("/typeCombobox")
    @ApiOperation(value = "下拉框")
    public Map<String, String> typeCombobox() {
        return CompanyTypeEnum.getCombobox();
    }

}
