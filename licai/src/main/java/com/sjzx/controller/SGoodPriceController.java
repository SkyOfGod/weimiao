package com.sjzx.controller;


import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SGoodPriceCalVO;
import com.sjzx.model.vo.input.SGoodPriceupdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SGoodPriceVO;
import com.sjzx.service.SGoodPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodPrice")
public class SGoodPriceController {

    @Autowired
    private SGoodPriceService sGoodPriceService;

    @PostMapping("/listPage")
    public EasyUIResult<SGoodPriceVO> listPage(SLiabilitiesSearchInputVO vo) {
        return sGoodPriceService.listPage(vo);
    }

    @PostMapping("/calculate")
    public Response<Void> calculateGoodPrice(SGoodPriceCalVO vo){
        sGoodPriceService.calculateGoodPrice(vo);
        return Response.success();
    }

    @PostMapping("/update")
    public  Response updateGoodPrice(SGoodPriceupdVO vo){
        sGoodPriceService.updateGoodPrice(vo);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response<Void> deleteGoodPrice(SGoodPriceVO vo){
        sGoodPriceService.deleteGoodPrice(vo);
        return Response.success();
    }

}
