package com.sjzx.service;

import com.sjzx.entity.TargetCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.TargetCategoryInputVO;

/**
 * <p>
 * 指标类型明细 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
public interface TargetCategoryService extends IService<TargetCategory> {

    EasyUIResult<TargetCategory> listPage(TargetCategoryInputVO vo);

    void addTargetCategory(TargetCategory vo);

    void updateTargetCategory(TargetCategory vo);

    void deleteTargetCategory(TargetCategory vo);
}
