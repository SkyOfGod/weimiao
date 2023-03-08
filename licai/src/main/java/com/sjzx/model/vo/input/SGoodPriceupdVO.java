package com.sjzx.model.vo.input;

import lombok.Data;

@Data
public class SGoodPriceupdVO {

    private Integer id;

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 机构净利润增长率 前端
     */
    private Integer institutionalGrowthRate;

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
     * 未来三年的净利润（万元）
     */
    private Integer netProfitNextThreeYears;

    /**
     * 未来三年的合理估值（万元）
     */
    private Integer reasonableValuationNextThreeYears;

    /**
     * 好价格
     */
    private Integer goodPrice;

    /**
     * 净利润增长率
     */
    private Integer netProfitGrowthRate;
}
