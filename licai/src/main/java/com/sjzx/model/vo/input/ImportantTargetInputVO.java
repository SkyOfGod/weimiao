package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;

/**
 * @Description : 重要指标表统计数据查询入参对象
 * @Author : Horus
 * @Date: 2020-11-27 14:04
 */
@Data
public class ImportantTargetInputVO extends BasePage {

    private Integer companyId;

    private Integer reportType;

    private Integer type;

    private Integer year;

}
