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
 * 合并资产负债表
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsolidatedAssetsLiabilities extends Model<ConsolidatedAssetsLiabilities> {

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
     * 1-年报 2-第三季度报 3-半年报
     */
    private Integer reportType;

    /**
     * 总资产
     */
    private Long totalAssets;

    /**
     * 总负债
     */
    private Long totalLiabilities;

    /**
     * 股东权益合计
     */
    private Long shareHolderEquity;

    /**
     * 短期负债
     */
    private Long shortTermLiabilities;

    /**
     * 一年内到期的非流动负债
     */
    private Long yearArriveNoCurrentLiabilities;

    /**
     * 长期借款
     */
    private Long longTermLiabilities;

    /**
     * 应付债券
     */
    private Long payableBonds;

    /**
     * 长期应付款
     */
    private Long longPayableMoney;

    /**
     * 应付利息
     */
    private Long payableInterest;

    /**
     * 货币资金
     */
    private Long monetaryCapital;

    /**
     * 应付票据
     */
    private Long payableBill;

    /**
     * 应付账款
     */
    private Long payableMoney;

    /**
     * 预收款项
     */
    private Long advanceReceivableMoney;

    /**
     * 应收票据
     */
    private Long receivableBill;

    /**
     * 应收账款
     */
    private Long receivableMoney;

    /**
     * 预付款项
     */
    private Long advancePayableMoney;

    /**
     * 固定资产
     */
    private Long fixedAssets;

    /**
     * 在建工程
     */
    private Long reconstructionProject;

    /**
     * 工程物资
     */
    private Long engineeringMaterials;

    /**
     * 以公允价值计量且变动计入当期损益的金融资产
     */
    private Long fairValueProject;

    /**
     * 可供出售金融资产
     */
    private Long prepareSaleFinanceProject;

    /**
     * 持有至到期投资
     */
    private Long heldToMaturityInvestment;

    /**
     * 投资房地产
     */
    private Long investinInRealEstate;

    /**
     * 长期股权投资（与主业无关）
     */
    private Long longTermEquityInvestment;

    /**
     * 归属于母公司所有者权益合计
     */
    private Long belongMotherEquity;

    /**
     * 应付职工薪酬
     */
    private Long payableSalary;

    /**
     * 当期总股本
     */
    private Long totalEquity;

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
