package com.sjzx.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : CompanyReportTypeEnum
 * @Description : 参考标准 1-年报 2-第三季度报 3-半年报 4-第一季度报
 * @Author : Horus
 * @Date: 2020-11-02 14:22
 */
@Getter
@AllArgsConstructor
public enum CompanyReportTypeEnum {

    A(1, "年报"),
    B(2, "第三季度报"),
    C(3, "半年报"),
    D(4, "第一季度报"),
    ;

    private int type;
    private String desc;

    public static Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        for (CompanyReportTypeEnum value : CompanyReportTypeEnum.values()) {
            map.put(String.valueOf(value.getType()), value.getDesc());
        }
        return map;
    }

}
