package com.sjzx.mapper;

import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SCompanyTargetVO;

import java.util.List;

public interface SCompanyTargetMapper {
    long selectCount(SLiabilitiesSearchInputVO vo);

    List<SCompanyTargetVO> selectList(SLiabilitiesSearchInputVO vo);

    void insertData(SCompanyTargetVO target);

    void deleteCompanyTarget(Integer id);
}
