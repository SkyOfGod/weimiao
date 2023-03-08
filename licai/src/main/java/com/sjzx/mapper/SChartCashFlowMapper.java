package com.sjzx.mapper;

import com.sjzx.model.vo.input.SChartCashFlowInputVO;
import com.sjzx.model.vo.input.SChartCashFlowUpdVO;
import com.sjzx.model.vo.output.SChartCashFlowVO;

import java.util.List;

public interface SChartCashFlowMapper {
    long selectcount(SChartCashFlowInputVO vo);

    List<SChartCashFlowVO> selectList(SChartCashFlowInputVO vo);

    void updateChartCashFlow(SChartCashFlowUpdVO vo);

    void deletechartCashFlow(Integer id);

    void insertChartCashFlow(SChartCashFlowVO schr);

}
