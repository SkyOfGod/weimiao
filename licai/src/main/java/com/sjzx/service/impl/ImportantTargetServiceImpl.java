package com.sjzx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.CombineCashFlow;
import com.sjzx.entity.CombineProfit;
import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.entity.ImportantTarget;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.ImportantTargetMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.enums.CompanyReportTypeEnum;
import com.sjzx.model.vo.input.ImportantTargetInputVO;
import com.sjzx.model.vo.output.ImportantTargetVO;
import com.sjzx.service.CombineCashFlowService;
import com.sjzx.service.CombineProfitService;
import com.sjzx.service.ConsolidatedAssetsLiabilitiesService;
import com.sjzx.service.ImportantTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

import static com.sjzx.utils.NumberUtils.*;

/**
 * <p>
 * 重要指标表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-11-27
 */
@Service
public class ImportantTargetServiceImpl extends ServiceImpl<ImportantTargetMapper, ImportantTarget> implements ImportantTargetService {

    @Autowired
    private ConsolidatedAssetsLiabilitiesService consolidatedAssetsLiabilitiesService;

    @Autowired
    private CombineProfitService combineProfitService;

    @Autowired
    private CombineCashFlowService combineCashFlowService;

    @Override
    public EasyUIResult<ImportantTargetVO> listPage(ImportantTargetInputVO vo) {
        IPage<ImportantTargetVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        Map<String, String> map = CompanyReportTypeEnum.toMap();
        iPage.getRecords().forEach(e -> {
            e.setReportType(map.get(e.getReportType()));
            handleImportantTargetVO(e);
        });
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    private void handleImportantTargetVO(ImportantTargetVO e) {
        Class<? extends ImportantTargetVO> clz = e.getClass();
        for (Field field : clz.getDeclaredFields()) {
            if (field.getName().startsWith("t") && !"t9".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    if (field.get(e) != null) {
                        field.set(e, toPercent(field.get(e).toString()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void statistics(Integer companyId, Integer year, Integer reportType) {
        ConsolidatedAssetsLiabilities liabilities = consolidatedAssetsLiabilitiesService.getByIndex(companyId, year, reportType);
        if (liabilities == null) {
            throw new ServiceException("无当期合并资产负债表数据");
        }
        CombineProfit combineProfit = combineProfitService.getByIndex(companyId, year, reportType);
        if (combineProfit == null) {
            throw new ServiceException("无当期合并利润表数据");
        }
        CombineCashFlow cashFlow = combineCashFlowService.getByIndex(companyId, year, reportType);
        if (cashFlow == null) {
            throw new ServiceException("无当期合并现金流量表数据");
        }

        ImportantTarget entity = new ImportantTarget()
                .setCompanyId(companyId).setYear(year).setReportType(reportType).setCreateTime(new Date());
        entity.setT1(divide(combineProfit.getBelongMotherNetProfit(), liabilities.getShareHolderEquity()))
                .setT2(divide(cashFlow.getBusinessToProfit(), combineProfit.getBelongMotherNetProfit()))
                .setT3(divide(liabilities.getTotalLiabilities(), liabilities.getTotalAssets()))
                .setT4(divide(combineProfit.getBusinessIncome() - combineProfit.getBusinessCosts(), combineProfit.getBusinessIncome()))
                .setT5(divide(combineProfit.getBusinessProfit(), combineProfit.getBusinessIncome()));
        CombineProfit prefixCombineProfit = combineProfitService.getByIndex(companyId, year - 1, reportType);
        if (prefixCombineProfit != null) {
            entity.setT6(addRate(combineProfit.getBusinessIncome(), prefixCombineProfit.getBusinessIncome()));
        } else {
            entity.setT6(0);
        }
        //固定资产+在建工程+工程物资
        long fixedAssetsTotal = liabilities.getFixedAssets() + liabilities.getReconstructionProject() + liabilities.getEngineeringMaterials();
        entity.setT7(divide(fixedAssetsTotal, liabilities.getTotalAssets()))
                .setT8(divide(cashFlow.getBonusCash(), combineProfit.getBelongMotherNetProfit()));
        //人均年工资
        entity.setT9(0);
        entity.insert();
    }

}
