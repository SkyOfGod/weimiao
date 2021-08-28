package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.FundCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjzx.model.vo.input.FundCompanyInputVO;
import com.sjzx.model.vo.output.FundCompanyCountVO;
import com.sjzx.model.vo.output.FundCompanyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基金持股表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
public interface FundCompanyMapper extends BaseMapper<FundCompany> {

    IPage<FundCompanyVO> listPage(IPage<FundCompanyVO> iPage, @Param("vo") FundCompanyInputVO vo);

    List<FundCompanyCountVO> combogrid(@Param("q") String q);
}
