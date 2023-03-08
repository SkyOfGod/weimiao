package com.sjzx.model.vo.output;

import lombok.Data;

import java.util.Date;

@Data
public class SBalanceSheetVO {

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
     * 资产合计（总资产）(万元)
     */
    private Integer totalAssets;

    /**
     * 负债合计(总负债)(万元)
     */
    private Integer totalLiabilities;

    /**
     * 货币资金(万元)
     */
    private Integer monetaryFunds;

    /**
     * 交易性金融资产(万元)
     */
    private Integer tradingFinancialAssets;

    /**
     * 其他流动资产(万元)
     */
    private Integer otherCurrentAssets;

    /**
     * 短期借款(万元)
     */
    private Integer shortTermBorrowing;

    /**
     * 一年到期的非流动负债(万元)
     */
    private Integer nonCurrentLiability;

    /**
     * 长期借款(万元)
     */
    private Integer longTermBorrowing;

    /**
     * 长期应付款合计(万元)
     */
    private Integer totalLongTermPayables;

    /**
     * 应付债券(万元)
     */
    private Integer bondsPayable;

    /**
     * 应付票据(万元)
     */
    private Integer notesPayable;

    /**
     * 应付账款(万元)
     */
    private Integer accountsPayable;

    /**
     * 应付票据及应付账款(万元)
     */
    private Integer notesAccounts;

    /**
     * 预收款项(万元)
     */
    private Integer accountCollectedAdvance;

    /**
     * 合同负债（万元）
     */
    private Integer liabilityForContract;

    /**
     * 应收票据（万元）
     */
    private Integer notesReceivable;

    /**
     * 应收账款（万元）
     */
    private Integer accountsReceivable;

    /**
     * 预付款项（万元）
     */
    private Integer prepayment;

    /**
     * 固定资产合计(万元)
     */
    private Integer totalFixedAssets;

    /**
     * 固定资产(万元)
     */
    private Integer fixedAssets;

    /**
     * 在建工程合计(万元)
     */
    private Integer totalWorksInProgress;

    /**
     * 在建工程(万元)
     */
    private Integer workInProgress;

    /**
     * 工程物资(万元)
     */
    private Integer materialsForConstruction;

    /**
     * 可供出售的金融资产（万元）
     */
    private Integer financialAssetsSale;

    /**
     * 持有到期的金融资产（万元）
     */
    private Integer holdFinancialAssets;

    /**
     * 长期股权投资（万元）
     */
    private Integer longTermEquityInvestment;

    /**
     * 投资性房地产（万元）
     */
    private Integer investmentRealEstate;

    /**
     * 其他权益工具投资（万元）
     */
    private Integer investmentInOther;

    /**
     * 其他非流动的金融资产（万元)
     */
    private Integer otherNonCurrentFinancialAssets;

    /**
     * 存货（万元）
     */
    private Integer inventory;

    /**
     * 商誉（万元）
     */
    private Integer goodwill;

    /**
     * 归属于母公司的所有者权益合计（万元）
     */
    private Integer totalOwnersEquity;

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
