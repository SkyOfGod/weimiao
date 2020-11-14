package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 合并利润表统计
 * </p>
 *
 * @author 
 * @since 2020-11-03
 */
@Data
@Accessors(chain = true)
public class ProfitStatisticsVO {

    private Integer id;

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 参考标准 1-年报 2-第三季度报 3-半年报 4-第一季度报
     */
    private String reportType;

    /**
     * 营业收入增速
     */
    private String businessIncomeGrowthRate;

    /**
     * 毛利率
     */
    private String grossProfitMargin;

    /**
     * 费用率
     */
    private String costRate;

    /**
     * 费用率/毛利率
     */
    private String costInProfit;

    /**
     * 主营利润
     */
    private Long mainProfit;

    /**
     * 经营活动产生的现金流量净额(现金流量表)
     */
    private Long businessToProfit;

    /**
     * 利润质量 经营活动产生的现金流量净额/主营利润
     */
    private String profitQuality;

    /**
     * 利润质量 经营活动产生的现金流量净额/净利润
     */
    private String profitQualityOne;

    /**
     * 主营利润/利润总额
     */
    private String mainProfitInProfitTotal;

    /**
     * 主营利润率 主营利润/营业收入
     */
    private String mainProfitInIncomeTotal;

    /**
     * 归属于母公司所有者的净利润
     */
    private Long belongMotherNetProfit;

    /**
     * 归属于母公司所有者的净利润增速
     */
    private String belongMotherNetProfitGrowthRate;

    /**
     * 总股本
     */
    private Long totalEquity;

    /**
     * 每股收益
     */
    private BigDecimal sharesProfit;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
