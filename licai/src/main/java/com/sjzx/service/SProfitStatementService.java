package com.sjzx.service;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.SProfitStatementInputVO;
import com.sjzx.model.vo.input.SProfitStatementUpdVO;
import com.sjzx.model.vo.output.SProfitStatementVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SProfitStatementService {

    EasyUIResult<SProfitStatementVO> listPage(SProfitStatementInputVO vo);

    void updateProfitStatement(SProfitStatementUpdVO vo);

    void deleteProfitStatement(SProfitStatementVO vo);

    Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum);

    SProfitStatementVO select(int companyId, int year);

    List<SProfitStatementVO> selectCompanyId(int companyId);
}
