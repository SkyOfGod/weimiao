package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.FundCompany;
import com.sjzx.entity.ProfitStatistics;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.FundCompanyMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.FundCompanyInputVO;
import com.sjzx.model.vo.output.FundCompanyCountVO;
import com.sjzx.model.vo.output.FundCompanyVO;
import com.sjzx.service.FundCompanyService;
import com.sjzx.service.ProfitStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基金持股表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@Service
public class FundCompanyServiceImpl extends ServiceImpl<FundCompanyMapper, FundCompany> implements FundCompanyService {

    @Autowired
    private ProfitStatisticsService profitStatisticsService;

    @Override
    public EasyUIResult<FundCompanyVO> listPage(FundCompanyInputVO vo) {
        IPage<FundCompanyVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void addFundCompany(FundCompany vo) {
        validateExit(vo.getFundId(), vo.getCompanyId(), null);

        Date date = new Date();
        ProfitStatistics profitStatistics = profitStatisticsService.getRecentYearData(vo.getCompanyId());
        if(profitStatistics != null) {
            vo.setYear(profitStatistics.getYear())
                    .setTtmTime(date)
                    .setTtm(vo.getPrice().divide(profitStatistics.getSharesProfit(), 0, BigDecimal.ROUND_HALF_UP).intValue());
        }
        vo.setCreateTime(date).insert();
    }

    @Override
    public void updateFundCompany(FundCompany vo) {
        validateExit(vo.getFundId(), vo.getCompanyId(), vo.getId());

        Date date = new Date();
        FundCompany old = getById(vo.getId());
        if(old.getPrice() == null || old.getPrice().compareTo(vo.getPrice()) != 0) {
            ProfitStatistics profitStatistics = profitStatisticsService.getRecentYearData(vo.getCompanyId());
            if(profitStatistics != null) {
                vo.setYear(profitStatistics.getYear())
                        .setTtmTime(date)
                        .setTtm(vo.getPrice().divide(profitStatistics.getSharesProfit(), 0, BigDecimal.ROUND_HALF_UP).intValue());
            }
        }
        vo.setUpdateTime(date).updateById();
    }

    @Override
    public void deleteFundCompany(FundCompany vo) {
        removeById(vo.getId());
    }

    @Override
    public List<FundCompanyCountVO> combogrid(String q) {
        return baseMapper.combogrid(q);
    }

    public void validateExit(Integer fundId, Integer companyId, Integer id) {
        LambdaQueryWrapper<FundCompany> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FundCompany::getFundId, fundId)
                .eq(FundCompany::getCompanyId, companyId)
                .ne(id != null, FundCompany::getId, id);

        List<FundCompany> list = list(wrapper);
        if (!list.isEmpty()) {
            throw new ServiceException("基金持有该公司数据已存在");
        }
    }


}
