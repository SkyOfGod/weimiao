package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjzx.model.vo.input.CompanyInputVO;
import com.sjzx.model.vo.output.CompanyVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
public interface CompanyMapper extends BaseMapper<Company> {

    IPage<CompanyVO> companyPage(IPage<CompanyVO> iPage, @Param("vo") CompanyInputVO vo);
}
