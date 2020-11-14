package com.sjzx.controller;

import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesAddVO;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesUpdVO;
import com.sjzx.model.vo.output.ConsolidatedAssetsLiabilitiesVO;
import com.sjzx.model.vo.input.LiabilitiesInputVO;
import com.sjzx.service.ConsolidatedAssetsLiabilitiesService;
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

/**
 * @ClassName : ConsolidatedAssetsLiabilitiesController
 * @Description : 资产负债表相关
 * @Author : Horus
 * @Date: 2020-11-02 20:05
 */
@RestController
@Api(tags = "资产负债表相关管理接口")
@RequestMapping("/liabilities")
@Slf4j
public class ConsolidatedAssetsLiabilitiesController {

    @Autowired
    private ConsolidatedAssetsLiabilitiesService consolidatedAssetsLiabilitiesService;

    @PostMapping("/listPage")
    @ApiOperation(value = "数据分页")
    public EasyUIResult<ConsolidatedAssetsLiabilitiesVO> listPage(LiabilitiesInputVO vo) {
        return consolidatedAssetsLiabilitiesService.listPage(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Response addLiabilities(ConsolidatedAssetsLiabilitiesAddVO vo) {
        consolidatedAssetsLiabilitiesService.addLiabilities(vo);
        return Response.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Response updateLiabilities(ConsolidatedAssetsLiabilitiesUpdVO vo) {
        consolidatedAssetsLiabilitiesService.updateLiabilities(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除")
    public Response deleteLiabilities(ConsolidatedAssetsLiabilities vo) {
        consolidatedAssetsLiabilitiesService.deleteLiabilities(vo);
        return Response.success();
    }

    @PostMapping("/uploadExcel")
    @ApiOperation(value = "文件上传")
    public Response importLiabilitiesExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request)  {
        log.info("fileNme:"+file.getOriginalFilename());

        String compId = request.getParameter("id");
        log.info("compId:{}",compId);
        return Response.success();
    }
}
