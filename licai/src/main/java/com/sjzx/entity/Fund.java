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
 * 基金表
 * </p>
 *
 * @author 
 * @since 2021-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Fund extends Model<Fund> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 基金代码
     */
    private String code;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 基金创建时价格
     */
    private String fundCreatePrice;

    /**
     * 基金创建时间
     */
    private String fundCreateTime;

    /**
     * 管理基金经理（中间用逗号隔开）
     */
    private String fundOperator;

    /**
     * 基金经理管理时长（中间用逗号隔开）
     */
    private String fundOperatorLong;

    /**
     * 基金资产规模（格式 年月日/规模，中间用逗号隔开）
     */
    private String fundAssertScale;

    /**
     * 平均年化收益率
     */
    private Integer averageIncome;

    /**
     * 排序
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
