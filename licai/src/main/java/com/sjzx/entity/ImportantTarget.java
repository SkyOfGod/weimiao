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
 * 重要指标表
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ImportantTarget extends Model<ImportantTarget> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司详情表主键
     */
    private Integer companyId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 1-年报 2-第三季度报 3-半年报 4-第一季度报
     */
    private Integer reportType;

    /**
     * 指标表tp1
     */
    private Integer tp1;

    /**
     * 指标表tp2
     */
    private Integer tp2;

    /**
     * 指标表tp3
     */
    private Integer tp3;

    /**
     * 指标表tp4
     */
    private Integer tp4;

    /**
     * 指标表tp5
     */
    private Integer tp5;

    /**
     * 指标表tp6
     */
    private Integer tp6;

    /**
     * 指标表tp7
     */
    private Integer tp7;

    /**
     * 指标表tp8
     */
    private Integer tp8;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
