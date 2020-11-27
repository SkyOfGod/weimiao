package com.sjzx.controller;


import com.sjzx.entity.TargetCategory;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.TargetCategoryInputVO;
import com.sjzx.service.TargetCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 指标类型明细 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
@RestController
@Api(tags = "指标类型明细相关管理接口")
@RequestMapping("/targetCategory")
public class TargetCategoryController {

    @Autowired
    private TargetCategoryService targetCategoryService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<TargetCategory> listPage(TargetCategoryInputVO vo) {
        return targetCategoryService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addTargetCategory(TargetCategory vo) {
        targetCategoryService.addTargetCategory(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateTargetCategory(TargetCategory vo) {
        targetCategoryService.updateTargetCategory(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteTargetCategory(TargetCategory vo) {
        targetCategoryService.deleteTargetCategory(vo);
        return Response.success();
    }

}
