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
  public void addTargetCategory(HotType vo) {
    if(selectByName(vo.getName()) != null) {
      throw new ServiceException("名称已存在");
    }
    vo.setCreateTime(new Date()).insert();
  }

  @Override
  public void updateTargetCategory(HotType vo) {
    HotType old = selectByName(vo.getName());
    if(old != null && !old.getId().equals(vo.getId())) {
      throw new ServiceException("名称[" + vo.getName() + "]已存在");
    }
    vo.setUpdateTime(new Date()).updateById();
  }

  @Override
  public void deleteTargetCategory(HotType vo) {
    removeById(vo.getId());
  }

  public HotType selectByName(String name) {
    LambdaQueryWrapper<HotType> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(HotType::getName, name);
    return getOne(wrapper);
  }

}
