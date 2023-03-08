package com.sjzx.service;

import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SCompanyTargetVO;

public interface SCompanyTargetService {
    EasyUIResult<SCompanyTargetVO> listPage(SLiabilitiesSearchInputVO vo);

    void calculateCompanyTarget(SCompanyTargetCalVO vo);


    void deleteCompanyTarget(SCompanyTargetVO vo);
}
