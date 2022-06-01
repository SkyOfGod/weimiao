package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.HotType;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotTypeInputVO;
import com.sjzx.model.vo.output.HotTypeVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 热点类型 服务类
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
public interface HotTypeService extends IService<HotType> {

  EasyUIResult<HotTypeVO> listPage(HotTypeInputVO vo);

  void addHotType(HotType vo);

  void updateHotType(HotType vo);

  void deleteHotType(HotType vo);

  List<HotType> combogrid(String q);

  Map<String, HotType> selectMap();
}
