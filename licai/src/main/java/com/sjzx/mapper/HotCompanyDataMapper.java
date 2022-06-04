package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.HotCompanyData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjzx.model.vo.input.HotCompanyDataInputVO;
import com.sjzx.model.vo.output.HotCompanyDataVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 热点公司复盘表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface HotCompanyDataMapper extends BaseMapper<HotCompanyData> {

    IPage<HotCompanyDataVO> listPage(IPage<HotCompanyDataVO> iPage, @Param("vo") HotCompanyDataInputVO vo);

    List<String> selectAllDataDate(String q);

    LocalDate selectLtDataDate(@Param("date") LocalDate date);

    BigDecimal selectRecentCirculationMarketValueByHotCompanyId(@Param("hotCompanyId") Integer hotCompanyId);
}
