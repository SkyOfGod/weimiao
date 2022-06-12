package com.sjzx.model.vo.input;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class HotCompanyDataAddVO {

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
    private Integer hotTypeId;

    /**
     * 最大换手
     */
    private BigDecimal maxChange;

    /**
     * 近期对手盘换手，百分比存2位数
     */
    private BigDecimal nearChange;

    /**
     * 第一分钟爆量
     */
    private BigDecimal oneMinuteValue;

    /**
     * 流通市值(亿)
     */
    private BigDecimal circulationMarketValue;

    /**
     * 涨停时间
     */
    private String fullTime;

    /**
     * 当前连扳次数
     */
    private Integer continuityTime;

    /**
     * 今日封单
     */
    private BigDecimal todayNoDeal;

    /**
     * 封单
     */
    private BigDecimal noDeal;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

}
