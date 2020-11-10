package com.sjzx.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : CompanyTypeEnum
 * @Description : 公司归属于哪一类行业枚举
 * @Author : Horus
 * @Date: 2020-11-02 14:22
 */
@Getter
@AllArgsConstructor
public enum StatisticsYearEnum {

    F(2020, "2020年"),
    E(2019, "2019年"),
    D(2018, "2018年"),
    C(2017, "2017年"),
    B(2016, "2016年"),
    A(2015, "2015年"),
    AA(2014, "2014年"),
    ;

    private int year;
    private String desc;

    public static List<Map<String, String>> getCombobox(String q) {
        List<Map<String, String>> list = new ArrayList<>();
        for (StatisticsYearEnum value : StatisticsYearEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("key", value.getYear() + "");
            map.put("value", value.getDesc());
            list.add(map);
        }
        return list;
    }
}
