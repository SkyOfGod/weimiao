package com.sjzx.model.vo.excel;



import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;



@Data
public class ConsolidatedAssetsLiabilitiesExcelVO extends BaseRowModel implements Serializable{

    @ExcelProperty(value = "年份" ,index = 0)
    private Integer year;

    @ExcelProperty(value = "1-年报 2-第三季度报 3-半年报" ,index = 1)
    private String reportType;

    @ExcelProperty(value = "总资产" ,index = 2)
    private String totalAssets;

    @ExcelProperty(value = "总负债" ,index = 3)
    private String totalLiabilities;

    @ExcelProperty(value = "股东权益合计" ,index = 4)
    private String shareHolderEquity;

    @ExcelProperty(value = "短期负债" ,index = 5)
    private String shortTermLiabilities;

    @ExcelProperty(value = "一年内到期的非流动负债" ,index = 6)
    private String yearArriveNoCurrentLiabilities;

    @ExcelProperty(value = "长期借款" ,index = 7)
    private String longTermLiabilities;

    @ExcelProperty(value = "应付债券" ,index = 8)
    private String payableBonds;

    @ExcelProperty(value = "长期应付款" ,index = 9)
    private String longPayableMoney;

    @ExcelProperty(value = "应付利息" ,index = 10)
    private String payableInterest;

    @ExcelProperty(value = "货币资金" ,index = 11)
    private String monetaryCapital;

    @ExcelProperty(value = "应付票据" ,index = 12)
    private String payableBill;

    @ExcelProperty(value = "应付账款" ,index = 13)
    private String payableMoney;

    @ExcelProperty(value = "预收款项" ,index = 14)
    private String advanceReceivableMoney;

    @ExcelProperty(value = "应收票据" ,index = 15)
    private String receivableBill;

    @ExcelProperty(value = "应收账款" ,index = 16)
    private String receivableMoney;

    @ExcelProperty(value = "预付款项" ,index = 17)
    private String advancePayableMoney;

    @ExcelProperty(value = "固定资产" ,index = 18)
    private String fixedAssets;

    @ExcelProperty(value = "在建工程" ,index = 19)
    private String reconstructionProject;

    @ExcelProperty(value = "工程物资" ,index = 20)
    private String engineeringMaterials;

    @ExcelProperty(value = "以公允价值计量且变动计入当期损益的金融资产" ,index = 21)
    private String fairValueProject;

    @ExcelProperty(value = "可供出售金融资产" ,index = 22)
    private String prepareSaleFinanceProject;

    @ExcelProperty(value = "持有至到期投资" ,index = 23)
    private String heldToMaturityInvestment;

    @ExcelProperty(value = "投资房地产" ,index = 24)
    private String investinInRealEstate;

    @ExcelProperty(value = "长期股权投资（与主业无关）" ,index = 25)
    private String longTermEquityInvestment;

    @ExcelProperty(value = "归属于母公司所有者权益合计" ,index = 26)
    private String belongMotherEquity;

    @ExcelProperty(value = "应付职工薪酬" ,index = 27)
    private String payableSalary;

    @ExcelProperty(value = "当期总股本" ,index = 28)
    private String totalEquity;

    @ExcelProperty(value = "备注" ,index = 29)
    private String remark;

}
