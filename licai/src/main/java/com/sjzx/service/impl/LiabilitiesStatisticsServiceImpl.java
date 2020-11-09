package com.sjzx.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.Company;
import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.entity.LiabilitiesStatistics;
import com.sjzx.mapper.LiabilitiesStatisticsMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.LiabilitiesStatisticsVO;
import com.sjzx.service.CompanyService;
import com.sjzx.service.ConsolidatedAssetsLiabilitiesService;
import com.sjzx.service.LiabilitiesStatisticsService;
import com.sjzx.utils.BeanUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import static com.sjzx.utils.NumberUtils.*;

/**
 * <p>
 * 资产负债表统计指标 服务实现类
 * </p>
 *
 * @author
 * @since 2020-11-03
 */
@Service
@Slf4j
public class LiabilitiesStatisticsServiceImpl extends ServiceImpl<LiabilitiesStatisticsMapper, LiabilitiesStatistics> implements LiabilitiesStatisticsService {

    @Autowired
    private ConsolidatedAssetsLiabilitiesService consolidatedAssetsLiabilitiesService;

    @Autowired
    private CompanyService companyService;

    @Override
    public EasyUIResult<LiabilitiesStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo) {
        IPage<LiabilitiesStatisticsVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        List<LiabilitiesStatisticsVO> records = iPage.getRecords();
        formatToPercent(records);
        return new EasyUIResult<>(iPage.getTotal(), records);
    }

    /**
     * 导出数据
     **/
    @Override
    @SneakyThrows
    public void exportData(HttpServletResponse response, LiabilitiesStatisticsInputVO vo) {
        List<LiabilitiesStatisticsVO> list = baseMapper.getList(vo);
        formatToPercent(list);
        ExcelWriter writer = ExcelUtil.getWriter();
        addHeaderAlias(writer);
        writer.setOnlyAlias(true);
        writer.write(list, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = URLEncoder.encode("合并资产负债表指标", "utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }

    /** 数据百分比格式化**/
    private void formatToPercent(List<LiabilitiesStatisticsVO> records) {
        records.forEach(e ->
                e.setTotalAssetsGrowthRate(toPercent(e.getTotalAssetsGrowthRate()))
                        .setTotalLiabilitiesInReportType(toPercent(e.getTotalLiabilitiesInReportType()))
                        .setTotalLiabilitiesGrowthRate(toPercent(e.getTotalLiabilitiesGrowthRate()))
                        .setShareHolderEquityGrowthRate(toPercent(e.getShareHolderEquityGrowthRate()))
                        .setReceivableMoneyInReportType(toPercent(e.getReceivableMoneyInReportType()))
                        .setFixedAssetsTotalInReportType(toPercent(e.getFixedAssetsTotalInReportType()))
                        .setInvestmentInReportType(toPercent(e.getInvestmentInReportType()))
        );
    }

    /**
     * 定义导出表格列别名
     **/
    private void addHeaderAlias(ExcelWriter writer) {
        writer.merge(18, "合并资产负债表数据统计");
        writer.addHeaderAlias("code", "股票代码");
        writer.addHeaderAlias("name", "公司名称");
        writer.addHeaderAlias("year", "年份");
        writer.addHeaderAlias("reportType", "规格");
        writer.addHeaderAlias("totalAssets", "总资产");
        writer.addHeaderAlias("sharesValue", "每股净资产");
        writer.addHeaderAlias("totalAssetsGrowthRate", "总资产增速");
        writer.addHeaderAlias("totalLiabilitiesInReportType", "资产负债率");
        writer.addHeaderAlias("totalLiabilitiesGrowthRate", "负债合计增速");
        writer.addHeaderAlias("shareHolderEquityGrowthRate", "股东权益合计增速");
        writer.addHeaderAlias("interestBearingLiabilities", "有息负债总额");
        writer.addHeaderAlias("industryStatusOne", "应付减应收");
        writer.addHeaderAlias("monetaryCapital", "货币资金");
        writer.addHeaderAlias("receivableMoneyInReportType", "应收账款占总资产比");
        writer.addHeaderAlias("fixedAssetsTotalInReportType", "固定资产总和占总资产比");
        writer.addHeaderAlias("investmentInReportType", "投资资产占总资产比");
//        writer.addHeaderAlias("remark", "备注");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("updateTime", "更新时间");
    }

    @Override
    public void statistics(Integer companyId, Integer year, Integer reportType) {
        ConsolidatedAssetsLiabilities current = consolidatedAssetsLiabilitiesService.getByIndex(companyId, year, reportType);
        if (current == null) {
            return;
        }
        LiabilitiesStatistics statistics = BeanUtils.copyProperties(current, LiabilitiesStatistics::new);
        //有息负债总额 = 短期负债 + 一年内到期的非流动负债 + 长期借款 + 应付债券 + 长期应付款 + 应付利息
        long interestBearingLiabilities = current.getShortTermLiabilities() + current.getYearArriveNoCurrentLiabilities() + current.getLongTermLiabilities()
                + current.getPayableBonds() + current.getLongPayableMoney() + current.getPayableInterest();
        //应付票据 + 应付账款 + 预收款项 - 应收票据 - 应收账款 - 预付款项
        long industryStatusOne = current.getPayableBill() + current.getPayableMoney() + current.getAdvanceReceivableMoney()
                - current.getReceivableBill() - current.getReceivableMoney() - current.getAdvancePayableMoney();
        //固定资产+在建工程+工程物资
        long fixedAssetsTotal = current.getFixedAssets() + current.getReconstructionProject() + current.getEngineeringMaterials();
        //公允价值计量且变动计入当期损益的金融资产 + 可供出售金融资产 + 持有至到期投资 + 投资房地产 + 长期股权投资（与主业无关）
        long investmentTotal = current.getFairValueProject() + current.getPrepareSaleFinanceProject() + current.getHeldToMaturityInvestment()
                + current.getInvestinInRealEstate() + current.getLongTermEquityInvestment();
        //上个对比的数据
        ConsolidatedAssetsLiabilities previous = consolidatedAssetsLiabilitiesService.getByIndex(companyId, year - 1, reportType);
        if (previous != null) {
            statistics.setTotalAssetsGrowthRate(addRate(current.getTotalAssets(), previous.getTotalAssets()))
                    .setTotalLiabilitiesGrowthRate(addRate(current.getTotalLiabilities(), previous.getTotalLiabilities()))
                    .setShareHolderEquityGrowthRate(addRate(current.getShareHolderEquity(), previous.getShareHolderEquity()));
        } else {
            statistics.setTotalAssetsGrowthRate(0).setTotalLiabilitiesGrowthRate(0).setShareHolderEquityGrowthRate(0);
        }

        statistics.setRemark(null).setTotalLiabilitiesInReportType(divide(current.getTotalLiabilities(), current.getTotalAssets()))
                .setInterestBearingLiabilities(interestBearingLiabilities).setIndustryStatusOne(industryStatusOne)
                .setReceivableMoneyInReportType(divide(current.getReceivableMoney(), current.getTotalAssets()))
                .setFixedAssetsTotalInReportType(divide(fixedAssetsTotal, current.getTotalAssets()))
                .setInvestmentInReportType(divide(investmentTotal, current.getTotalAssets()));
        Company company = companyService.getById(statistics.getCompanyId());
        if (company != null) {
            statistics.setSharesValue(divideToBigDecimal(current.getBelongMotherEquity(), company.getTotalEquity()));
        }
        LiabilitiesStatistics old = getByIndex(statistics.getCompanyId(), statistics.getYear(), statistics.getReportType());
        if (old == null) {
            statistics.setCreateTime(new Date()).setUpdateTime(null).insert();
        } else {
            statistics.setId(old.getId()).setUpdateTime(new Date()).updateById();
        }
    }

    private LiabilitiesStatistics getByIndex(Integer companyId, Integer year, Integer reportType) {
        if (companyId == null || year == null || reportType == null) {
            return null;
        }
        LambdaQueryWrapper<LiabilitiesStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiabilitiesStatistics::getCompanyId, companyId)
                .eq(LiabilitiesStatistics::getYear, year)
                .eq(LiabilitiesStatistics::getReportType, reportType);
        return getOne(wrapper);
    }

}
