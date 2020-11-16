package com.sjzx.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.entity.CombineProfit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.CombineProfitAddVO;
import com.sjzx.model.vo.input.CombineProfitInputVO;
import com.sjzx.model.vo.output.CombineProfitVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 合并利润表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-03
 */
public interface CombineProfitService extends IService<CombineProfit> {

    EasyUIResult<CombineProfitVO> listPage(CombineProfitInputVO vo);

    void addCombineProfit(CombineProfitAddVO vo);

    void updateCombineProfit(CombineProfit vo);

    void deleteCombineProfit(CombineProfit vo);

    CombineProfit getByIndex(Integer companyId, Integer year, Integer reportType);

    Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum);
}
