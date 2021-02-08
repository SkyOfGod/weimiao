package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotCompanyInputVO extends BasePage {

    /**
     * 名称
     */
    private String name;



}
