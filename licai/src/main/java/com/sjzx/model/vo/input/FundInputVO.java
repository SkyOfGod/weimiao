package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FundInputVO extends BasePage {

    /**
     * 代码或名称
     */
    private String name;



}
