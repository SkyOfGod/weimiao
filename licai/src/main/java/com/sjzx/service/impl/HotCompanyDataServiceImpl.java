package com.sjzx.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotCompany;
import com.sjzx.entity.HotCompanyData;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotCompanyDataMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.excel.CombineCashFlowExcelVO;
import com.sjzx.model.vo.excel.HotCompanyDataExcelVO;
import com.sjzx.model.vo.input.HotCompanyDataAddVO;
import com.sjzx.model.vo.input.HotCompanyDataInputVO;
import com.sjzx.model.vo.input.HotCompanyInputVO;
import com.sjzx.model.vo.output.HotCompanyDataVO;
import com.sjzx.service.HotCompanyDataService;
import com.sjzx.service.HotCompanyService;
import com.sjzx.service.HotTypeService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.EasyExcelUtils;
import com.sjzx.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 热点公司复盘表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-21
 */
@Service
public class HotCompanyDataServiceImpl extends ServiceImpl<HotCompanyDataMapper, HotCompanyData> implements HotCompanyDataService {

    private final static BigDecimal PERCENT = new BigDecimal("0.7");
    // 下一个交易日开局第一分钟爆量最少比例
    private final static BigDecimal ONE_MINUTE_VALUE_PERCENT = new BigDecimal("0.1");

    @Autowired
    private HotTypeService hotTypeService;

    @Autowired
    private HotCompanyService hotCompanyService;

    @Override
    public EasyUIResult<HotCompanyDataVO> listPage(HotCompanyDataInputVO vo) {
        List<String> dataList = baseMapper.selectAllDataDate(vo.getDataDate());
        if (dataList.size() > 1) {
            vo.setYesterdayDataDate(dataList.get(1));
        }
        IPage<HotCompanyDataVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);

        Map<String, HotType> map = hotTypeService.selectMap();
        for (HotCompanyDataVO hotCompanyDataVO : iPage.getRecords()) {
            hotCompanyDataVO.setHotType(map.get(hotCompanyDataVO.getHotTypeId()).getName());

            BigDecimal maxChange = new BigDecimal(hotCompanyDataVO.getMaxChange());
            hotCompanyDataVO.setMaxChange(maxChange.toString());

            BigDecimal safeChange = maxChange.multiply(PERCENT).setScale(2, BigDecimal.ROUND_HALF_UP);
            hotCompanyDataVO.setSafeChange(safeChange);
            hotCompanyDataVO.setSafeChangeMarketValue(safeChange.multiply(hotCompanyDataVO.getCirculationMarketValue()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP));

            if (hotCompanyDataVO.getNearChange().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal nearValue = hotCompanyDataVO.getCirculationMarketValue().multiply(hotCompanyDataVO.getNearChange())
                        .divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);

                hotCompanyDataVO.setOneMinuteValuePercent(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("100"))
                        .divide(nearValue, 2, BigDecimal.ROUND_HALF_UP));
                hotCompanyDataVO.setTomorrowOneMinuteValue(nearValue.multiply(ONE_MINUTE_VALUE_PERCENT));
            }
            if (hotCompanyDataVO.getTodayNoDeal().compareTo(BigDecimal.ZERO) == 0) {
                hotCompanyDataVO.setTodayNoDeal(hotCompanyDataVO.getYesterdayNoDeal());
            }
            if (hotCompanyDataVO.getTodayNoDeal() != null) {
                hotCompanyDataVO.setTodayNoDealPercent(hotCompanyDataVO.getTodayNoDeal().multiply(new BigDecimal("100"))
                        .divide(hotCompanyDataVO.getCirculationMarketValue(), 2, BigDecimal.ROUND_HALF_UP));
            }
            // 3个亿压力换完30%，5个亿压力换完50%，10亿压力换70%，有进攻盘
            if (hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.ZERO) > 0 && hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(3)) < 0) {
                hotCompanyDataVO.setSafeValue(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("0.3")));
            } else if (hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(3)) >= 0 && hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(4)) < 0) {
                hotCompanyDataVO.setSafeValue(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("0.4")));
            } else if (hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(4)) >= 0 && hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(5)) < 0) {
                hotCompanyDataVO.setSafeValue(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("0.5")));
            } else if (hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(5)) >= 0 && hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(7.5)) < 0) {
                hotCompanyDataVO.setSafeValue(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("0.6")));
            } else if (hotCompanyDataVO.getOneMinuteValue().compareTo(BigDecimal.valueOf(7.5)) >= 0) {
                hotCompanyDataVO.setSafeValue(hotCompanyDataVO.getOneMinuteValue().multiply(new BigDecimal("0.7")));
            }

            if (StringUtils.isEmpty(hotCompanyDataVO.getHotTypeIds())) {
                continue;
            }
            List<HotType> list = new ArrayList<>();
            for (String id : hotCompanyDataVO.getHotTypeIds().split(",")) {
                if (map.containsKey(id) && !hotCompanyDataVO.getHotTypeId().equals(id)) {
                    list.add(map.get(id));
                }
            }
            list.sort(hotTypeService.getComparator());
            hotCompanyDataVO.setHotTypeName(String.join(",", list.stream().map(HotType::getName).collect(Collectors.toSet())));
        }

        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    @Transactional
    public String addHotCompanyData(HotCompanyDataAddVO vo) {
        handleFullTime(vo);
        Integer hotTypeId = handleHotTypeId(vo);
        String dataDate = vo.getDataDate();
        HotCompany hotCompany = hotCompanyService.selectByCode(vo.getCode());
        if (hotCompany == null) {
            hotCompany = new HotCompany().setCode(vo.getCode()).setName(vo.getName())
                    .setHotTypeIds(vo.getHotTypeId()).setContinuityTime(vo.getContinuityTime())
                    .setHotTypeId(hotTypeId).setMaxChange(vo.getMaxChange())
                    .setCirculationMarketValue(vo.getCirculationMarketValue());
            if (vo.getContinuityTime() == 1) {
                hotCompany.setFirstDate(dataDate);
            }
            hotCompany.setCreateTime(new Date()).insert();
        } else {
            hotCompany.setContinuityTime(vo.getContinuityTime()).setName(vo.getName())
                    .setHotTypeId(hotTypeId);
            if (hotCompany.getMaxChange() == null || (vo.getMaxChange() != null && vo.getMaxChange().compareTo(hotCompany.getMaxChange()) > 0)) {
                hotCompany.setMaxChange(vo.getMaxChange());
            }
            hotCompany.setCirculationMarketValue(vo.getCirculationMarketValue());
            if (vo.getContinuityTime() == 1) {
                hotCompany.setFirstDate(dataDate);
            }
            hotCompany.setHotTypeIds(handleHotTypeIds(hotCompany.getHotTypeIds(), vo.getHotTypeId()));
            hotCompany.setUpdateTime(new Date()).updateById();
        }
        HotCompanyData hotCompanyData = BeanUtils.copyProperties(vo, HotCompanyData::new);
        hotCompanyData.setHotTypeId(hotTypeId).setHotCompanyId(hotCompany.getId())
                .setCreateTime(new Date());
//        HotCompanyData old = select(hotCompanyData.getHotCompanyId(), hotCompanyData.getDataDate());
//        if (old == null) {
            hotCompanyData.insert();
//        } else {
//            hotCompanyData.setId(old.getId()).setUpdateTime(new Date()).updateById();
//        }

        new HotType().setId(hotTypeId).setUpdateTime(new Date()).updateById();

        updateHotCompareDataSort(dataDate, vo.getHotTypeId());
        return dataDate;
    }

    private String handleHotTypeIds(String hotTypeIds, String hotTypeId) {
        Set<String> set = new HashSet<>();
        if (hotTypeId != null) {
            set.add(hotTypeId);
        }
        Collections.addAll(set, hotTypeIds.split(","));
        List<HotType> list = hotTypeService.getByIds(set);
        return String.join(",", list.stream().map(e -> e.getId().toString()).collect(Collectors.toSet()));
    }

    private HotCompanyData select(Integer hotCompanyId, String dataDate) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getHotCompanyId, hotCompanyId)
                .eq(HotCompanyData::getDataDate, dataDate);
        return getOne(wrapper);
    }

    public List<HotCompanyData> select(Integer hotTypeId, List<LocalDate> dataDateList) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getHotTypeId, hotTypeId)
                .in(HotCompanyData::getDataDate, dataDateList);
        return list(wrapper);
    }

    private Integer handleHotTypeId(HotCompanyDataAddVO vo) {
        HotType hotType;
        try {
            Integer.parseInt(vo.getHotTypeId());
            hotType = hotTypeService.getById(vo.getHotTypeId());
        } catch (NumberFormatException e) {
            hotType = hotTypeService.getByName(vo.getHotTypeId());
        }
        if (hotType == null) {
            hotType = new HotType();
            hotType.setName(vo.getHotTypeId()).setCreateTime(new Date()).setUpdateTime(new Date()).insert();
            if (hotType.getSort() == null || hotType.getSort() == 0) {
                hotType.setSort(vo.getId()).updateById();
            }
        }
        vo.setHotTypeId(hotType.getId() + "");
        return hotType.getId();
    }

    @Override
    public void updateHotCompareDataSort(String dataDate, Object hotTypeId) {
        // 查询
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getHotTypeId, hotTypeId)
                .eq(HotCompanyData::getDataDate, dataDate);
        List<HotCompanyData> list = list(wrapper);
        if (list.isEmpty()) {
            return;
        }
        LambdaQueryWrapper<HotCompanyData> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.in(HotCompanyData::getId, list.stream().map(HotCompanyData::getId).collect(Collectors.toSet()));

        update(new HotCompanyData().setSort(list.size()), wrapper);
    }

    private void handleFullTime(HotCompanyDataAddVO vo) {
        if (StringUtils.isEmpty(vo.getFullTime()) || vo.getFullTime().length() == 8) {
            return;
        }
        String[] split = vo.getFullTime().split(":");
        StringBuilder sb = new StringBuilder();
        if (split[0].length() == 1) {
            sb.append("0");
        }
        sb.append(split[0]).append(":")
                .append(split[1]).append(":");
        if (split.length > 2) {
            sb.append(split[2]);
        } else {
            sb.append("00");
        }
        vo.setFullTime(sb.toString());
    }

    @Override
    public void updateHotCompanyData(HotCompanyDataAddVO vo) {
        handleFullTime(vo);
        HotCompanyData old = getById(vo.getId());
        int hotTypeId = handleHotTypeId(vo);

        HotCompanyData hotCompanyData = BeanUtils.copyProperties(vo, HotCompanyData::new);
        hotCompanyData.setHotTypeId(hotTypeId).setUpdateTime(new Date()).updateById();

        if (!old.getHotTypeId().equals(hotTypeId)) {
            String dataDate = vo.getDataDate();
            updateHotCompareDataSort(dataDate, old.getHotTypeId());
            updateHotCompareDataSort(dataDate, hotTypeId);
        }

    }

    @Override
    public void deleteHotCompanyData(HotCompanyDataAddVO vo) {
        removeById(vo.getId());
    }

    @Override
    public List<Map<String, String>> getDataDateCombobox(String q) {
        List<String> dataList = baseMapper.selectAllDataDate(q);

        List<Map<String, String>> list = new ArrayList<>();
        int i = 0;
        for (String dataDate : dataList) {
            i++;
            Map<String, String> map = new HashMap<>();
            map.put("sort", i + "");
            map.put("key", dataDate);
            LocalDate localDate = NumberUtils.toLocalDate(dataDate);
            map.put("value", "周" + localDate.getDayOfWeek().getValue());
            list.add(map);
        }
        return list;
    }

    @Override
    public int selectCountByDataDate(LocalDate date) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getDataDate, date);
        return count(wrapper);
    }

    @Override
    public LocalDate selectLtDataDate(LocalDate date) {
        return baseMapper.selectLtDataDate(date);
    }

    @Override
    public BigDecimal selectRecentCirculationMarketValueByHotCompanyId(Integer hotCompanyId) {
        return baseMapper.selectRecentCirculationMarketValueByHotCompanyId(hotCompanyId);
    }

    @Override
    public List<HotCompanyData> selectByHotCompanyId(Integer hotCompanyId) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getHotCompanyId, hotCompanyId)
                .orderByDesc(HotCompanyData::getDataDate);
        return list(wrapper);
    }

    @Override
    public HotCompanyData selectMaxIdData() {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(HotCompanyData::getId);

        IPage<HotCompanyData> iPage = new Page<>(1, 1);
        page(iPage, wrapper);
        if (iPage.getTotal() > 0) {
            return iPage.getRecords().get(0);
        }
        return null;
    }

    @Override
    public List<HotCompanyData> select(HotCompanyInputVO vo) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(vo.getDataDate()), HotCompanyData::getDataDate, vo.getDataDate())
            .eq(vo.getOnSelected() != null, HotCompanyData::getOnSelected, vo.getOnSelected());
        Integer continuityTime = vo.getContinuityTime();
        if (continuityTime != null && continuityTime > 0) {
            wrapper.eq(HotCompanyData::getContinuityTime, continuityTime);
        }
        if (continuityTime != null && continuityTime < 0 && continuityTime > -20) {
            wrapper.gt(HotCompanyData::getContinuityTime, -continuityTime);
        }
        if (continuityTime != null && continuityTime == -20) {
            wrapper.eq(HotCompanyData::getFullTime, "09:30:00");
        }
        wrapper.orderByDesc(HotCompanyData::getDataDate)
                .orderByDesc(HotCompanyData::getSort)
                .orderByDesc(HotCompanyData::getHotTypeId)
                .orderByAsc(HotCompanyData::getFullTime)
                .orderByDesc(HotCompanyData::getContinuityTime)
                .orderByAsc(HotCompanyData::getId);
        return list(wrapper);
    }

    @Override
    public List<Map<String, String>> dataDateNewCombogrid(String q) {
        LocalDate now = LocalDate.now();
        List<Map<String, String>> list = new ArrayList<>();
        int total = 1;
        for (int i = 0; i < 1000; i++) {
            LocalDate localDate = now.minusDays(i);
            int value = localDate.getDayOfWeek().getValue();
            if (Arrays.asList(6, 7).contains(value)) {
                continue;
            }
            Map<String, String> map = new HashMap<>();
            map.put("sort", total + "");
            map.put("key", localDate.toString());
            map.put("value", "周" + value);
            list.add(map);
            total++;
        }
        return list;
    }

    @Override
    public int delete(String dataDate) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getDataDate, dataDate);
        int total = count(wrapper);
        remove(wrapper);
        return total;
    }

    @Override
    public int uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) throws Exception {
        String dataDate = request.getParameter("dataDate");
        List<HotCompanyDataExcelVO> list = EasyExcelUtils.readExcelWithModel(file.getInputStream(), HotCompanyDataExcelVO.class, typeEnum);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        int total = 0;
        for (HotCompanyDataExcelVO excelVO : list) {
            if (!StringUtils.hasText(excelVO.getCode())) {
                continue;
            }
            try {
                if (!StringUtils.hasText(excelVO.getName())) {
                    excelVO.setName("");
                }
                String cause = excelVO.getCause();
                if (cause == null) {
                    cause = "";
                }
                String[] split = cause.split(",");// 兼容历史数据
                HotCompanyDataAddVO dataAddVO = HotCompanyDataAddVO.builder()
                        .code(excelVO.getCode()).name(excelVO.getName())
                        .hotTypeId(split[split.length - 1])
                        .fullTime(sdf.format(excelVO.getFullTime()))
                        .continuityTime(Integer.parseInt(excelVO.getContinuityTime())).dataDate(dataDate)
                        .build();
                addHotCompanyData(dataAddVO);
                total++;
            } catch (Exception e) {
                log.error("导入数据新增失败", e);
            }
        }
        return total;
    }

}
