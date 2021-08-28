package com.sjzx.model.vo.output;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 基金表
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@Data
public class FundCompanyVO {

    private Integer id;

    /**
     * 基金ID
     */
    private Integer fundId;
    private String fundCode;
    private String fundName;

    /**
     * 公司ID
     */
    private Integer companyId;
    private String companyCode;
    private String companyName;

    /**
     * 建仓时间
     */
    private String joinTime;

    /**
     * 撤离时间
     */
    private String leaveTime;

    /**
     * 占基金比例(格式 年月日/比例，中间用逗号隔开)
     */
    private String percent;

    /**
     * 股价
     */
    private BigDecimal price;

    /**
     * 计算TTM的财报年限
     */
    private Integer year;

    /**
     * TTM动态市盈率
     */
    private Integer ttm;

    /**
     * 计算TTM时间
     */
    private Date ttmTime;

    /**
     * 0-无效 1-有效
     */
    private Integer validIs;

    /**
     * 排序值
     */
    private Integer sort;

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
