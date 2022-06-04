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
 * 龙头战法热点公司
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotCompany extends Model<HotCompany> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 热点集合
     */
    private String hotTypeIds;

    /**
     * 最近连扳次数
     */
    private Integer continuityTime;

    /**
     * 最近首板日期
     */
    private String firstDate;

    /**
     * 当日隶属热点ID
     */
    private Integer hotTypeId;

    /**
     * 历史最大换手，百分比存4位数
     */
    private BigDecimal maxChange;

    /**
     * 当前流通市值（亿元）
     */
    private BigDecimal circulationMarketValue;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
