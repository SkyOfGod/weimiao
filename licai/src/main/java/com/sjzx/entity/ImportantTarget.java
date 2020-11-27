package com.sjzx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
     * 指标表t1
     */
    private Integer t1;

    /**
     * 指标表t2
     */
    private Integer t2;

    /**
     * 指标表t3
     */
    private Integer t3;

    /**
     * 指标表t4
     */
    private Integer t4;

    /**
     * 指标表t5
     */
    private Integer t5;

    /**
     * 指标表t6
     */
    private Integer t6;

    /**
     * 指标表t7
     */
    private Integer t7;

    /**
     * 指标表t8
     */
    private Integer t8;

    /**
     * 指标表t9
     */
    private Long t9;

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
