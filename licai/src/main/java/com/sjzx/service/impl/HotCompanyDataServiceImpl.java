package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotCompany;
import com.sjzx.entity.HotCompanyData;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotCompanyDataMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotCompanyDataAddVO;
import com.sjzx.model.vo.input.HotCompanyDataInputVO;
import com.sjzx.model.vo.output.HotCompanyDataVO;
import com.sjzx.service.HotCompanyDataService;
import com.sjzx.service.HotCompanyService;
import com.sjzx.service.HotTypeService;
import com.sjzx.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

    private final static BigDecimal PERCENT = new BigDecimal("1");

    @Autowired
    private HotTypeService hotTypeService;

    @Autowired
    private HotCompanyService hotCompanyService;

    @Override
    public EasyUIResult<HotCompanyDataVO> listPage(HotCompanyDataInputVO vo) {
        IPage<HotCompanyDataVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);

        Map<String, HotType> map = hotTypeService.selectMap();
        for (HotCompanyDataVO hotCompanyDataVO : iPage.getRecords()) {
            hotCompanyDataVO.setHotType(map.get(hotCompanyDataVO.getHotTypeId()).getName());

            BigDecimal maxChange = new BigDecimal(hotCompanyDataVO.getMaxChange());
            hotCompanyDataVO.setMaxChange(maxChange.toString());

            BigDecimal safeChange = maxChange.multiply(PERCENT).setScale(2, BigDecimal.ROUND_HALF_UP);
            hotCompanyDataVO.setSafeChange(safeChange + "%");
            hotCompanyDataVO.setSafeChangeMarketValue(safeChange.multiply(hotCompanyDataVO.getCirculationMarketValue()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP));
            hotCompanyDataVO.setNoDealPercent(hotCompanyDataVO.getNoDeal().multiply(new BigDecimal("100")).divide(hotCompanyDataVO.getCirculationMarketValue(), 2, BigDecimal.ROUND_HALF_UP));

            if (StringUtils.isEmpty(hotCompanyDataVO.getHotTypeIds())) {
                continue;
            }
            List<HotType> list = new ArrayList<>();
            for (String id : hotCompanyDataVO.getHotTypeIds().split(",")) {
                if (map.containsKey(id) && !hotCompanyDataVO.getHotTypeId().equals(id)) {
                    list.add(map.get(id));
                }
            }
            list.sort((a, b) -> Integer.compare(b.getSort(), a.getSort()));
            if (list.size() > 0) {
                hotCompanyDataVO.setHotType1(list.get(0).getName());
                if (list.size() > 1) {
                    hotCompanyDataVO.setHotType2(list.get(1).getName());
                    if (list.size() > 2) {
                        hotCompanyDataVO.setHotType3(list.get(2).getName());
                        if (list.size() > 3) {
                            hotCompanyDataVO.setHotType4(list.get(3).getName());
                            if (list.size() > 4) {
                                hotCompanyDataVO.setHotType5(list.get(4).getName());
                            }
                        }
                    }
                }
            }
        }

        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    @Transactional
    public void addHotCompanyData(HotCompanyDataAddVO vo) {
        String dataDate = vo.getFullTime().substring(0, 10);
        HotCompany hotCompany = hotCompanyService.selectByCode(vo.getCode());
        if (hotCompany == null) {
            if (StringUtils.isEmpty(vo.getName())) {
                throw new ServiceException("缺少公司名称");
            }
            hotCompany = new HotCompany().setCode(vo.getCode()).setName(vo.getName())
                    .setHotTypeIds(vo.getHotTypeId() + "").setContinuityTime(vo.getContinuityTime())
                    .setHotTypeId(vo.getHotTypeId()).setMaxChange(vo.getMaxChange())
                    .setCirculationMarketValue(vo.getCirculationMarketValue());
            if (vo.getContinuityTime() == 1) {
                hotCompany.setFirstTime(dataDate);
            }
            hotCompany.setCreateTime(new Date()).insert();
        } else {
            hotCompany.setContinuityTime(vo.getContinuityTime())
                    .setHotTypeId(vo.getHotTypeId());
            if (hotCompany.getMaxChange() == null || (vo.getMaxChange() != null && vo.getMaxChange().compareTo(hotCompany.getMaxChange()) > 0)) {
                hotCompany.setMaxChange(vo.getMaxChange());
            }
            hotCompany.setCirculationMarketValue(vo.getCirculationMarketValue());
            if (vo.getContinuityTime() == 1) {
                hotCompany.setFirstTime(dataDate);
            }
            if (!Arrays.asList(hotCompany.getHotTypeIds().split(",")).contains(vo.getHotTypeId() + "")) {
                hotCompany.setHotTypeIds(hotCompany.getHotTypeIds() + "," + vo.getHotTypeId());
            }
            hotCompany.setUpdateTime(new Date()).updateById();
        }
        HotCompanyData hotCompanyData = BeanUtils.copyProperties(vo, HotCompanyData::new);
        hotCompanyData.setHotCompanyId(hotCompany.getId()).setDataDate(dataDate)
                .setCreateTime(new Date()).insert();

        //new HotType().setId(vo.getHotTypeId()).setUpdateTime(new Date()).updateById();
    }

    @Override
    public void updateHotCompanyData(HotCompanyDataAddVO vo) {
        new HotCompanyData().setId(vo.getId())
                .setMaxChange(vo.getMaxChange()).setCirculationMarketValue(vo.getCirculationMarketValue())
                .setContinuityTime(vo.getContinuityTime()).setFullTime(vo.getFullTime())
                .setDataDate(vo.getFullTime().substring(0, 10)).setSort(vo.getSort())
                .setRemark(vo.getRemark()).setUpdateTime(new Date())
                .setNoDeal(vo.getNoDeal()).updateById();
    }

    @Override
    public void deleteHotCompanyData(HotCompanyDataAddVO vo) {
        removeById(vo.getId());
    }

    @Override
    public List<HotCompanyData> select(Integer hotTypeId, List<LocalDate> dataDateList) {
        LambdaQueryWrapper<HotCompanyData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotCompanyData::getHotTypeId, hotTypeId)
                .in(HotCompanyData::getDataDate, dataDateList);
        return list(wrapper);
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
            LocalDate localDate = LocalDate.of(Integer.parseInt(dataDate.substring(0, 4)),
                    Integer.parseInt(dataDate.substring(5, 7)), Integer.parseInt(dataDate.substring(8, 10)));
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

}
