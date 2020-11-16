package com.sjzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.ConsolidatedAssetsLiabilitiesMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesAddVO;
import com.sjzx.model.vo.input.ConsolidatedAssetsLiabilitiesUpdVO;
import com.sjzx.model.vo.input.LiabilitiesInputVO;
import com.sjzx.model.vo.excel.ConsolidatedAssetsLiabilitiesExcelVO;
import com.sjzx.model.vo.output.ConsolidatedAssetsLiabilitiesVO;
import com.sjzx.service.ConsolidatedAssetsLiabilitiesService;
import com.sjzx.service.LiabilitiesStatisticsService;
import com.sjzx.utils.BeanUtils;
//import com.sjzx.utils.excel.ExcelUtils;
import com.sjzx.utils.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 合并资产负债表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
@Service
@Slf4j
public class ConsolidatedAssetsLiabilitiesServiceImpl extends ServiceImpl<ConsolidatedAssetsLiabilitiesMapper, ConsolidatedAssetsLiabilities> implements ConsolidatedAssetsLiabilitiesService {

    @Autowired
    private LiabilitiesStatisticsService liabilitiesStatisticsService;


    @Override
    public EasyUIResult<ConsolidatedAssetsLiabilitiesVO> listPage(LiabilitiesInputVO vo) {
        IPage<ConsolidatedAssetsLiabilitiesVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    @Transactional
    public void addLiabilities(ConsolidatedAssetsLiabilitiesAddVO vo) {
        if(getByIndex(vo.getCompanyId(), vo.getYear(), vo.getReportType()) != null) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        ConsolidatedAssetsLiabilities entity = BeanUtils.copyProperties(vo, ConsolidatedAssetsLiabilities::new);
        entity.setCreateTime(new Date()).insert();

        liabilitiesStatisticsService.statistics(entity.getCompanyId(), entity.getYear(), entity.getReportType());
    }

    @Override
    @Transactional
    public void updateLiabilities(ConsolidatedAssetsLiabilitiesUpdVO vo) {
        ConsolidatedAssetsLiabilities old = getById(vo.getId());
        if(old == null) {
            throw new ServiceException("数据不存在");
        }
        ConsolidatedAssetsLiabilities sameIndexEntity = getByIndex(old.getCompanyId(), vo.getYear(), vo.getReportType());
        if(sameIndexEntity != null && !sameIndexEntity.getId().equals(vo.getId())) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        ConsolidatedAssetsLiabilities entity = BeanUtils.copyProperties(vo, ConsolidatedAssetsLiabilities::new);
        entity.setUpdateTime(new Date()).updateById();

        liabilitiesStatisticsService.statistics(old.getCompanyId(), vo.getYear(), vo.getReportType());
    }

    @Override
    public void deleteLiabilities(ConsolidatedAssetsLiabilities vo) {
        removeById(vo.getId());
    }

    @Override
    public ConsolidatedAssetsLiabilities getByIndex(Integer companyId, Integer year, Integer reportType) {
        if(companyId == null || year == null || reportType == null) {
            return null;
        }
        LambdaQueryWrapper<ConsolidatedAssetsLiabilities> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ConsolidatedAssetsLiabilities::getCompanyId, companyId)
                .eq(ConsolidatedAssetsLiabilities::getYear, year)
                .eq(ConsolidatedAssetsLiabilities::getReportType, reportType);
        return getOne(wrapper);
    }

    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request,ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<ConsolidatedAssetsLiabilitiesExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), ConsolidatedAssetsLiabilitiesExcelVO.class, typeEnum);
            log.info("======>>>vos:{}",vos);
            if(vos != null && vos.size() > 0){
                vos.stream().forEach(x->{
                    ConsolidatedAssetsLiabilities entity = new ConsolidatedAssetsLiabilities();
                    BeanUtil.copyProperties(x,entity);
                    entity.setCompanyId(Integer.parseInt(compId.trim()));
                    entity.setCreateTime(new Date());
                    entity.insert();
                });
            }
        } catch (Exception e) {
            log.info("解析Excel系统异常:{}",e);
            throw new ServiceException("文件上传失败:" + e.getMessage());
        }
        return Response.success();
    }



}
