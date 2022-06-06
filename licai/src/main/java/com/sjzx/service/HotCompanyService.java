package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.HotCompany;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotCompanyInputVO;
import com.sjzx.model.vo.output.HotCompanyCombogridVO;
import com.sjzx.model.vo.output.HotCompanyVO;

import java.util.List;

/**
 * <p>
 * 龙头战法热点公司 服务类
 * </p>
 *
 * @author
 * @since 2021-02-04
 */
public interface HotCompanyService extends IService<HotCompany> {

    EasyUIResult<HotCompanyVO> listPage(HotCompanyInputVO vo);

    void addHotCompany(HotCompanyVO vo);

    void updateHotCompany(HotCompanyVO vo);

    void deleteHotCompany(HotCompanyVO vo);

    HotCompany selectByCode(String code);

    List<HotCompanyCombogridVO> combogrid(String q);
}
