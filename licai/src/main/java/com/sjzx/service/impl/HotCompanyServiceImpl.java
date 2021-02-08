package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotCompany;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotCompanyMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotCompanyInputVO;
import com.sjzx.model.vo.output.HotCompanyVO;
import com.sjzx.service.HotCompanyService;
import com.sjzx.service.HotTypeService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.NumberUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  @Override
  public EasyUIResult<HotCompanyVO> listPage(HotCompanyInputVO vo) {
    LambdaQueryWrapper<HotCompany> wrapper = new LambdaQueryWrapper<>();
    if(StringUtils.hasText(vo.getName())) {
      wrapper.like(HotCompany::getName, vo.getName());
    }
    IPage<HotCompany> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
    page(iPage, wrapper);

    Map<String, HotType> map = hotTypeService.selectMap();
    return new EasyUIResult<>(iPage.getTotal(), BeanUtils.copyProperties(iPage.getRecords(), HotCompanyVO::new, (s, t) -> {
      if(s.getHotTypeIds() == null) {
        return;
      }
      List<HotType> list = new ArrayList<>();
      for(String id : s.getHotTypeIds().split(",")) {
        if(map.get(id) != null) {
          list.add(map.get(id));
        }
      }
      list.sort((a, b) -> Integer.compare(b.getSort(), a.getSort()));
      if(list.size() > 0) {
        t.setHotType1(list.get(0).getName());
        if(list.size() > 1) {
          t.setHotType2(list.get(1).getName());
          if(list.size() > 2) {
            t.setHotType3(list.get(2).getName());
          }
        }
      }
    }));
  }

  @Override
  public void addHotCompany(HotCompanyVO vo) {
    String hotTypeIds = vo.getHotType1();
    if(StringUtils.hasText(vo.getHotType2())) {
      hotTypeIds += "," + vo.getHotType2();
    }
    if(StringUtils.hasText(vo.getHotType3())) {
      hotTypeIds += "," + vo.getHotType3();
    }
    HotCompany hotCompany = BeanUtils.copyProperties(vo, HotCompany::new);
    hotCompany.setHotTypeIds(hotTypeIds).setCreateTime(new Date()).insert();
  }

  private String getHotTypeIds(String hotTypeIds, String hotType, Map<String, Integer> map) {
    if(StringUtils.hasText(hotType)) {
      if(NumberUtils.isNumeric(hotType)) {
        hotTypeIds += "," + hotType;
      } else {
        if(map.get(hotType) == null) {
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
    if(NumberUtils.isNumeric(vo.getHotType1())) {
      hotTypeIds = vo.getHotType1();
    } else {
      if(map.get(vo.getHotType1()) == null) {
        throw new ServiceException(vo.getHotType1() + "-类型不存在");
      }
      hotTypeIds = map.get(vo.getHotType1()).toString();
    }
    hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType2(), map);
    hotTypeIds = getHotTypeIds(hotTypeIds, vo.getHotType3(), map);

    HotCompany hotCompany = BeanUtils.copyProperties(vo, HotCompany::new);
    hotCompany.setHotTypeIds(hotTypeIds).setUpdateTime(new Date()).updateById();
  }



  @Override
  public void deleteHotCompany(HotCompanyVO vo) {
    removeById(vo.getId());
  }

}
