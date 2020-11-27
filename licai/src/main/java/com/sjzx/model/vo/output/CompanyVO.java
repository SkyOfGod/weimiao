package com.sjzx.model.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
@Data
@Accessors(chain = true)
public class CompanyVO {

    private Integer id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 行业类型
     */
    private String type;

    /**
     * 归属市场
     */
    private String location;

    /**
     * 类型 1-股票 2-基金 3-Reits 4-可转债 5-逆回购
     */
    private String category;

    /**
     * 总股本
     */
    private Long totalEquity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 指标级别
     */
    private Integer targetLevel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
