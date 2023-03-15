package com.sjzx.model.vo;

import lombok.Data;

/**
 * @ClassName : BasePage
 * @Description : 分页入参
 * @Author : Horus
 * @Date: 2020-11-02 14:03
 */
@Data
public class BasePage {

    //当前页
    private Integer pageNo = 1;
    //分页大小
    private Integer pageSize = 10;

}
