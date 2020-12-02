package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.CompanyType;
import com.sjzx.mapper.CompanyTypeMapper;
import com.sjzx.service.CompanyTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司行业类型表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
@Service
public class CompanyTypeServiceImpl extends ServiceImpl<CompanyTypeMapper, CompanyType> implements CompanyTypeService {

    @Override
    public Map<Integer, String> toMap() {
        List<CompanyType> list = list();
        Map<Integer, String> map = new HashMap<>();
        for (CompanyType companyType : list) {
            map.put(companyType.getId(), companyType.getName());
        }
        return map;
    }

    @Override
    public List<Map<String, String>> getCombobox(String q) {
        LambdaQueryWrapper<CompanyType> wrapper = new LambdaQueryWrapper<>();
        if(StringUtils.hasText(q)) {
            wrapper.eq(CompanyType::getId, q).or().like(CompanyType::getName, q);
        }
        wrapper.orderByDesc(CompanyType::getSort).orderByAsc(CompanyType::getId);
        IPage<CompanyType> iPage = new Page<>(1, 20);

        List<Map<String, String>> list = new ArrayList<>();
        for (CompanyType companyType : page(iPage, wrapper).getRecords()) {
            Map<String, String> map = new HashMap<>();
            map.put("key", companyType.getId() + "");
            map.put("value", companyType.getName());
            list.add(map);
        }
        return list;
    }
}
