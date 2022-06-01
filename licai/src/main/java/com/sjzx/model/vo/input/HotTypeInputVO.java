package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotTypeInputVO extends BasePage {

    /**
     * 热点名称
     */
    private String name;

    /**
     *
     */
    private String dataDate;


}
