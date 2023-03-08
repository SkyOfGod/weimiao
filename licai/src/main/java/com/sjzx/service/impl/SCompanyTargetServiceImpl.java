package com.sjzx.service.impl;

import com.sjzx.mapper.SCompanyTargetMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.input.SGoodPriceCalVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.model.vo.output.SChartCashFlowVO;
import com.sjzx.model.vo.output.SCompanyTargetVO;
import com.sjzx.model.vo.output.SProfitStatementVO;
import com.sjzx.service.*;
import com.sjzx.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SCompanyTargetServiceImpl implements SCompanyTargetService {

    @Autowired
    private SCompanyTargetMapper sCompanyTargetMapper;

    @Autowired
    private SBalanceSheetService sBalanceSheetService;

    @Autowired
    private SProfitStatementService sProfitStatementService;

    @Autowired
    private SChartCashFlowService sChartCashFlowService;

    @Autowired
    private SGoodPriceService sGoodPriceService;



    @Override
    public EasyUIResult<SCompanyTargetVO> listPage(SLiabilitiesSearchInputVO vo) {
        long total = sCompanyTargetMapper.selectCount(vo);

        int pageIndex = (vo.getPageNo() - 1) * vo.getPageSize();
        vo.setPageIndex(pageIndex);

        List<SCompanyTargetVO> list = sCompanyTargetMapper.selectList(vo);

        return new EasyUIResult<>(total, list);
    }

    @Override
    public void calculateCompanyTarget(SCompanyTargetCalVO vo) {
        SCompanyTargetVO target = new SCompanyTargetVO();
        target.setCompanyId(vo.getCompanyId());
        target.setYear(vo.getYear());
        target.setCreateTime(new Date());

        SBalanceSheetVO sbala = sBalanceSheetService.select(vo.getCompanyId(), vo.getYear());
        if (sbala != null) {
            SBalanceSheetVO lastYearSbala = sBalanceSheetService.select(vo.getCompanyId(), vo.getYear() - 1);
            //2、资产负债率=总负债{负债合计}/总资产*100%
            int b = NumberUtils.maintainAccuracy(sbala.getTotalLiabilities(), sbala.getTotalAssets());
            target.setTotalDebtGrowthRate(b);
            //2.1、准货币资金=货币资金+交易性金融资产+其他流动资产
            int b1 = sbala.getMonetaryFunds() + sbala.getTradingFinancialAssets() + sbala.getOtherCurrentAssets();
            target.setQuasiMonetaryFunds(b1);
            //2.2、有息负债=短期借款+一年到期的非流动负债+长期借款+长期应付款合计+应付债券
            int b2 = sbala.getShortTermBorrowing() + sbala.getNonCurrentLiability() + sbala.getLongTermBorrowing() +
                    sbala.getTotalLongTermPayables() + sbala.getBondsPayable();
            target.setLiabilityInterest(b2);
            //2.0、准货币资金减有息负债的差额=准货币资金-有息负债
            target.setDifference(b1 - b2);

            //3.1应付预收=应付票据+应付账款+预收款项+合同负债
            if (sbala.getNotesPayable() != null && sbala.getNotesPayable() > 0
                    && sbala.getAccountsPayable() != null && sbala.getAccountsPayable() > 0) {
                int c1 = sbala.getNotesPayable() + sbala.getAccountsPayable() + sbala.getAccountCollectedAdvance() + sbala.getLiabilityForContract();
                //3.2应收预付=应收票据+应收账款+预付款项
                int c3 = sbala.getNotesReceivable() + sbala.getAccountsReceivable() + sbala.getPrepayment();
                //3、应付预收减应收预付差额=应付预收-应收预付
                target.setDifferencePayableReceivable(c1 - c3);
            } else {
                int c1 = sbala.getNotesAccounts() + sbala.getAccountCollectedAdvance() + sbala.getLiabilityForContract();
                //3.2应收预付=应收票据+应收账款+预付款项
                int c3 = sbala.getNotesReceivable() + sbala.getAccountsReceivable() + sbala.getPrepayment();
                //3、应付预收减应收预付差额=应付预收-应收预付
                target.setDifferencePayableReceivable(c1 - c3);
            }

            //4、应收账款占总资产的比率=应收账款/总资产*100%
            int d = NumberUtils.maintainAccuracy(sbala.getAccountsReceivable(), sbala.getTotalAssets());
            target.setAccountsReceivablePercentage(d);

            //5、固定资产比例=（固定资产+在建工程+工程物资）/总资产*100%
            if (sbala.getFixedAssets() != null && sbala.getFixedAssets() > 0
                    && sbala.getWorkInProgress() != null && sbala.getWorkInProgress() > 0) {
                int e = NumberUtils.maintainAccuracy(sbala.getFixedAssets() + sbala.getWorkInProgress() + sbala.getMaterialsForConstruction(), sbala.getTotalAssets());
                target.setProportionFixedAssets(e);
            } else {
                int e = NumberUtils.maintainAccuracy(sbala.getTotalFixedAssets() + sbala.getTotalWorksInProgress(), sbala.getTotalAssets());
                target.setProportionFixedAssets(e);
            }

            //6.1投资类资产合计=可供出售的金融资产+持有到期的金融资产+长期股权投资+投资性房地产+其他权益工具投资+其他非流动的金融资产
            int f1 = sbala.getFinancialAssetsSale() + sbala.getHoldFinancialAssets() + sbala.getLongTermEquityInvestment() + sbala.getInvestmentRealEstate()
                    + sbala.getInvestmentInOther() + sbala.getOtherNonCurrentFinancialAssets();
            target.setTotalInvestmentAssets(f1);
            //6、投资类资产占总资产的比率=投资类资产合计/总资产*100%
            int f = NumberUtils.maintainAccuracy(f1, sbala.getTotalAssets());
            target.setRatioInvestmentAssets(f);

            //7、存货占总资产的比率=存货/总资产*100%
            target.setRatioInventory(NumberUtils.maintainAccuracy(sbala.getInventory(), sbala.getTotalAssets()));

            //7.1、商誉占总资产的比率=商誉/总资产*100%
            target.setRatioGoodwill(NumberUtils.maintainAccuracy(sbala.getGoodwill(), sbala.getTotalAssets()));

            if (lastYearSbala != null) {
                //1、总资产{资产合计}增长率=（本年资产总计-上年资产总计）/上年资产总计*100%
                int a = NumberUtils.maintainAccuracy(sbala.getTotalAssets() - lastYearSbala.getTotalAssets(), lastYearSbala.getTotalAssets());
                target.setGrowthRateTotalAssets(a);
            }


            SProfitStatementVO spro = sProfitStatementService.select(vo.getCompanyId(), vo.getYear());
            if (spro != null) {
                //9、毛利率=（营业收入-营业成本）/营业收入*100%
                int g = NumberUtils.maintainAccuracy(spro.getOperatingIncome() - spro.getOperatingCost(), spro.getOperatingIncome());
                target.setGrossProfitRate(g);

                //10.1期间费用率=（销售费用+管理费用+研发费用+财务费用）/营业收入
                if (spro.getFinancialExpenses() < 0) {
                    int h = (spro.getExpenseSales() + spro.getAdministrativeExpenses() + spro.getResearchDevelopmentExpenses()) * 100 / spro.getOperatingIncome();
                    target.setPeriodExpenseRate(h);
                } else {
                    int h = (spro.getExpenseSales() + spro.getAdministrativeExpenses() + spro.getResearchDevelopmentExpenses() + spro.getFinancialExpenses()) * 100 / spro.getOperatingIncome();
                    target.setPeriodExpenseRate(h);
                }

                //10、期间费用率与毛利率的比率=期间费用率/毛利率*100%
                target.setRatioPeriodExpenseGross(NumberUtils.maintainAccuracy(target.getPeriodExpenseRate(), target.getGrossProfitRate()));

                //11、销售费用率=销售费用/营业收入*100%
                target.setRatioExpensesSales(NumberUtils.maintainAccuracy(spro.getExpenseSales(), spro.getOperatingIncome()));

                //12.1、主营利润=营业收入-营业成本-（销售费用+管理费用+研发费用+财务费用）-税金及附加。
                if (spro.getFinancialExpenses() < 0) {
                    target.setMainProfit(spro.getOperatingIncome() - spro.getOperatingCost() - spro.getExpenseSales() - spro.getAdministrativeExpenses()
                            - spro.getResearchDevelopmentExpenses() - spro.getBusinessTaxesSurcharges());
                } else {
                    target.setMainProfit(spro.getOperatingIncome() - spro.getOperatingCost() - spro.getExpenseSales() - spro.getAdministrativeExpenses()
                            - spro.getResearchDevelopmentExpenses() - spro.getFinancialExpenses() - spro.getBusinessTaxesSurcharges());
                }

                //12、主营利润率=主营利润/营业收入*100%
                target.setOperatingProfitRate(NumberUtils.maintainAccuracy(target.getMainProfit(), spro.getOperatingIncome()));

                //12.2、12.1、主营利润占营业利润的比率=主营利润/营业利润*100%
                target.setRatioMainOperating(NumberUtils.maintainAccuracy(target.getMainProfit(), spro.getOperatingProfit()));

                //14、净资产收益率ROE=归属于母公司股东的净利润/归属于母公司的所有者权益合计*100%
                target.setRoe(NumberUtils.maintainAccuracy(spro.getTtm(), sbala.getTotalOwnersEquity()));

                SProfitStatementVO lastYearSpro = sProfitStatementService.select(vo.getCompanyId(), vo.getYear() - 1);
                if (lastYearSpro != null) {
                    //8、营业收入增长率=（本年营业收入-上年营业收入）/上年营业收入*100%
                    target.setIncreaseRateBusinessRevenue(NumberUtils.maintainAccuracy(spro.getOperatingIncome() - lastYearSpro.getOperatingIncome(), lastYearSpro.getOperatingIncome()));

                    //上年毛利率
                    int g1 = NumberUtils.maintainAccuracy(lastYearSpro.getOperatingIncome() - lastYearSpro.getOperatingCost(), lastYearSpro.getOperatingIncome());
                    //9.1、毛利率波幅=（本年毛利率-上年毛利率）/上年毛利率
                    target.setGrossMarginFluctuation(NumberUtils.maintainAccuracy(g - g1, g1));

                    //14.1、归母净利润增长率=（本年的归母净利润-上年的归母净利润）/上年的归母净利润*100%
                    target.setGrowthRateNetProfitAttributable(NumberUtils.maintainAccuracy(spro.getTtm() - lastYearSpro.getTtm(), lastYearSpro.getTtm()));
                }
                SChartCashFlowVO scha = sChartCashFlowService.select(vo.getCompanyId(), vo.getYear());
                if (scha != null) {

                    //13、净利润现金比率=经营活动产生的现金流量净额/净利润*100%
                    target.setNetProfitCashRatio(NumberUtils.maintainAccuracy(scha.getOperatingCashFlow(), spro.getNetProfit()));

                    //15、经营活动产生的现金流量净额
                    target.setOperatingCashFlow(scha.getOperatingCashFlow());

                    SChartCashFlowVO lastYearScha = sChartCashFlowService.select(vo.getCompanyId(), vo.getYear() - 1);
                    //15.1经营活动产生的现金流量净额增长率=（本期经营活动产生的现金流量净额-上期经营活动产生的现金流量净额）/上期经营活动产生的现金流量净额。
                    if (lastYearScha != null) {
                        target.setGrowthRateNetCashFlow(NumberUtils.maintainAccuracy(scha.getOperatingCashFlow() - lastYearScha.getOperatingCashFlow(), lastYearScha.getOperatingCashFlow()));
                    }

                    //16、购建固定资产、无形资产和其他长期资产支付的现金，了解公司的增长潜力
                    //比率=购买固定资产、无形资产和其他长期资产支付的现金/经营活动产生的现金流量净额*100%
                    target.setRatioCashPaid(NumberUtils.maintainAccuracy(scha.getPurchaseConstructionFixedAssets(), scha.getOperatingCashFlow()));

                    //17、分红
                    //年度股利支付率=分配股利、利润或偿付利息支付的现金/经营活动产生的现金流量净额*100%
                    target.setShareOutBonus(NumberUtils.maintainAccuracy(scha.getShareBonus(), scha.getOperatingCashFlow()));

                    //18、公司类型
                    //经营活动产生的现金流量净额、投资活动产生的现金流量净额、筹资活动产生的现金流量净额
                    if (scha.getOperatingCashFlow() > 0) {
                        if (scha.getCashFlowsInvestingActivities() > 0) {
                            if (scha.getNetCashFlowsFinancingActivities() > 0) {
                                target.setCompactType("正正正");
                            } else {
                                target.setCompactType("正正负");
                            }
                        } else {
                            if (scha.getNetCashFlowsFinancingActivities() > 0) {
                                target.setCompactType("正负正");
                            } else {
                                target.setCompactType("正负负");
                            }
                        }
                    } else {
                        if (scha.getCashFlowsInvestingActivities() > 0) {
                            if (scha.getNetCashFlowsFinancingActivities() > 0) {
                                target.setCompactType("负正正");
                            } else {
                                target.setCompactType("负正负");
                            }
                        } else {
                            if (scha.getNetCashFlowsFinancingActivities() > 0) {
                                target.setCompactType("负负正");
                            } else {
                                target.setCompactType("负负负");
                            }
                        }
                    }
                }
            }

        }
        sCompanyTargetMapper.insertData(target);

        SGoodPriceCalVO sgod = new SGoodPriceCalVO();
        sgod.setCompanyId(vo.getCompanyId());
        sGoodPriceService.calculateGoodPrice(sgod);

    }

    @Override
    public void deleteCompanyTarget(SCompanyTargetVO vo) {
        sCompanyTargetMapper.deleteCompanyTarget(vo.getId());
    }


}
