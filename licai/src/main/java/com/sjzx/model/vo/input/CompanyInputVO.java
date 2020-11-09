package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;

/**
 * @ClassName : CompanyInputVO
 * @Description : 公司查询入参对象
 * @Author : Horus
 * @Date: 2020-11-02 14:04
 */
@Data
public class CompanyInputVO extends BasePage {

    private Integer companyId;

    private Integer type;

}
