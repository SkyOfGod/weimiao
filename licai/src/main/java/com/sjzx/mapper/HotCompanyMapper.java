package com.sjzx.mapper;

import com.sjzx.entity.HotCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjzx.model.vo.output.HotCompanyCombogridVO;

import java.util.List;

/**
 * <p>
 * 龙头战法热点公司 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
public interface HotCompanyMapper extends BaseMapper<HotCompany> {

    List<HotCompanyCombogridVO> combogridMax(String q);
}
