package com.sjzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.CombineCashFlow;
import com.sjzx.entity.CombineProfit;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.CombineCashFlowMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.excel.CombineCashFlowExcelVO;
import com.sjzx.model.vo.excel.CombineProfitExcelVO;
import com.sjzx.model.vo.input.CombineCashFlowAddVO;
import com.sjzx.model.vo.input.CombineCashFlowInputVO;
import com.sjzx.model.vo.output.CombineCashFlowVO;
import com.sjzx.service.CashFlowStatisticsService;
import com.sjzx.service.CombineCashFlowService;
import com.sjzx.service.ProfitStatisticsService;
import com.sjzx.utils.BeanUtils;
import com.sjzx.utils.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 合并现金流量表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-04
 */
@Service
@Slf4j
public class CombineCashFlowServiceImpl extends ServiceImpl<CombineCashFlowMapper, CombineCashFlow> implements CombineCashFlowService {

    @Autowired
    private CashFlowStatisticsService cashFlowStatisticsService;

    @Autowired
    private ProfitStatisticsService profitStatisticsService;

    @Override
    public EasyUIResult<CombineCashFlowVO> listPage(CombineCashFlowInputVO vo) {
        IPage<CombineCashFlowVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    @Transactional
    public void addCashFlow(CombineCashFlowAddVO vo) {
        if(getByIndex(vo.getCompanyId(), vo.getYear(), vo.getReportType()) != null) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        CombineCashFlow entity = BeanUtils.copyProperties(vo, CombineCashFlow::new);
        entity.setCreateTime(new Date()).insert();

        profitStatisticsService.statistics(vo.getCompanyId(), vo.getYear(), vo.getReportType());
        cashFlowStatisticsService.statistics(vo.getCompanyId(), vo.getYear(), vo.getReportType());
    }

    @Override
    @Transactional
    public void updateCashFlow(CombineCashFlow vo) {
        CombineCashFlow old = getById(vo.getId());
        if(old == null) {
            throw new ServiceException("数据不存在");
        }
        CombineCashFlow sameIndexEntity = getByIndex(old.getCompanyId(), vo.getYear(), vo.getReportType());
        if(sameIndexEntity != null && !sameIndexEntity.getId().equals(vo.getId())) {
            throw new ServiceException(vo.getYear() + "年数据已存在");
        }
        CombineCashFlow entity = BeanUtils.copyProperties(vo, CombineCashFlow::new);
        entity.setUpdateTime(new Date()).updateById();

        profitStatisticsService.statistics(old.getCompanyId(), vo.getYear(), vo.getReportType());
        cashFlowStatisticsService.statistics(old.getCompanyId(), vo.getYear(), vo.getReportType());
    }

    @Override
    public void deleteCashFlow(CombineCashFlow vo) {
        removeById(vo.getId());
    }

    @Override
    public CombineCashFlow getByIndex(Integer companyId, Integer year, Integer reportType) {
        if(companyId == null || year == null || reportType == null) {
            return null;
        }
        LambdaQueryWrapper<CombineCashFlow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CombineCashFlow::getCompanyId, companyId)
                .eq(CombineCashFlow::getYear, year)
                .eq(CombineCashFlow::getReportType, reportType);
        return getOne(wrapper);
    }

    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<CombineCashFlowExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), CombineCashFlowExcelVO.class, typeEnum);
            log.info("======>>>vos:{}",vos);
            if(vos != null && vos.size() > 0){
                vos.stream().forEach(x->{
                    CombineCashFlow entity = new CombineCashFlow();
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
