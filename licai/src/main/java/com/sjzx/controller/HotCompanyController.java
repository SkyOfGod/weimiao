package com.sjzx.controller;


import com.sjzx.entity.HotCompany;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.HotCompanyInputVO;
import com.sjzx.model.vo.output.HotCompanyCombogridVO;
import com.sjzx.model.vo.output.HotCompanyVO;
import com.sjzx.service.HotCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 龙头战法热点公司 前端控制器
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/hotCompany")
public class HotCompanyController {

  @Autowired
  private HotCompanyService hotCompanyService;

  @PostMapping("/listPage")
  @ApiOperation(value = "数据分页")
  public EasyUIResult<HotCompanyVO> listPage(HotCompanyInputVO vo) {
    return hotCompanyService.listPage(vo);
  }

  @PostMapping("/add")
  @ApiOperation(value = "添加")
  public Response addHotCompany(HotCompanyVO vo) {
    hotCompanyService.addHotCompany(vo);
    return Response.success();
  }

  @PostMapping("/update")
  @ApiOperation(value = "修改")
  public Response updateHotCompany(HotCompanyVO vo) {
    hotCompanyService.updateHotCompany(vo);
    return Response.success();
  }

  @PostMapping("/delete")
  @ApiOperation(value = "删除")
  public Response deleteHotCompany(HotCompanyVO vo) {
    hotCompanyService.deleteHotCompany(vo);
    return Response.success();
  }

  @PostMapping("/combogrid")
  @ApiOperation(value = "下拉框")
  public List<HotCompanyCombogridVO> combogrid(String q) {
    return hotCompanyService.combogrid(q);
  }

}
