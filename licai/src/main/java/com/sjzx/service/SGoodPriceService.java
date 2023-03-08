package com.sjzx.service;

import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.SGoodPriceCalVO;
import com.sjzx.model.vo.input.SGoodPriceupdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SGoodPriceVO;

public interface SGoodPriceService {
    EasyUIResult<SGoodPriceVO> listPage(SLiabilitiesSearchInputVO vo);

    void calculateGoodPrice(SGoodPriceCalVO vo);

    void deleteGoodPrice(SGoodPriceVO vo);

    void updateGoodPrice(SGoodPriceupdVO vo);
}
