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
public enum CompanyTypeEnum {

    DEFAULT(0, "默认"),
    CAR_PRODUCT(1, "汽车整车"),
    WHITE_ELECTRICAL(2, "白色家电"),
    MEDICAL_CARE(3, "医疗"),
    SEMICONDUCTOR(4, "半导体及元件"),
    BEVERAGE_MANUFACTURING(5, "饮料制造"),
    BANK(6, "银行"),
    AVIATION(7, "航空"),
    LOGISTICS(8, "仓储物流"),
    TEXTILE(9, "纺织制造"),
    BUILDING_MATERIAL(10, "建筑材料"),
    NEGOTIABLE_SECURITIES(11, "证券公司"),
    ;

    private int type;
    private String desc;

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (CompanyTypeEnum value : CompanyTypeEnum.values()) {
            map.put(value.getType(), value.getDesc());
        }
        return map;
    }

    public static List<Map<String, String>> getCombobox(String q) {
        List<Map<String, String>> list = new ArrayList<>();
        for (CompanyTypeEnum value : CompanyTypeEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("key", value.getType() + "");
            map.put("value", value.getDesc());
            list.add(map);
        }
        return list;
    }
}
