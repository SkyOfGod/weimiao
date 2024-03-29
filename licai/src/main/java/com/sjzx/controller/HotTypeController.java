package com.sjzx.controller;


import com.sjzx.entity.HotType;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.HotTypeInputVO;
import com.sjzx.model.vo.output.HotTypeVO;
import com.sjzx.service.HotTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 热点类型 前端控制器
 * </p>
 *
 * @author
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/hotType")
public class HotTypeController {

    @Autowired
    private HotTypeService hotTypeService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<HotTypeVO> listPage(HotTypeInputVO vo) {
        return hotTypeService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addHotType(HotType vo) {
        hotTypeService.addHotType(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateHotType(HotType vo) {
        hotTypeService.updateHotType(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteHotType(HotType vo) {
        hotTypeService.deleteHotType(vo);
        return Response.success();
    }

    @PostMapping("/combogrid")
    @ApiOperation(value = "下拉框")
    public List<HotType> combogrid(String q) {
        return hotTypeService.combogrid(q);
    }


}
