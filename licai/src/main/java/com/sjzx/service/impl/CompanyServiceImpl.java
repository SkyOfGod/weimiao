package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.Company;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.CompanyMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.enums.CompanyCategoryEnum;
import com.sjzx.model.enums.CompanyLocationEnum;
import com.sjzx.model.enums.CompanyTypeEnum;
import com.sjzx.model.vo.input.CompanyInputVO;
import com.sjzx.model.vo.input.CompanyUpdVO;
import com.sjzx.model.vo.output.CompanyVO;
import com.sjzx.service.CompanyService;
import com.sjzx.utils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-11-02
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public EasyUIResult<CompanyVO> companyPage(CompanyInputVO vo) {
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        if (vo.getCompanyId() != null) {
            wrapper.eq(Company::getId, vo.getCompanyId());
        }
        if (vo.getType() != null) {
            wrapper.eq(Company::getType, vo.getType());
        }
        wrapper.orderByDesc(Company::getCreateTime);
        IPage<Company> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        page(iPage, wrapper);

        Map<Integer, String> typeMap = CompanyTypeEnum.toMap();
        Map<Integer, String> locationMap = CompanyLocationEnum.toMap();
        Map<Integer, String> categoryMap = CompanyCategoryEnum.toMap();
        return new EasyUIResult<>(iPage.getTotal(), BeanUtils.copyProperties(iPage.getRecords(), CompanyVO::new,
                (s, t) -> t.setType(typeMap.get(s.getType())).setLocation(locationMap.get(s.getLocation()))
                        .setCategory(categoryMap.get(s.getCategory()))));
    }

    @Override
    public void addCompany(Company vo) {
        Company company = getByCode(vo.getCode());
        if (company != null) {
            throw new ServiceException("股票代码已存在");
        }
        vo.setCreateTime(new Date()).insert();
    }

    @Override
    public void updateCompany(CompanyUpdVO vo) {
        Company entity = getById(vo.getId());
        if(entity == null) {
            throw new ServiceException("编辑数据不存在");
        }
        if(!vo.getCode().equals(entity.getCode())) {
            Company sameCodeEntity = getByCode(vo.getCode());
            if(sameCodeEntity != null && !sameCodeEntity.getId().equals(vo.getId())) {
                throw new ServiceException("股票代码【" + vo.getCode() + "】已存在");
            }
        }
        Company company = BeanUtils.copyProperties(vo, Company::new);
        company.setUpdateTime(new Date()).updateById();
    }

    @Override
    public void deleteCompany(Company vo) {
        removeById(vo.getId());
    }

    @Override
    public List<Company> combogrid(String q) {
        IPage<Company> iPage = new Page<>(1, 20);
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(q)) {
            wrapper.like(Company::getCode, q).or().like(Company::getName, q);
        }
        wrapper.orderByDesc(Company::getSort).orderByDesc(Company::getId);
        return page(iPage, wrapper).getRecords();
    }

    private Company getByCode(String code) {
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Company::getCode, code);
        return getOne(wrapper);
    }

}
