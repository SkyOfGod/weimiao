package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.HotType;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.HotTypeMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotTypeInputVO;
import com.sjzx.service.HotTypeService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  @Override
  public EasyUIResult<HotType> listPage(HotTypeInputVO vo) {
    LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
    if(StringUtils.hasText(vo.getName())) {
      wrapper.like(HotType::getName, vo.getName());
    }
    IPage<HotType> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
    page(iPage, wrapper);
    return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
  }

  @Override
  public void addHotType(HotType vo) {
    if(selectByName(vo.getName()) != null) {
      throw new ServiceException("名称已存在");
    }
    vo.setCreateTime(new Date()).insert();
  }

  @Override
  public void updateHotType(HotType vo) {
    HotType old = selectByName(vo.getName());
    if(old != null && !old.getId().equals(vo.getId())) {
      throw new ServiceException("名称[" + vo.getName() + "]已存在");
    }
    vo.setUpdateTime(new Date()).updateById();
  }

  @Override
  public void deleteHotType(HotType vo) {
    removeById(vo.getId());
  }

  public HotType selectByName(String name) {
    LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(HotType::getName, name);
    return getOne(wrapper);
  }

  @Override
  public List<HotType> combogrid(String q) {
    LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
    if(StringUtils.hasText(q)) {
      wrapper.like(HotType::getName, q);
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
