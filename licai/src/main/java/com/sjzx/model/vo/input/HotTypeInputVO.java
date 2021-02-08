package com.sjzx.model.vo.input;

import com.sjzx.model.vo.BasePage;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 热点类型
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
@Data
@Accessors(chain = true)
public class HotTypeInputVO extends BasePage {

    /**
     * 热点名称
     */
    private String name;



}
