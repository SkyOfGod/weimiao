package com.sjzx.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjzx.entity.CashFlowStatistics;
import com.sjzx.entity.CombineCashFlow;
import com.sjzx.entity.CombineProfit;
import com.sjzx.entity.ConsolidatedAssetsLiabilities;
import com.sjzx.mapper.CashFlowStatisticsMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.enums.CompanyReportTypeEnum;
import com.sjzx.model.vo.input.LiabilitiesStatisticsInputVO;
import com.sjzx.model.vo.output.CashFlowStatisticsVO;
import com.sjzx.service.CashFlowStatisticsService;
import com.sjzx.service.CombineCashFlowService;
import com.sjzx.service.CombineProfitService;
import com.sjzx.service.ConsolidatedAssetsLiabilitiesService;
import com.sjzx.utils.BeanUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.sjzx.utils.NumberUtils.divide;
import static com.sjzx.utils.NumberUtils.toPercent;

/**
 * <p>
 * 合并现金流量表指标 服务实现类
 * </p>
 *
 * @author
 * @since 2020-11-04
 */
@Service
public class CashFlowStatisticsServiceImpl extends ServiceImpl<CashFlowStatisticsMapper, CashFlowStatistics> implements CashFlowStatisticsService {

    @Autowired
    private CombineCashFlowService combineCashFlowService;

    @Autowired
    private CombineProfitService combineProfitService;

    @Autowired
    private ConsolidatedAssetsLiabilitiesService consolidatedAssetsLiabilitiesService;

    @Override
    public EasyUIResult<CashFlowStatisticsVO> listPage(LiabilitiesStatisticsInputVO vo) {
        IPage<CashFlowStatisticsVO> iPage = new Page<>(vo.getPageNo(), vo.getPageSize());
        baseMapper.listPage(iPage, vo);
        formatToPercent(iPage.getRecords());
        return new EasyUIResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void statistics(Integer companyId, Integer year, Integer reportType) {
        CombineCashFlow current = combineCashFlowService.getByIndex(companyId, year, reportType);
        if (current == null) {
            return;
        }

        CashFlowStatistics statistics = BeanUtils.copyProperties(current, CashFlowStatistics::new);
        ConsolidatedAssetsLiabilities liabilities = consolidatedAssetsLiabilitiesService.getByIndex(companyId, year, reportType);
        if(liabilities != null) {
            //分红
            long bonusCash = current.getBonusCash().multiply(BigDecimal.valueOf(liabilities.getTotalEquity())).longValue();
            statistics.setBonusCash(bonusCash).setProfitSubstractBonus(current.getBusinessToProfit() - bonusCash);
        }
        statistics.setRemark(null).setExpandInProfitRate(divide(current.getInvestmentInsideOut(), current.getBusinessToProfit()))
                .setSellInExpandRate(divide(current.getInvestmentInsideIn(), current.getBusinessToProfit()));

        CombineProfit combineProfit = combineProfitService.getByIndex(companyId, year, reportType);
        if (combineProfit != null) {
            statistics.setCashInIncoming(divide(current.getSaleToCash(), combineProfit.getBusinessIncome()));
        } else {
            statistics.setCashInIncoming(0);
        }
        CashFlowStatistics old = getByIndex(companyId, year, reportType);
        if (old == null) {
            statistics.setCreateTime(new Date()).setUpdateTime(null).insert();
        } else {
            statistics.setId(old.getId()).setUpdateTime(new Date()).updateById();
        }
    }

    @Override
    @SneakyThrows
    public void exportData(HttpServletResponse response, LiabilitiesStatisticsInputVO vo) {
        List<CashFlowStatisticsVO> list = baseMapper.getList(vo);
        formatToPercent(list);
        ExcelWriter writer = ExcelUtil.getWriter();
        addHeaderAlias(writer);
        writer.setOnlyAlias(true);
        writer.write(list, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = URLEncoder.encode("合并现金流量表指标", "utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }

    private void addHeaderAlias(ExcelWriter writer) {
        writer.merge(12, "合并现金流量表指标数据统计");
        writer.addHeaderAlias("code", "股票代码");
        writer.addHeaderAlias("name", "公司名称");
        writer.addHeaderAlias("year", "年份");
        writer.addHeaderAlias("reportType", "参考标准");
        writer.addHeaderAlias("businessToProfit", "经营活动产生的现金流量净额");
        writer.addHeaderAlias("bonusCash", "分配股利、利润或偿付利息支付的现金");
        writer.addHeaderAlias("profitSubstractBonus", "现金净增额 经营活动产生的现金流量净额-分红");
        writer.addHeaderAlias("cashInIncoming", "销售商品提供劳务收到的现金/营业收入");
        writer.addHeaderAlias("expandInProfitRate", "购建资产/经营活动产生的现金流量净额");
        writer.addHeaderAlias("sellInExpandRate", "处置资产/购建资产");
//        writer.addHeaderAlias("remark", "备注");
//        writer.addHeaderAlias("createTime", "创建时间");
//        writer.addHeaderAlias("updateTime", "更新时间");

    }

    /**
     * 数据百分比格式化
     **/
    private void formatToPercent(List<CashFlowStatisticsVO> records) {
        Map<String, String> map = CompanyReportTypeEnum.toMap();
        records.forEach(e ->
                e.setCashInIncoming(toPercent(e.getCashInIncoming()))
                        .setExpandInProfitRate(toPercent(e.getExpandInProfitRate()))
                        .setSellInExpandRate(toPercent(e.getSellInExpandRate()))
                        .setReportType(map.get(e.getReportType()))
        );
    }

    private CashFlowStatistics getByIndex(Integer companyId, Integer year, Integer reportType) {
        if (companyId == null || year == null || reportType == null) {
            return null;
        }
        LambdaQueryWrapper<CashFlowStatistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CashFlowStatistics::getCompanyId, companyId)
                .eq(CashFlowStatistics::getYear, year)
                .eq(CashFlowStatistics::getReportType, reportType);
        return getOne(wrapper);
    }

}
