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
 * 合并现金流量表
 * </p>
 *
 * @author 
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CombineCashFlow extends Model<CombineCashFlow> {

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
     * 经营活动产生的现金流量净额
     */
    private Long businessToProfit;

    /**
     * 投资活动产生的现金流量净额
     */
    private Long investmentToProfit;

    /**
     * 筹资活动产生的现金流量净额
     */
    private Long financingToProfite;

    /**
     * 销售商品提供劳务收到的现金(含增值税)
     */
    private Long saleToCash;

    /**
     * 支付给职工以及为职工支付的现金
     */
    private Long salary;

    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金
     */
    private Long investmentInsideOut;

    /**
     * 处置固定资产、无形资产和其他长期资产收回的现金净额
     */
    private Long investmentInsideIn;

    /**
     * 收购或投资子公司，对外扩大经营
     */
    private Long investmentOutsideSubOut;

    /**
     * 投资支付的现金
     */
    private Long investmentOutsideMoneyOut;

    /**
     * 现金及现金等价物净增加额
     */
    private Long cashAndCashEquivalentsAdd;

    /**
     * 期初现金及现金等价物余额
     */
    private Long cashAndCashEquivalentsBegin;

    /**
     * 期末现金及现金等价物余额
     */
    private Long cashAndCashEquivalentsEnd;

    /**
     * 分红
     */
    private Long bonusCash;

    /**
     * 员工总数
     */
    private Long staffTotal;

    /**
     * PE最小值
     */
    private BigDecimal peMin;

    /**
     * PE最大值
     */
    private BigDecimal peMax;

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
