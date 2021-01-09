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
public enum CompanyCategoryEnum {

    GUPIAO(1, "股票"),
    JIJIN(2, "基金"),
    REITS(3, "Reits"),
    KEZHUANZHAI(4, "可转债"),
    NIHUIGOU(5, "逆回购"),
    ;

    private int category;
    private String desc;

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (CompanyCategoryEnum value : CompanyCategoryEnum.values()) {
            map.put(value.getCategory(), value.getDesc());
        }
        return map;
    }

    public static Map<String, String> toStrMap() {
        Map<String, String> map = new HashMap<>();
        for (CompanyCategoryEnum value : CompanyCategoryEnum.values()) {
            map.put(value.getCategory() + "", value.getDesc());
        }
        return map;
    }

}
