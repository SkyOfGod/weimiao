package com.sjzx.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.exception.ServiceException;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesAddVO;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesUpdVO;
import com.sjzx.model.vo.output.ConsolidatedAssetsLiabilitiesVO;
import com.sjzx.model.vo.input.LiabilitiesInputVO;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 合并资产负债表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
public interface ConsolidatedAssetsLiabilitiesService extends IService<ConsolidatedAssetsLiabilities> {

    EasyUIResult<ConsolidatedAssetsLiabilitiesVO> listPage(LiabilitiesInputVO vo);

    void addLiabilities(ConsolidatedAssetsLiabilitiesAddVO vo);

    void updateLiabilities(ConsolidatedAssetsLiabilitiesUpdVO vo);

    void deleteLiabilities(ConsolidatedAssetsLiabilities vo);

    ConsolidatedAssetsLiabilities getByIndex(Integer companyId, Integer year, Integer reportType);

    Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum);


}
