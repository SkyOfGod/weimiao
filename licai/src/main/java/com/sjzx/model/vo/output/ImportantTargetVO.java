package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

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
     * 指标表t1
     */
    private String t1;

    /**
     * 指标表t2
     */
    private String t2;

    /**
     * 指标表t3
     */
    private String t3;

    /**
     * 指标表t4
     */
    private String t4;

    /**
     * 指标表t5
     */
    private String t5;

    /**
     * 指标表t6
     */
    private String t6;

    /**
     * 指标表t7
     */
    private String t7;

    /**
     * 指标表t8
     */
    private String t8;

    /**
     * 指标表t9
     */
    private String t9;

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
