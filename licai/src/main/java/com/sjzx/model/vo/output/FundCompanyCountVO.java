package com.sjzx.model.vo.output;

import lombok.Data;

@Data
public class FundCompanyCountVO {

    private Integer id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    private Integer count;

}
