package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;

@Data
public class SProfitStatementInputVO extends BasePage {
    private Integer companyId;

    private Integer year;

    private Integer pageIndex = 0;
}
