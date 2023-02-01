package com.sjzx.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.exception.ServiceException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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
        hotCompanyDataService.addHotCompanyData(vo);
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

    @PostMapping("/dataDateNewCombogrid")
    @ApiOperation(value = "实时复盘日期下拉框")
    public List<Map<String, String>> dataDateNewCombogrid(String q) {
        return hotCompanyDataService.dataDateNewCombogrid(q);
    }

    @PostMapping("/deleteByDataDate")
    @ApiOperation(value = "删除")
    public Response<Integer> deleteByDataDate(HotCompanyDataAddVO vo) {
        return Response.successData(hotCompanyDataService.delete(vo.getDataDate()));
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Response uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            // 校验入参
            BiFunction<MultipartFile, HttpServletRequest, ExcelTypeEnum> checkParam = BaseController::checkParam;
            ExcelTypeEnum typeEnum = checkParam.apply(file, request);
            hotCompanyDataService.uploadExcel(file, request, typeEnum);
        } catch (Exception e) {
            throw new ServiceException("文件上传失败:" + e.getMessage());
        }
        return Response.success();
    }

}
