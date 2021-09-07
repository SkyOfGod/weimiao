package com.sjzx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 基金持股表
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FundCompany extends Model<FundCompany> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基金ID
     */
    private Integer fundId;

    /**
     * 公司ID
     */
    private Integer companyId;

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
     * 持股数（中间用逗号隔开，单位：万股）
     */
    private String total;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
