package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.Fund;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.FundAddVO;
import com.sjzx.model.vo.input.FundInputVO;

import java.util.List;

/**
 * <p>
 * 基金表 服务类
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
public interface FundService extends IService<Fund> {

    EasyUIResult<Fund> listPage(FundInputVO vo);

    void addFund(FundAddVO vo);

    void updateFund(FundAddVO vo);

    void deleteFund(FundAddVO vo);

    List<Fund> combogrid(String q);
}
