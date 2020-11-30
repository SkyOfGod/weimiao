package com.sjzx.model.vo.input;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static com.sjzx.utils.NumberUtils.toLong;
@Data
@Accessors(chain = true)
public class CombineCashFlowAddVO {

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 1-年报 2-第三季度报 3-半年报 4-第一季度报
     */
    private Integer reportType;

    /**
     * 经营活动产生的现金流量净额
     */
    private Long businessToProfit;

    /**
     * 投资活动产生的现金流量净额
     */
    private Long investmentToProfit;

    /**
     * 筹资活动产生的现金流量净额
     */
    private Long financingToProfite;

    /**
     * 销售商品提供劳务收到的现金(含增值税)
     */
    private Long saleToCash;

    /**
     * 支付给职工以及为职工支付的现金
     */
    private Long salary;

    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金
     */
    private Long investmentInsideOut;

    /**
     * 处置固定资产、无形资产和其他长期资产收回的现金净额
     */
    private Long investmentInsideIn;

    /**
     * 收购或投资子公司，对外扩大经营
     */
    private Long investmentOutsideSubOut;

    /**
     * 投资支付的现金
     */
    private Long investmentOutsideMoneyOut;

    /**
     * 现金及现金等价物净增加额
     */
    private Long cashAndCashEquivalentsAdd;

    /**
     * 期初现金及现金等价物余额
     */
    private Long cashAndCashEquivalentsBegin;

    /**
     * 期末现金及现金等价物余额
     */
    private Long cashAndCashEquivalentsEnd;

    /**
     * 每股分红
     */
    private BigDecimal bonusCash;

    /**
     * 员工总数
     */
    private Long staffTotal;

    /**
     * PE最小值
     */
    private BigDecimal peMin;

    /**
     * PE最大值
     */
    private BigDecimal peMax;


    /**
     * 备注
     */
    private String remark;

    public void setStaffTotal(String staffTotal) {
        this.staffTotal = toLong(staffTotal);
    }

    public void setBusinessToProfit(String businessToProfit) {
        this.businessToProfit = toLong(businessToProfit);
    }

    public void setInvestmentToProfit(String investmentToProfit) {
        this.investmentToProfit = toLong(investmentToProfit);
    }

    public void setFinancingToProfite(String financingToProfite) {
        this.financingToProfite = toLong(financingToProfite);
    }

    public void setSaleToCash(String saleToCash) {
        this.saleToCash = toLong(saleToCash);
    }

    public void setSalary(String salary) {
        this.salary = toLong(salary);
    }

    public void setInvestmentInsideOut(String investmentInsideOut) {
        this.investmentInsideOut = toLong(investmentInsideOut);
    }

    public void setInvestmentInsideIn(String investmentInsideIn) {
        this.investmentInsideIn = toLong(investmentInsideIn);
    }

    public void setInvestmentOutsideSubOut(String investmentOutsideSubOut) {
        this.investmentOutsideSubOut = toLong(investmentOutsideSubOut);
    }

    public void setInvestmentOutsideMoneyOut(String investmentOutsideMoneyOut) {
        this.investmentOutsideMoneyOut = toLong(investmentOutsideMoneyOut);
    }

    public void setCashAndCashEquivalentsAdd(String cashAndCashEquivalentsAdd) {
        this.cashAndCashEquivalentsAdd = toLong(cashAndCashEquivalentsAdd);
    }

    public void setCashAndCashEquivalentsBegin(String cashAndCashEquivalentsBegin) {
        this.cashAndCashEquivalentsBegin = toLong(cashAndCashEquivalentsBegin);
    }

    public void setCashAndCashEquivalentsEnd(String cashAndCashEquivalentsEnd) {
        this.cashAndCashEquivalentsEnd = toLong(cashAndCashEquivalentsEnd);
    }
}
