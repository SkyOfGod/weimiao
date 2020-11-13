package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CashFlowStatisticsVO {

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
     * 经营活动产生的现金流量净额
     */
    private Long businessToProfit;

    /**
     * 分红金额
     */
    private Long bonusCash;

    /**
     * 现金净增额 经营活动产生的现金流量净额-分红
     */
    private Long profitSubstractBonus;

    /**
     * 销售商品提供劳务收到的现金/营业收入
     */
    private String cashInIncoming;

    /**
     * 购建资产/经营活动产生的现金流量净额
     */
    private String expandInProfitRate;

    /**
     * 处置资产/购建资产
     */
    private String sellInExpandRate;

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
