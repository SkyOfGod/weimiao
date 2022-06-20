package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class HotCompanyDataVO {

    private Integer id;

    /**
     * 公司代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 热点ID
     */
    private String hotTypeId;

    /**
     * 热点
     */
    private String hotType;

    /**
     * 最大换手
     */
    private String maxChange;

    /**
     * 流通股占比(%)
     */
    private BigDecimal percent;

    /**
     * 流通市值(亿)
     */
    private BigDecimal circulationMarketValue;

    /**
     * 今日封单金额
     */
    private BigDecimal todayNoDeal;

    /**
     * 昨日封单金额
     */
    private BigDecimal yesterdayNoDeal;

    /**
     * 下一个交易日封单金额
     */
    private BigDecimal noDeal;

    /**
     * 安全换手
     */
    private BigDecimal safeChange;

    /**
     * 近期对手盘换手，百分比存2位数
     */
    private BigDecimal nearChange;

    /**
     * 第一次钟爆量
     */
    private BigDecimal oneMinuteValue;

    /**
     * 下一个交易日第一分钟爆量开盘预计爆量
     */
    private BigDecimal tomorrowOneMinuteValue;

    /**
     * 第一分钟爆量占近期对手盘比例
     */
    private BigDecimal oneMinuteValuePercent;

    /**
     * 安全换值(亿)
     */
    private BigDecimal safeChangeMarketValue;

    /**
     * 封单率
     */
    private BigDecimal todayNoDealPercent;

    /**
     * 涨停时间
     */
    private String fullTime;

    /**
     * 当前连扳数
     */
    private Integer continuityTime;

    /**
     * 首扳数
     */
    private Integer firstTime;

    /**
     * 二扳数
     */
    private Integer secondTime;

    /**
     * 三扳数
     */
    private Integer thirdTime;

    /**
     * 四扳数
     */
    private Integer forthTime;

    /**
     * 五扳数
     */
    private Integer fifthTime;

    /**
     * 六扳数
     */
    private Integer sixthTime;

    /**
     * 七扳数
     */
    private Integer seventhTime;

    /**
     * 热点
     */
    private String hotTypeName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0-未选中  1-选中
     */
    private Integer onSelected;

    /**
     * 复盘日期
     */
    private String dataDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 总备注
     */
    private String totalRemark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    private String hotTypeIds;

}
