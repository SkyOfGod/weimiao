package com.sjzx.model.vo.excel;



import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;


@Data
public class ProfitStatementExcelVO extends BaseRowModel implements Serializable{

    @ExcelProperty(index = 0)
    private String data0;

    @ExcelProperty(index = 1)
    private String data1;

    @ExcelProperty(index = 2)
    private String data2;

    @ExcelProperty(index = 3)
    private String data3;

    @ExcelProperty(index = 4)
    private String data4;

    @ExcelProperty(index = 5)
    private String data5;

    @ExcelProperty(index = 6)
    private String data6;
}
