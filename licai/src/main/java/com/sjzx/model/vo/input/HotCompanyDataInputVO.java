package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotCompanyDataInputVO extends BasePage {

    /**
     * 复盘日期
     */
    private String dataDate;

    /**
     * 热点ID
     */
    private Integer hotTypeId;

    /**
     * 公司ID
     */
    private Integer hotCompanyId;

    /**
     * 连扳次数
     */
    private Integer continuityTime;

    /**
     * 复盘上一个交易日日期
     */
    private String yesterdayDataDate;

    /**
     * 0-未选中  1-选中
     */
    private Integer onSelected;

}
