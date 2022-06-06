package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class HotCompanyCombogridVO {

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
    private String hotTypeIds;

    /**
     * 当日隶属热点ID
     */
    private Integer hotTypeId;

    /**
     * 当日隶属热点
     */
    private String hotTypeName;

    /**
     * 历史最大换手，百分比存4位数
     */
    private BigDecimal maxChange;

    /**
     * 当前连扳数
     */
    private Integer continuityTime;


    private String fullTime;

    private int sort;


}
