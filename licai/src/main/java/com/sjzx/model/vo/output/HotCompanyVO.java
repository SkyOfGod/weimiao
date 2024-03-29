package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class HotCompanyVO {

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
     * 历史最大换手，百分比存4位数
     */
    private BigDecimal maxChange;

    /**
     * 概念1
     */
    private String hotType1;

    /**
     * 概念2
     */
    private String hotType2;

    /**
     * 概念3
     */
    private String hotType3;

    /**
     * 概念4
     */
    private String hotType4;

    /**
     * 概念5
     */
    private String hotType5;

    /**
     * 概念6
     */
    private String hotType6;

    /**
     * 概念7
     */
    private String hotType7;

    /**
     * 概念8
     */
    private String hotType8;

    /**
     * 概念9
     */
    private String hotType9;

    /**
     * 概念10
     */
    private String hotType10;

    /**
     * 最近连扳次数
     */
    private Integer continuityTime;

    /**
     * 首板日期
     */
    private String firstDate;

    /**
     * 流通股占比(%)
     */
    private BigDecimal percent;

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

    /**
     * 流通市值(亿)
     */
    private BigDecimal circulationMarketValue;

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


}
