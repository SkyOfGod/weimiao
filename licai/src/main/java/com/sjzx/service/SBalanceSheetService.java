package com.sjzx.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SBalanceSheetUpdVO;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface SBalanceSheetService {
    EasyUIResult<SBalanceSheetVO> listPage(SLiabilitiesSearchInputVO vo);

    void updateBalanceSheet(SBalanceSheetUpdVO vo);

    void deleteBalanceSheet(SBalanceSheetVO vo);

    Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum);

    SBalanceSheetVO select(int companyId, int year);
}
