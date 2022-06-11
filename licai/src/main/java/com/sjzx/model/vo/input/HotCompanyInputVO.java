package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotCompanyInputVO extends BasePage {

    /**
     * 热点ID
     */
    private Integer hotTypeId;

    /**
     * 公司
     */
    private String hotCompany;

    /**
     * 类别 0-全部  1-沪深
     */
    private Integer category;


}
