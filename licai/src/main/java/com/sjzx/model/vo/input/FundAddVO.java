package com.sjzx.model.vo.input;

import com.sjzx.entity.Fund;
import lombok.Data;

@Data
public class FundAddVO extends Fund {

    /**
     * 基金目前价格
     */
    private String price;


}
