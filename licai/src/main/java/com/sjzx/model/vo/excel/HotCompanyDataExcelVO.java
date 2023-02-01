package com.sjzx.model.vo.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;


@Data
public class HotCompanyDataExcelVO extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "公司代码", index = 1)
    private String code;

    @ExcelProperty(value = "公司名称", index = 2)
    private String name;

    @ExcelProperty(value = "原因", index = 7)
    private String cause;

    @ExcelProperty(value = "最后涨停时间", index = 9)
    private Date fullTime;

    @ExcelProperty(value = "连板天数", index = 11)
    private String continuityTime;

}
