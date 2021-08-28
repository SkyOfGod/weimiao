package com.sjzx.service;

import com.sjzx.entity.FundCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.FundCompanyInputVO;
import com.sjzx.model.vo.output.FundCompanyCountVO;
import com.sjzx.model.vo.output.FundCompanyVO;

import java.util.List;

/**
 * <p>
 * 基金持股表 服务类
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
public interface FundCompanyService extends IService<FundCompany> {

    EasyUIResult<FundCompanyVO> listPage(FundCompanyInputVO vo);

    void addFundCompany(FundCompany vo);

    void updateFundCompany(FundCompany vo);

    void deleteFundCompany(FundCompany vo);

    List<FundCompanyCountVO> combogrid(String q);
}
