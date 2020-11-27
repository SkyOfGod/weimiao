package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ImportantTargetVO {

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
     * 1-年报 2-第三季度报 3-半年报 4-第一季度报
     */
    private String reportType;


    /**
     * 指标表tp1
     */
    private String tp1;

    /**
     * 指标表tp2
     */
    private String tp2;

    /**
     * 指标表tp3
     */
    private String tp3;

    /**
     * 指标表tp4
     */
    private String tp4;

    /**
     * 指标表tp5
     */
    private String tp5;

    /**
     * 指标表tp6
     */
    private String tp6;

    /**
     * 指标表tp7
     */
    private String tp7;

    /**
     * 指标表tp8
     */
    private String tp8;

    /**
     * 指标表ta1
     */
    private Long ta1;

    /**
     * 指标表ta2
     */
    private BigDecimal ta2;

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
