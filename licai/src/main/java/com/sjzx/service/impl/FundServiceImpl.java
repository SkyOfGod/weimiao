package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.Fund;
import com.sjzx.mapper.FundMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.FundAddVO;
import com.sjzx.model.vo.input.FundInputVO;
import com.sjzx.service.FundService;
import com.sjzx.utils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基金表 服务实现类
 * </p>
 *
 * @author
 * @since 2021-08-20
 */
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements FundService {


    @Override
    public EasyUIResult<Fund> listPage(FundInputVO vo) {
        LambdaQueryWrapper<Fund> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(vo.getName())) {
            wrapper.like(Fund::getName, vo.getName())
                    .or().eq(Fund::getCode, vo.getName());
        }
        wrapper.orderByDesc(Fund::getSort).orderByDesc(Fund::getCreateTime);
        IPage<Fund> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        page(iPage, wrapper);
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void addFund(FundAddVO vo) {
        BigDecimal percent = countAverageIncome(vo);

        Fund fund = BeanUtils.copyProperties(vo, Fund::new);
        fund.setAverageIncome(percent.intValue())
                .setCreateTime(new Date())
                .insert();

    }

    @Override
    public void updateFund(FundAddVO vo) {
        BigDecimal percent = countAverageIncome(vo);

        Fund fund = BeanUtils.copyProperties(vo, Fund::new);
        fund.setAverageIncome(percent.intValue())
                .setUpdateTime(new Date())
                .updateById();
    }

    @Override
    public void deleteFund(FundAddVO vo) {
        removeById(vo.getId());
    }

    public BigDecimal countAverageIncome(FundAddVO vo) {
        BigDecimal createPrice = new BigDecimal(vo.getFundCreatePrice().trim());
        BigDecimal price = new BigDecimal(vo.getPrice().trim());

        //计算基金创建时间到现在的时间年限间隔
        BigDecimal fundCreateYear = new BigDecimal(vo.getFundCreateTime().trim().substring(0, 4));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        BigDecimal nowYear = new BigDecimal(LocalDate.now().format(dateTimeFormatter));
        BigDecimal period = nowYear.add(BigDecimal.valueOf(1)).subtract(fundCreateYear);

        return price.subtract(createPrice).multiply(BigDecimal.valueOf(10000))
                .divide(createPrice.multiply(period), 4, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public List<Fund> combogrid(String q) {
        IPage<Fund> iPage = new Page<>(1, 20);
        LambdaQueryWrapper<Fund> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(q)) {
            wrapper.like(Fund::getCode, q).or().like(Fund::getName, q);
        }
        wrapper.orderByDesc(Fund::getSort).orderByDesc(Fund::getId);
        return page(iPage, wrapper).getRecords();
    }


}
