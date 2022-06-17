package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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
        if (vo.getCategory() != null && vo.getCategory() == -1) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode, "60")
                    .or().likeRight(HotCompany::getCode,  "00"));
        }
        if (vo.getCategory() != null && vo.getCategory() == 1) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode,  "00"));
        }
        if (vo.getCategory() != null && vo.getCategory() == 2) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode, "60"));
        }
        if (vo.getCategory() != null && vo.getCategory() == 3) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode, "30"));
        }
        if (vo.getCategory() != null && vo.getCategory() == 4) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode, "688"));
        }
        if (vo.getCategory() != null && vo.getCategory() == 5) {
            wrapper.and(wrapper0 -> wrapper0.likeRight(HotCompany::getCode, "83"));
        }
        if (!StringUtils.isEmpty(vo.getHotCompany())) {
            wrapper.and(wrapper0 -> wrapper0.like(HotCompany::getCode, vo.getHotCompany()).or().like(HotCompany::getName, vo.getHotCompany()));
        }
        List<HotCompanyData> hotCompanyDataList = null;
        if (!StringUtils.isEmpty(vo.getDataDate()) || vo.getContinuityTime() != null) {
            hotCompanyDataList = hotCompanyDataService.selectByDataDateAndContinuityTime(vo.getDataDate(), vo.getContinuityTime());
            if (!hotCompanyDataList.isEmpty()) {
                wrapper.in(HotCompany::getId, hotCompanyDataList.stream().map(HotCompanyData::getHotCompanyId).collect(Collectors.toSet()));
            } else {
                wrapper.eq(HotCompany::getId, 0);
            }
        }
        wrapper.orderByAsc(HotCompany::getCode);

        List<HotCompany> allList = list(wrapper);
        Map<String, HotType> map = hotTypeService.selectMap();
        if (vo.getHotTypeId() == null) {
            if (hotCompanyDataList != null && !hotCompanyDataList.isEmpty() && !StringUtils.isEmpty(vo.getDataDate())) {
                Map<Integer, HotCompany> collect = allList.stream().collect(Collectors.toMap(HotCompany::getId, Function.identity()));
                List<HotCompanyVO> recordList = new ArrayList<>();
                for (HotCompanyData hotCompanyData : hotCompanyDataList) {
                    if (!collect.containsKey(hotCompanyData.getHotCompanyId())) {
                        continue;
                    }
                    recordList.add(getHotCompanyVO(collect.get(hotCompanyData.getHotCompanyId()), map));
                }
                return getEasyUIResult(recordList, vo);
            } else {
                long total = allList.size();
                allList = allList.stream().skip((vo.getPageNo() - 1) * vo.getPageSize()).limit(vo.getPageSize())
                        .collect(Collectors.toList());
                return new EasyUIResult<>(total, BeanUtils.copyProperties(allList, HotCompanyVO::new, (s, t) -> copy(s, t, map)));
            }
        } else {
            List<HotCompanyVO> recordList = new ArrayList<>();
            for (HotCompany hotCompany : allList) {
                if (Arrays.asList(hotCompany.getHotTypeIds().split(",")).contains(vo.getHotTypeId().toString())) {
                    recordList.add(getHotCompanyVO(hotCompany, map));
                }
            }
            recordList.sort(Comparator.comparing(HotCompanyVO::getCirculationMarketValue));
            return getEasyUIResult(recordList, vo);
        }
    }

    private EasyUIResult<HotCompanyVO> getEasyUIResult(List<HotCompanyVO> list, HotCompanyInputVO vo) {
        long total = list.size();
        list = list.stream().skip((vo.getPageNo() - 1) * vo.getPageSize()).limit(vo.getPageSize())
                .collect(Collectors.toList());
        return new EasyUIResult<>(total, list);
    }

    private HotCompanyVO getHotCompanyVO(HotCompany hotCompany, Map<String, HotType> map) {
        HotCompanyVO target = BeanUtils.copyProperties(hotCompany, HotCompanyVO::new);
        copy(hotCompany, target, map);
        return target;
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
        list.sort(hotTypeService.getComparator());
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
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType2());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType3());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType4());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType5());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType6());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType7());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType8());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType9());
        hotTypeIds = sumHotType(hotTypeIds, vo.getHotType10());

        HotCompany hotCompany = BeanUtils.copyProperties(vo, HotCompany::new);
        hotCompany.setHotTypeIds(hotTypeIds).setCreateTime(new Date()).insert();
    }

    private String sumHotType(String hotTypeIds, String hotType) {
        if (StringUtils.hasText(hotType) && !Arrays.asList(hotTypeIds.split(",")).contains(hotType)) {
            return hotTypeIds + "," + hotType;
        }
        return hotTypeIds;
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
