package com.sjzx.model.vo.output;

import lombok.Data;

import java.util.Date;

@Data
public class SGoodPriceVO {


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
     * 好价格
     */
    private Integer goodPrice;

    /**
     * 净利润增长率
     */
    private Integer netProfitGrowthRate;

    /**
     * 净利润复合增长率
     */
    private Integer compoundGrowthRate;

    /**
     * 机构净利润增长率 前端
     */
    private Integer institutionalGrowthRate;

    /**
     * 未来三年的净利润（万元）
     */
    private Integer netProfitNextThreeYears;

    /**
     * 未来三年的合理估值（万元）
     */
    private Integer reasonableValuationNextThreeYears;

    /**
     * 合理市盈率 前端
     */
    private Integer earningsRatio;

    /**
     * 总股本（万股）前端
     */
    private Integer generalCapital;

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
