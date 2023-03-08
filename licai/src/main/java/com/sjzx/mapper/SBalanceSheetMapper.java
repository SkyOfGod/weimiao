package com.sjzx.mapper;

import com.sjzx.model.vo.input.SBalanceSheetUpdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;

import java.util.List;

public interface SBalanceSheetMapper {
    long selectcount(SLiabilitiesSearchInputVO vo);

    List<SBalanceSheetVO> selectList(SLiabilitiesSearchInputVO vo);

    void updateBalanceSheet(SBalanceSheetUpdVO vo);

    void deleteBalanceSheet(Integer id);

    void insertBalanceSheet(SBalanceSheetVO sbal);
}
