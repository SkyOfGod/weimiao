package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SCompanyTargetVO;
import com.sjzx.service.SCompanyTargetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyTarget")
@Slf4j
public class SCompanyTargetController {

    @Autowired
    private SCompanyTargetService sCompanyTargetService;

    @PostMapping("/listPage")
    public EasyUIResult<SCompanyTargetVO> listPage(SLiabilitiesSearchInputVO vo){
        return sCompanyTargetService.listPage(vo);
    }

    @PostMapping("/calculate")
    public Response<Void> calculateCompanyTarget(SCompanyTargetCalVO vo){
        sCompanyTargetService.calculateCompanyTarget(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response<Void> deleteCompanyTarget(SCompanyTargetVO vo){
        sCompanyTargetService.deleteCompanyTarget(vo);
        return Response.success();
    }

}
