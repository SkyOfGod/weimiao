package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.HotType;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotTypeInputVO;

/**
 * <p>
 * 热点类型 服务类
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
public interface HotTypeService extends IService<HotType> {

  EasyUIResult<HotType> listPage(HotTypeInputVO vo);

  void addTargetCategory(HotType vo);

  void updateTargetCategory(HotType vo);

  void deleteTargetCategory(HotType vo);
}
