package com.sjzx.model.vo.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String hotTypeId;

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
     * 复盘日期
     */
    private String dataDate;

    /**
     * 异动 异动次数/第一次异动到复盘日期交易日数
     */
    private String changeTotal;

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
     * 0-未选中  1-选中
     */
    private Integer onSelected = 0;

    /**
     * 备注
     */
    private String remark;

}
