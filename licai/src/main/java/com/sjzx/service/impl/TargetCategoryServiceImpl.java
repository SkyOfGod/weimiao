package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjzx.entity.TargetCategory;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.TargetCategoryMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.TargetCategoryInputVO;
import com.sjzx.service.TargetCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 指标类型明细 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
@Service
public class TargetCategoryServiceImpl extends ServiceImpl<TargetCategoryMapper, TargetCategory> implements TargetCategoryService {

    @Override
    public EasyUIResult<TargetCategory> listPage(TargetCategoryInputVO vo) {
        LambdaQueryWrapper<TargetCategory> wrapper = new LambdaQueryWrapper<>();
        if(vo.getTargetLevel() != null) {
            wrapper.eq(TargetCategory::getTargetLevel, vo.getTargetLevel());
        }
        IPage<TargetCategory> page = new Page<>(vo.getPageNo(), vo.getPageSize());
        page(page, wrapper);
        return new EasyUIResult<>(page.getTotal(), page.getRecords());
    }

    @Override
    public void addTargetCategory(TargetCategory vo) {
        if(getByIndex(vo.getPropertyName()) != null) {
            throw new ServiceException("属性名已经存在");
        }
        vo.setCreateTime(new Date()).insert();
    }

    @Override
    public void updateTargetCategory(TargetCategory vo) {
        vo.setUpdateTime(new Date()).updateById();
    }

    @Override
    public void deleteTargetCategory(TargetCategory vo) {
        vo.deleteById();
    }

    private TargetCategory getByIndex(String propertyName) {
        return getOne(new LambdaQueryWrapper<TargetCategory>().eq(TargetCategory::getPropertyName, propertyName));
    }

}
