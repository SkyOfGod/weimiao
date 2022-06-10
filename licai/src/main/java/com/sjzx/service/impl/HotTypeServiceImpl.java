package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotCompanyData;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotTypeMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotTypeInputVO;
import com.sjzx.model.vo.output.HotTypeVO;
import com.sjzx.service.HotCompanyDataService;
import com.sjzx.service.HotTypeService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 热点类型 服务实现类
 * </p>
 *
 * @author
 * @since 2021-02-04
 */
@Service
public class HotTypeServiceImpl extends ServiceImpl<HotTypeMapper, HotType> implements HotTypeService {

    @Autowired
    private HotCompanyDataService hotCompanyDataService;

    @Override
    public EasyUIResult<HotTypeVO> listPage(HotTypeInputVO vo) {
        LocalDateTime now = LocalDateTime.now();
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate date;
        if (StringUtils.isEmpty(vo.getDataDate())) {
            date = now.toLocalDate();
        } else {
            date = NumberUtils.toLocalDate(vo.getDataDate());
            // 更新查询日期所在热点更新时间
            baseMapper.updateUpdateTimeByDataDate(vo.getDataDate());
        }
        while (dateList.size() < 20) {
            LocalDate selectDate = hotCompanyDataService.selectLtDataDate(date);
            if (selectDate != null && selectDate.isBefore(date)) {
                date = selectDate;
            }
            dateList.add(date);
            date = date.minusDays(1);
        }

        LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(vo.getName())) {
            wrapper.like(HotType::getName, vo.getName());
        }
        wrapper.orderByDesc(HotType::getUpdateTime).orderByDesc(HotType::getSort);
        IPage<HotType> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        page(iPage, wrapper);

        return new EasyUIResult<>(iPage.getTotal(), BeanUtils.copyProperties(iPage.getRecords(), HotTypeVO::new,
                (s, t) -> {
                    t.setDataDate(dateList);
                    List<HotCompanyData> list = hotCompanyDataService.select(s.getId(), dateList);
                    Map<String, List<HotCompanyData>> map = list.stream().collect(Collectors.groupingBy(HotCompanyData::getDataDate));
                    t.setCount1(count(map, dateList.get(0)));
                    t.setCount2(count(map, dateList.get(1)));
                    t.setCount3(count(map, dateList.get(2)));
                    t.setCount4(count(map, dateList.get(3)));
                    t.setCount5(count(map, dateList.get(4)));
                    t.setCount6(count(map, dateList.get(5)));
                    t.setCount7(count(map, dateList.get(6)));
                    t.setCount8(count(map, dateList.get(7)));
                    t.setCount9(count(map, dateList.get(8)));
                    t.setCount10(count(map, dateList.get(9)));
                    t.setCount11(count(map, dateList.get(10)));
                    t.setCount12(count(map, dateList.get(11)));
                    t.setCount13(count(map, dateList.get(12)));
                    t.setCount14(count(map, dateList.get(13)));
                    t.setCount15(count(map, dateList.get(14)));
                    t.setCount16(count(map, dateList.get(15)));
                    t.setCount17(count(map, dateList.get(16)));
                    t.setCount18(count(map, dateList.get(17)));
                    t.setCount19(count(map, dateList.get(18)));
                    t.setCount20(count(map, dateList.get(19)));
                }
        ));
    }

    private String count(Map<String, List<HotCompanyData>> map, LocalDate finalDate) {
        final String split = ":";
        if (map.containsKey(finalDate.toString())) {
            List<HotCompanyData> list = map.get(finalDate.toString());
            long count1 = list.stream().filter(e -> e.getContinuityTime() == 1).count();
            long count2 = list.stream().filter(e -> e.getContinuityTime() == 2).count();
            long count3 = list.stream().filter(e -> e.getContinuityTime() == 3).count();
            long count4 = list.stream().filter(e -> e.getContinuityTime() > 3).count();
            return count4 + split + count3 + split + count2 + split + count1 + "|" + list.size();
        }
        return "0";
    }

    @Override
    public void addHotType(HotType vo) {
        if (selectByName(vo.getName()) != null) {
            throw new ServiceException("名称已存在");
        }
        vo.setCreateTime(new Date()).setUpdateTime(new Date()).insert();
        if (vo.getSort() == null || vo.getSort() == 0) {
            vo.setSort(vo.getId()).updateById();
        }
    }

    @Override
    public void updateHotType(HotType vo) {
        HotType old = selectByName(vo.getName());
        if (old != null && !old.getId().equals(vo.getId())) {
            throw new ServiceException("名称[" + vo.getName() + "]已存在");
        }
        vo.setUpdateTime(new Date()).updateById();
    }

    @Override
    public void deleteHotType(HotType vo) {
        removeById(vo.getId());
    }

    private HotType selectByName(String name) {
        LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotType::getName, name);
        return getOne(wrapper);
    }

    @Override
    public List<HotType> combogrid(String q) {
        LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(q)) {
            wrapper.like(HotType::getName, q).or().eq(HotType::getId, q);
        }
        wrapper.orderByDesc(HotType::getSort).orderByDesc(HotType::getId);
        IPage<HotType> iPage = new Page<>(1, 20);
        page(iPage, wrapper);
        return iPage.getRecords();
    }

    @Override
    public Map<String, HotType> selectMap() {
        LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(HotType::getSort).orderByDesc(HotType::getId);
        return list(wrapper).stream().collect(
                Collectors.toMap(e -> e.getId().toString(), Function.identity()));
    }

}
