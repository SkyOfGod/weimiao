package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.HotCompanyDataAddVO;
import com.sjzx.model.vo.input.HotCompanyDataInputVO;
import com.sjzx.model.vo.output.HotCompanyDataVO;
import com.sjzx.service.HotCompanyDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 热点公司复盘表 前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@RestController
@RequestMapping("/hotCompanyData")
public class HotCompanyDataController {

    @Autowired
    private HotCompanyDataService hotCompanyDataService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<HotCompanyDataVO> listPage(HotCompanyDataInputVO vo) {
        return hotCompanyDataService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addHotCompanyData(HotCompanyDataAddVO vo) {
        String dataDate = hotCompanyDataService.addHotCompanyData(vo);
        hotCompanyDataService.updateHotCompareDataSort(dataDate, vo.getHotTypeId());
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateHotCompanyData(HotCompanyDataAddVO vo) {
        hotCompanyDataService.updateHotCompanyData(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteHotCompanyData(HotCompanyDataAddVO vo) {
        hotCompanyDataService.deleteHotCompanyData(vo);
        return Response.success();
    }

    @PostMapping("/dataDateCombogrid")
    @ApiOperation(value = "下拉框")
    public List<Map<String, String>> getDataDateCombobox(String q) {
        return hotCompanyDataService.getDataDateCombobox(q);
    }

}
