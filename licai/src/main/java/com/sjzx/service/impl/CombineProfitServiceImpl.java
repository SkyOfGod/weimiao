package com.sjzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.CombineProfit;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.CombineProfitMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.input.CombineProfitAddVO;
import com.sjzx.model.vo.input.CombineProfitInputVO;
import com.sjzx.model.vo.excel.CombineProfitExcelVO;
import com.sjzx.model.vo.output.CombineProfitVO;
import com.sjzx.service.CombineProfitService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 合并利润表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-03
 */
@Service
@Slf4j
public class CombineProfitServiceImpl extends ServiceImpl<CombineProfitMapper, CombineProfit> implements CombineProfitService {

    @Override
    public EasyUIResult<CombineProfitVO> listPage(CombineProfitInputVO vo) {
        IPage<CombineProfitVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void addCombineProfit(CombineProfitAddVO vo) {
        if(getByIndex(vo.getCompanyId(), vo.getYear(), vo.getReportType()) != null) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        CombineProfit entity = BeanUtils.copyProperties(vo, CombineProfit::new);
        entity.setCreateTime(new Date()).insert();
    }

    @Override
    public void updateCombineProfit(CombineProfit vo) {
        CombineProfit old = getById(vo.getId());
        if(old == null) {
            throw new ServiceException("数据不存在");
        }
        CombineProfit sameIndexEntity = getByIndex(old.getCompanyId(), vo.getYear(), vo.getReportType());
        if(sameIndexEntity != null && !sameIndexEntity.getId().equals(vo.getId())) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        CombineProfit entity = BeanUtils.copyProperties(vo, CombineProfit::new);
        entity.setUpdateTime(new Date()).updateById();
    }

    @Override
    public void deleteCombineProfit(CombineProfit vo) {
        removeById(vo.getId());
    }

    @Override
    public CombineProfit getByIndex(Integer companyId, Integer year, Integer reportType) {
        if(companyId == null || year == null || reportType == null) {
            return null;
        }
        LambdaQueryWrapper<CombineProfit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CombineProfit::getCompanyId, companyId)
                .eq(CombineProfit::getYear, year)
                .eq(CombineProfit::getReportType, reportType);
        return getOne(wrapper);
    }

    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<CombineProfitExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), CombineProfitExcelVO.class, typeEnum);
            log.info("======>>>vos:{}",vos);
            if(vos != null && vos.size() > 0){
                vos.stream().forEach(x->{
                    CombineProfit entity = new CombineProfit();
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
