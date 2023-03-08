package com.sjzx.model.vo.output;


import lombok.Data;

import java.util.Date;

@Data
public class SChartCashFlowVO {

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
     * 经营活动产生的现金流量净额（万元）
     */
    private Integer operatingCashFlow;

    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金（万元）
     */
    private Integer purchaseConstructionFixedAssets;

    /**
     * 分配股利、利润或偿付利息支付的现金（万元）
     */
    private Integer shareBonus;

    /**
     * 投资活动产生的现金流量净额（万元）
     */
    private Integer cashFlowsInvestingActivities;

    /**
     * 筹资活动产生的现金流量净额（万元）
     */
    private Integer netCashFlowsFinancingActivities;

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
