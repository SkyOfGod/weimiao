package com.sjzx.model.vo.excel;



import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CombineProfitExcelVO extends BaseRowModel implements Serializable{

    @ExcelProperty(value = "年份" ,index = 0)
    private Integer year;

    @ExcelProperty(value = "1-年报 2-第三季度报 3-半年报" ,index = 1)
    private String reportType;

    @ExcelProperty(value = "营业收入" ,index = 2)
    private String businessIncome;

    @ExcelProperty(value = "营业成本" ,index = 3)
    private String businessCosts;

    @ExcelProperty(value = "税金及附加" ,index = 4)
    private String taxRevenueTotal;

    @ExcelProperty(value = "销售费用" ,index = 5)
    private String sellingExpenses;

    @ExcelProperty(value = "管理费用" ,index = 6)
    private String manageExpenses;

    @ExcelProperty(value = "财务费用" ,index = 7)
    private String financialExpenses;

    @ExcelProperty(value = "资产减值损失" ,index = 8)
    private String assetsImpairmentLoss;

    @ExcelProperty(value = "公允价值变动收益（损失以“－”号填列）" ,index = 9)
    private String incomeFromChangesInFairValue;

    @ExcelProperty(value = "投资收益（损失以“－”号填列）" ,index = 10)
    private String incomeFromInvestment;

    @ExcelProperty(value = "营业利润（主业和投资）" ,index = 11)
    private String businessProfit;

    @ExcelProperty(value = "营业外收入" ,index = 12)
    private String nonBusinessIncome;

    @ExcelProperty(value = "营业外支出" ,index = 13)
    private String nonBusinessCosts;

    @ExcelProperty(value = "利润总额" ,index = 14)
    private String totalProfit;

    @ExcelProperty(value = "所得税费用" ,index = 15)
    private String incomeTaxExpense;

    @ExcelProperty(value = "净利润" ,index = 16)
    private String netProfit;

    @ExcelProperty(value = "归属于母公司所有者的净利润" ,index = 17)
    private String belongMotherNetProfit;

    @ExcelProperty(value = "备注" ,index = 18)
    private String remark;

}
