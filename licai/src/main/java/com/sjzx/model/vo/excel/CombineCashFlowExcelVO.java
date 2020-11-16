package com.sjzx.model.vo.excel;



import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;


@Data
public class CombineCashFlowExcelVO extends BaseRowModel implements Serializable{

    @ExcelProperty(value = "年份" ,index = 0)
    private Integer year;

    @ExcelProperty(value = "1-年报 2-第三季度报 3-半年报" ,index = 1)
    private String reportType;

    @ExcelProperty(value = "经营活动产生的现金流量净额" ,index = 2)
    private String businessToProfit;

    @ExcelProperty(value = "投资活动产生的现金流量净额" ,index = 3)
    private String investmentToProfit;

    @ExcelProperty(value = "筹资活动产生的现金流量净额" ,index = 4)
    private String financingToProfite;

    @ExcelProperty(value = "销售商品提供劳务收到的现金(含增值税)" ,index = 5)
    private String saleToCash;

    @ExcelProperty(value = "支付给职工以及为职工支付的现金" ,index = 6)
    private String salary;

    @ExcelProperty(value = "购建固定资产、无形资产和其他长期资产支付的现金" ,index = 7)
    private String investmentInsideOut;

    @ExcelProperty(value = "处置固定资产、无形资产和其他长期资产收回的现金净额" ,index = 8)
    private String investmentInsideIn;

    @ExcelProperty(value = "收购或投资子公司，对外扩大经营" ,index = 9)
    private String investmentOutsideSubOut;

    @ExcelProperty(value = "投资支付的现金" ,index = 10)
    private String investmentOutsideMoneyOut;

    @ExcelProperty(value = "现金及现金等价物净增加额" ,index = 11)
    private String cashAndCashEquivalentsAdd;

    @ExcelProperty(value = "期初现金及现金等价物余额" ,index = 12)
    private String cashAndCashEquivalentsBegin;

    @ExcelProperty(value = "期末现金及现金等价物余额" ,index = 13)
    private String cashAndCashEquivalentsEnd;

    @ExcelProperty(value = "分配股利、利润或偿付利息支付的现金" ,index = 14)
    private String bonusCash;

    @ExcelProperty(value = "备注" ,index = 15)
    private String remark;

}
