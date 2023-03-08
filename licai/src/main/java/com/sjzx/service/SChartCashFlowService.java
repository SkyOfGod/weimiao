package com.sjzx.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SChartCashFlowInputVO;
import com.sjzx.model.vo.input.SChartCashFlowUpdVO;
import com.sjzx.model.vo.output.SChartCashFlowVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface SChartCashFlowService {
    EasyUIResult<SChartCashFlowVO> listpage(SChartCashFlowInputVO vo);


    void updateChartCashFlow(SChartCashFlowUpdVO vo);

    void deletechartCashFlow(SChartCashFlowVO vo);

    Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum);

    SChartCashFlowVO select(int companyId, int year);
}
