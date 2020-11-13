package com.sjzx.model.vo.input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static com.sjzx.utils.NumberUtils.toLong;

/**
 * <p>
 * 合并资产负债表
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsolidatedAssetsLiabilitiesAddVO {

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 1-年报 2-半年报 3-第三季度报
     */
    private Integer reportType;

    /**
     * 总资产
     */
    private Long totalAssets;

    /**
     * 总负债
     */
    private Long totalLiabilities;

    /**
     * 股东权益合计
     */
    private Long shareHolderEquity;

    /**
     * 短期负债
     */
    private Long shortTermLiabilities;

    /**
     * 一年内到期的非流动负债
     */
    private Long yearArriveNoCurrentLiabilities;

    /**
     * 长期借款
     */
    private Long longTermLiabilities;

    /**
     * 应付债券
     */
    private Long payableBonds;

    /**
     * 长期应付款
     */
    private Long longPayableMoney;

    /**
     * 应付利息
     */
    private Long payableInterest;

    /**
     * 货币资金
     */
    private Long monetaryCapital;

    /**
     * 应付票据
     */
    private Long payableBill;

    /**
     * 应付账款
     */
    private Long payableMoney;

    /**
     * 预收款项
     */
    private Long advanceReceivableMoney;

    /**
     * 应收票据
     */
    private Long receivableBill;

    /**
     * 应收账款
     */
    private Long receivableMoney;

    /**
     * 预付款项
     */
    private Long advancePayableMoney;

    /**
     * 固定资产
     */
    private Long fixedAssets;

    /**
     * 在建工程
     */
    private Long reconstructionProject;

    /**
     * 工程物资
     */
    private Long engineeringMaterials;

    /**
     * 以公允价值计量且变动计入当期损益的金融资产
     */
    private Long fairValueProject;

    /**
     * 可供出售金融资产
     */
    private Long prepareSaleFinanceProject;

    /**
     * 持有至到期投资
     */
    private Long heldToMaturityInvestment;

    /**
     * 投资房地产
     */
    private Long investinInRealEstate;

    /**
     * 长期股权投资（与主业无关）
     */
    private Long longTermEquityInvestment;

    /**
     * 归属于母公司所有者权益合计
     */
    private Long belongMotherEquity;

    /**
     * 应付职工薪酬
     */
    private Long payableSalary;

    /**
     * 当期总股本
     */
    private Long totalEquity;

    /**
     * 备注
     */
    private String remark;

    public void setTotalEquity(String totalEquity) {
        this.totalEquity = toLong(totalEquity);
    }

    public void setPayableSalary(String payableSalary) {
        this.payableSalary = toLong(payableSalary);
    }

    public void setBelongMotherEquity(String belongMotherEquity) {
        this.belongMotherEquity = toLong(belongMotherEquity);
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = toLong(totalAssets);
    }

    public void setTotalLiabilities(String totalLiabilities) {
        this.totalLiabilities = toLong(totalLiabilities);
    }

    public void setShareHolderEquity(String shareHolderEquity) {
        this.shareHolderEquity = toLong(shareHolderEquity);
    }

    public void setShortTermLiabilities(String shortTermLiabilities) {
        this.shortTermLiabilities = toLong(shortTermLiabilities);
    }

    public void setYearArriveNoCurrentLiabilities(String yearArriveNoCurrentLiabilities) {
        this.yearArriveNoCurrentLiabilities = toLong(yearArriveNoCurrentLiabilities);
    }

    public void setLongTermLiabilities(String longTermLiabilities) {
        this.longTermLiabilities = toLong(longTermLiabilities);
    }

    public void setPayableBonds(String payableBonds) {
        this.payableBonds = toLong(payableBonds);
    }

    public void setLongPayableMoney(String longPayableMoney) {
        this.longPayableMoney = toLong(longPayableMoney);
    }

    public void setPayableInterest(String payableInterest) {
        this.payableInterest = toLong(payableInterest);
    }

    public void setMonetaryCapital(String monetaryCapital) {
        this.monetaryCapital = toLong(monetaryCapital);
    }

    public void setPayableBill(String payableBill) {
        this.payableBill = toLong(payableBill);
    }

    public void setPayableMoney(String payableMoney) {
        this.payableMoney = toLong(payableMoney);
    }

    public void setAdvanceReceivableMoney(String advanceReceivableMoney) {
        this.advanceReceivableMoney = toLong(advanceReceivableMoney);
    }

    public void setReceivableBill(String receivableBill) {
        this.receivableBill = toLong(receivableBill);
    }

    public void setReceivableMoney(String receivableMoney) {
        this.receivableMoney = toLong(receivableMoney);
    }

    public void setAdvancePayableMoney(String advancePayableMoney) {
        this.advancePayableMoney = toLong(advancePayableMoney);
    }

    public void setFixedAssets(String fixedAssets) {
        this.fixedAssets = toLong(fixedAssets);
    }

    public void setReconstructionProject(String reconstructionProject) {
        this.reconstructionProject = toLong(reconstructionProject);
    }

    public void setEngineeringMaterials(String engineeringMaterials) {
        this.engineeringMaterials = toLong(engineeringMaterials);
    }

    public void setFairValueProject(String fairValueProject) {
        this.fairValueProject = toLong(fairValueProject);
    }

    public void setPrepareSaleFinanceProject(String prepareSaleFinanceProject) {
        this.prepareSaleFinanceProject = toLong(prepareSaleFinanceProject);
    }

    public void setHeldToMaturityInvestment(String heldToMaturityInvestment) {
        this.heldToMaturityInvestment = toLong(heldToMaturityInvestment);
    }

    public void setInvestinInRealEstate(String investinInRealEstate) {
        this.investinInRealEstate = toLong(investinInRealEstate);
    }

    public void setLongTermEquityInvestment(String longTermEquityInvestment) {
        this.longTermEquityInvestment = toLong(longTermEquityInvestment);
    }
}
