package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotCompany;
import com.sjzx.entity.HotCompanyData;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotCompanyMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotCompanyInputVO;
import com.sjzx.model.vo.output.HotCompanyCombogridVO;
import com.sjzx.model.vo.output.HotCompanyVO;
import com.sjzx.service.HotCompanyDataService;
import com.sjzx.service.HotCompanyService;
import com.sjzx.service.HotTypeService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 龙头战法热点公司 服务实现类
 * </p>
 *
 * @author
 * @since 2021-02-04
 */
@Service
public class HotCompanyServiceImpl extends ServiceImpl<HotCompanyMapper, HotCompany> implements HotCompanyService {

    @Autowired
    private HotTypeService hotTypeService;

    @Autowired
    private HotCompanyDataService hotCompanyDataService;

    @Override
    public EasyUIResult<HotCompanyVO> listPage(HotCompanyInputVO vo) {
        LambdaQueryWrapper<HotCompany> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(HotCompany::getCode, vo.getHotCompany()).or().like(HotCompany::getName, vo.getHotCompany())
                .orderByAsc(HotCompany::getCode);

        List<HotCompany> allList = list(wrapper);
        Map<String, HotType> map = hotTypeService.selectMap();
        if (vo.getHotTypeId() == null) {
            long total = allList.size();
            allList = allList.stream().skip((vo.getPageNo() - 1) * vo.getPageSize()).limit(vo.getPageSize())
                    .collect(Collectors.toList());
            return new EasyUIResult<>(total, BeanUtils.copyProperties(allList, HotCompanyVO::new, (s, t) -> copy(s, t, map)));
        } else {
            List<HotCompanyVO> recordList = new ArrayList<>();
            for (HotCompany hotCompany : allList) {
                if (Arrays.asList(hotCompany.getHotTypeIds().split(",")).contains(vo.getHotTypeId().toString())) {
                    HotCompanyVO target = BeanUtils.copyProperties(hotCompany, HotCompanyVO::new);
                    copy(hotCompany, target, map);
                    recordList.add(target);
                }
            }
            recordList.sort(Comparator.comparing(HotCompanyVO::getCirculationMarketValue));
            long total = recordList.size();
            recordList = recordList.stream().skip((vo.getPageNo() - 1) * vo.getPageSize()).limit(vo.getPageSize())
                    .collect(Collectors.toList());
            return new EasyUIResult<>(total, recordList);
        }
    }

    private int count(List<HotCompanyData> list, int count) {
        return (int)list.stream().filter(e -> e.getContinuityTime() == count).count();
    }

    private void copy(HotCompany s, HotCompanyVO t, Map<String, HotType> map) {
        List<HotCompanyData> dataList = hotCompanyDataService.selectByHotCompanyId(s.getId());
        t.setFirstTime(count(dataList, 1));
        t.setSecondTime(count(dataList, 2));
        t.setThirdTime(count(dataList, 3));
        t.setForthTime(count(dataList, 4));
        t.setFifthTime(count(dataList, 5));
        t.setSixthTime(count(dataList, 6));
        t.setSeventhTime(count(dataList, 7));

        // 查询公司最近的流通市值
        t.setCirculationMarketValue(dataList.isEmpty() ? BigDecimal.ZERO : dataList.get(0).getCirculationMarketValue());
        if (s.getHotTypeIds() == null) {
            return;
        }
        List<HotType> list = new ArrayList<>();
        for (String id : s.getHotTypeIds().split(",")) {
            if (map.containsKey(id)) {
                list.add(map.get(id));
            }
        }
        list.sort((a, b) -> b.getUpdateTime().compareTo(a.getUpdateTime()));
        if (list.size() > 0) {
            t.setHotType1(list.get(0).getName());
            if (list.size() > 1) {
                t.setHotType2(list.get(1).getName());
                if (list.size() > 2) {
                    t.setHotType3(list.get(2).getName());
                    if (list.size() > 3) {
                        t.setHotType4(list.get(3).getName());
                        if (list.size() > 4) {
                            t.setHotType5(list.get(4).getName());
                            if (list.size() > 5) {
                                t.setHotType6(list.get(5).getName());
                                if (list.size() > 6) {
                                    t.setHotType7(list.get(6).getName());
                                    if (list.size() > 7) {
                                        t.setHotType8(list.get(7).getName());
                                        if (list.size() > 8) {
                                            t.setHotType9(list.get(8).getName());
                                            if (list.size() > 9) {
                                                t.setHotType10(list.get(9).getName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addHotCompany(HotCompanyVO vo) {
        String hotTypeIds = vo.getHotType1();
        if (StringUtils.hasText(vo.getHotType2()) && !Arrays.asList(hotTypeIds.split(",")).contains(vo.getHotType2())) {
            hotTypeIds += "," + vo.getHotType2();
        }
        if (StringUtils.hasText(vo.getHotType3()) && !Arrays.asList(hotTypeIds.split(",")).contains(vo.getHotType3())) {
            hotTypeIds += "," + vo.getHotType3();
        }
        if (StringUtils.hasText(vo.getHotType4()) && !Arrays.asList(hotTypeIds.split(",")).contains(vo.getHotType4())) {
            hotTypeIds += "," + vo.getHotType4();
        }
        if (StringUtils.hasText(vo.getHotType5()) && !Arrays.asList(hotTypeIds.split(",")).contains(vo.getHotType5())) {
            hotTypeIds += "," + vo.getHotType5();
        }
        HotCompany hotCompany = BeanUtils.copyProperties(vo, HotCompany::new);
        hotCompany.setHotTypeIds(hotTypeIds).setCreateTime(new Date()).insert();
    }

    private String getHotTypeIds(String hotTypeIds, String hotType, Map<String, Integer> map) {
        if (StringUtils.hasText(hotType)) {
            if (NumberUtils.isNumeric(hotType) && !Arrays.asList(hotTypeIds.split(",")).contains(hotType)) {
                hotTypeIds += "," + hotType;
            } else {
                if (map.get(hotType) == null) {
                    throw new ServiceException(hotType + "-类型不存在");
                }
                hotTypeIds += "," + map.get(hotType).toString();
            }
        }
        return hotTypeIds;
    }

    @Override
    public void updateHotCompany(HotCompanyVO vo) {
        Map<String, Integer> map = hotTypeService.list().stream()
                .collect(Collectors.toMap(HotType::getName, HotType::getId));

        String hotTypeIds;
        if (NumberUtils.isNumeric(vo.getHotType1())) {
            hotTypeIds = vo.getHotType1();
        } else {
            if (map.get(vo.getHotType1()) == null) {
                throw new ServiceException(vo.getHotType1() + "-类型不存在");
            }
            hotTypeIds = map.get(vo.getHotType1()).toString();
        }
        hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType2(), map);
        hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType3(), map);
        hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType4(), map);
        hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType5(), map);

        HotCompany hotCompany = BeanUtils.copyProperties(vo, HotCompany::new);
        hotCompany.setHotTypeIds(hotTypeIds).setUpdateTime(new Date()).updateById();
    }


    @Override
    public void deleteHotCompany(HotCompanyVO vo) {
        removeById(vo.getId());
    }

    @Override
    public HotCompany selectByCode(String code) {
        LambdaQueryWrapper<HotCompany> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompany::getCode, code);
        return getOne(wrapper);
    }

    @Override
    public List<HotCompanyCombogridVO> combogrid(String q) {
        List<HotCompanyCombogridVO> list = baseMapper.combogridMax(q);
        if (!list.isEmpty()) {
            Map<String, HotType> map = hotTypeService.selectMap();
            for (HotCompanyCombogridVO combogridVO : list) {
                Set<String> names = new HashSet<>();
                for (String hotTypeId : combogridVO.getHotTypeIds().split(",")) {
                    if (map.containsKey(hotTypeId)) {
                        names.add(map.get(hotTypeId).getName());
                    }
                }
                combogridVO.setHotTypeName(String.join(",", names));
            }
        }
        return list;
    }

}
