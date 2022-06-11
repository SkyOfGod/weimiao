package com.sjzx.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : CompanyTypeEnum
 * @Description : 公司归属于哪一类行业枚举
 * @Author : Horus
 * @Date: 2020-11-02 14:22
 */
@Getter
@AllArgsConstructor
public enum CompanyLocationEnum {

    SHANGHAI(10, "沪主板", "60", "一般大型国企"),
    SHANGHAI1(11, "沪科创板", "688", "科技创新类企业，50万得资金门槛才能开通权"),
    SHENZHEN(20, "深主板", "000,001", ""),
    SHENZHEN1(21, "深中小板", "002,003,004", "中小民营企业"),
    SHENZHEN2(22, "深创业板", "300,301", "相对于中小板，注重企业得创新能力以及成长性"),
    GANG(30, "港", "", ""),
    MEI(40, "美", "", ""),
    ;

    private int location;
    private String name;
    private String prefix;
    private String desc;

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (CompanyLocationEnum value : CompanyLocationEnum.values()) {
            map.put(value.getLocation(), value.getName());
        }
        return map;
    }

    public static Map<String, String> toStrMap() {
        Map<String, String> map = new HashMap<>();
        for (CompanyLocationEnum value : CompanyLocationEnum.values()) {
            map.put(value.getLocation() + "", value.getName());
        }
        return map;
    }

}
