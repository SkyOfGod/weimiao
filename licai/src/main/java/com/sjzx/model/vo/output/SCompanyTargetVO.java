package com.sjzx.model.vo.output;


import lombok.Data;

import java.util.Date;

@Data
public class SCompanyTargetVO {


    private Integer id;

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 总资产增长率(%)
     */
    private Integer growthRateTotalAssets = 0;

    /**
     * 总负债增长率(%）
     */
    private Integer totalDebtGrowthRate = 0;

    /**
     * 准货币资金减有息负债的差额(万元)
     */
    private Integer difference = 0;

    /**
     * 准货币资金（万元） *****
     */
    private Integer quasiMonetaryFunds = 0;

    /**
     * 有息负债（万元）  *****
     */
    private Integer liabilityInterest = 0;

    /**
     * 应付预收减应收预付差额（万元）
     */
    private Integer differencePayableReceivable = 0;

    /**
     * 应收账款占总资产的比率(%）
     */
    private Integer accountsReceivablePercentage = 0;

    /**
     * 固定资产比例(%）
     */
    private Integer proportionFixedAssets = 0;

    /**
     * 投资类资产占总资产的比率(%）
     */
    private Integer ratioInvestmentAssets = 0;

    /**
     * 投资类资产合计（万元）
     */
    private Integer totalInvestmentAssets = 0;

    /**
     * 存货占总资产的比率(%）
     */
    private Integer ratioInventory = 0;

    /**
     * 商誉占总资产的比率(%）
     */
    private Integer ratioGoodwill = 0;

    /**
     * 营业收入增长率(%）
     */
    private Integer increaseRateBusinessRevenue = 0;

    /**
     * 毛利率(%）
     */
    private Integer grossProfitRate = 0;

    /**
     * 毛利率波幅(%）
     */
    private Integer grossMarginFluctuation = 0;

    /**
     * 期间费用率与毛利率的比率(%）
     */
    private Integer ratioPeriodExpenseGross = 0;

    /**
     * 期间费用率(%）
     */
    private Integer periodExpenseRate = 0;

    /**
     * 销售费用率(%）
     */
    private Integer ratioExpensesSales = 0;

    /**
     * 主营利润率(%）
     */
    private Integer operatingProfitRate = 0;

    /**
     * 主营利润(%）
     */
    private Integer mainProfit = 0;

    /**
     * 主营利润占营业利润的比率(%）
     */
    private Integer ratioMainOperating = 0;

    /**
     * 净利润现金比率(%）
     */
    private Integer netProfitCashRatio = 0;

    /**
     * 净资产收益率(%）
     */
    private Integer roe = 0;

    /**
     * 归母净利润增长率(%）
     */
    private Integer growthRateNetProfitAttributable = 0;

    /**
     * 经营活动产生的现金流量净额（万元）
     */
    private Integer operatingCashFlow = 0;

    /**
     * 经营活动产生的现金流量净额增长率(%）
     */
    private Integer growthRateNetCashFlow = 0;

    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金占经营活动产生的现金流量净额比率(%）
     */
    private Integer ratioCashPaid = 0;

    /**
     * 年度股利支付率/分红(%）
     */
    private Integer shareOutBonus = 0;

    /**
     * 公司类型(正负类型)
     */
    private String compactType = null;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
