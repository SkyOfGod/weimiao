package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FundCompanyInputVO extends BasePage {

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 基金ID
     */
    private String fundId;

    /**
     *
     */
    private String companyCountId;



}
