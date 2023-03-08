package com.sjzx.model.vo.output;

import lombok.Data;

import java.util.Date;


@Data
public class SProfitStatementVO {
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
     * 营业收入（万元）
     */
    private Integer operatingIncome;

    /**
     * 营业成本（万元）
     */
    private Integer operatingCost;

    /**
     * 销售费用（万元）
     */
    private Integer expenseSales;

    /**
     * 管理费用（万元）
     */
    private Integer administrativeExpenses;

    /**
     * 研发费用（万元）
     */
    private Integer researchDevelopmentExpenses;

    /**
     * 财务费用（万元）
     */
    private Integer financialExpenses;

    /**
     * 营业税金及附加（万元）
     */
    private Integer businessTaxesSurcharges;

    /**
     * 营业利润（万元）
     */
    private Integer operatingProfit;

    /**
     * 净利润（万元）
     */
    private Integer netProfit;

    /**
     * 归属于母公司所有者的净利润（万元）
     */
    private Integer ttm;

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
