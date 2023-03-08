package com.sjzx.mapper;

import com.sjzx.model.vo.input.SProfitStatementInputVO;
import com.sjzx.model.vo.input.SProfitStatementUpdVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.model.vo.output.SProfitStatementVO;

import java.util.List;

public interface SProfitStatementMapper {
    long selectcount(SProfitStatementInputVO vo);
    
    List<SProfitStatementVO> selectList(SProfitStatementInputVO vo);

    void updateProfitStatement(SProfitStatementUpdVO vo);

    void deleteProfitStatement(Integer id);

    void insertProfitStatement(SProfitStatementVO sPro);

}
