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
 * 热点公司复盘表
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotCompanyData extends Model<HotCompanyData> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 热点公司ID
     */
    private Integer hotCompanyId;

    /**
     * 当日隶属热点ID
     */
    private Integer hotTypeId;

    /**
     * 历史最大换手，百分比存4位数
     */
    private BigDecimal maxChange;

    /**
     * 近期对手盘换手，百分比存2位数
     */
    private BigDecimal nearChange;

    /**
     * 第一次爆量
     */
    private BigDecimal oneMinuteValue;

    /**
     * 当前流通市值（亿元）
     */
    private BigDecimal circulationMarketValue;

    /**
     * 当日涨停时间
     */
    private String fullTime;

    /**
     * 当前连扳次数
     */
    private Integer continuityTime;

    /**
     * 今日封单
     */
    private BigDecimal todayNoDeal;

    /**
     * 明日封单金额
     */
    private BigDecimal noDeal;

    /**
     * 复盘时间
     */
    private String dataDate;

    /**
     * 异动 异动次数/第一次异动到复盘日期交易日数
     */
    private String changeTotal;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0-未选中  1-选中
     */
    private Integer onSelected;

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
