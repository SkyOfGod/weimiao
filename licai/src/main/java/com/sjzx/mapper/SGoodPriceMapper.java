package com.sjzx.mapper;

import com.sjzx.model.vo.input.SGoodPriceupdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SGoodPriceVO;

import java.util.List;

public interface SGoodPriceMapper {

    long count(SLiabilitiesSearchInputVO vo);

    List<SGoodPriceVO> selectList(SLiabilitiesSearchInputVO vo);

    void insertData(SGoodPriceVO target);

    void deleteGoodPrice(Integer id);

    void updateGoodPrice(SGoodPriceupdVO vo);

    SGoodPriceVO selectById(Integer id);

}
