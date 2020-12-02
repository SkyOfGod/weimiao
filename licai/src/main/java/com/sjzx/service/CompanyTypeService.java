package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.CompanyType;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司行业类型表 服务类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
public interface CompanyTypeService extends IService<CompanyType> {

    Map<Integer, String> toMap();

    List<Map<String, String>> getCombobox(String q);

}
