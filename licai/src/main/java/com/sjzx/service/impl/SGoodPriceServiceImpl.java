package com.sjzx.service.impl;

import com.sjzx.mapper.SGoodPriceMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.SGoodPriceCalVO;
import com.sjzx.model.vo.input.SGoodPriceupdVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SGoodPriceVO;
import com.sjzx.model.vo.output.SProfitStatementVO;
import com.sjzx.service.SGoodPriceService;
import com.sjzx.service.SProfitStatementService;
import com.sjzx.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.PostVMInitHook;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SGoodPriceServiceImpl implements SGoodPriceService {

    @Autowired
    private SGoodPriceMapper sGoodPriceMapper;

    @Autowired
    private SProfitStatementService sProfitStatementService;


    @Override
    public EasyUIResult<SGoodPriceVO> listPage(SLiabilitiesSearchInputVO vo) {
        long total = sGoodPriceMapper.count(vo);

        int pageIndex = (vo.getPageNo() - 1) * vo.getPageSize();
        vo.setPageIndex((pageIndex));

        List<SGoodPriceVO> list = sGoodPriceMapper.selectList(vo);

        return new EasyUIResult<>(total, list);
    }

    @Override
    public void calculateGoodPrice(SGoodPriceCalVO vo) {
        SGoodPriceVO target = new SGoodPriceVO();
        target.setCompanyId(vo.getCompanyId());
        target.setCreateTime(new Date());

        List<SProfitStatementVO> spro = sProfitStatementService.selectCompanyId(vo.getCompanyId());
        if (!spro.isEmpty()) {
            target.setYear(spro.get(0).getYear());

            int a = spro.get(0).getNetProfit();
            if (spro.size() > 4) {
                int b = spro.get(4).getNetProfit();
                double c =BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),4,BigDecimal.ROUND_HALF_UP).doubleValue();
                double r = NumberUtils.maintainAccuracy(Math.pow(c, 0.2) - 1, 1);
                target.setCompoundGrowthRate((int) r);
                sGoodPriceMapper.insertData(target);

            }
        }
    }

    @Override
    public void deleteGoodPrice(SGoodPriceVO vo) {
        sGoodPriceMapper.deleteGoodPrice(vo.getId());
    }

    @Override
    public void updateGoodPrice(SGoodPriceupdVO vo) {
       SGoodPriceVO sgod = sGoodPriceMapper.selectById(vo.getId());
       if(sgod == null){
           return;
       }
       //净利润增长率,取净利润复合增长率和机构净利润增长率中较小的值
        vo.setInstitutionalGrowthRate(vo.getInstitutionalGrowthRate()*100);
        if(sgod.getCompoundGrowthRate()>vo.getInstitutionalGrowthRate()){
            vo.setNetProfitGrowthRate(vo.getInstitutionalGrowthRate());
        }else {
            vo.setNetProfitGrowthRate(sgod.getCompoundGrowthRate());
        }

        List<SProfitStatementVO> spro = sProfitStatementService.selectCompanyId(sgod.getCompanyId());
        if (!spro.isEmpty()) {
            //未来3年的净利润
            int a = 10000+vo.getNetProfitGrowthRate();
            double b = Math.pow(a,3)/1000000000000D;
            vo.setNetProfitNextThreeYears((int)(spro.get(0).getNetProfit()*b));

            //未来三年的合理估值=未来3年的净利润*合理市盈率
            vo.setReasonableValuationNextThreeYears(vo.getNetProfitNextThreeYears()*vo.getEarningsRatio());

            //好价格=未来三年合理估值/2/总股本
            BigDecimal bg = BigDecimal.valueOf(vo.getReasonableValuationNextThreeYears()).multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(vo.getGeneralCapital()).multiply(BigDecimal.valueOf(2)),2,BigDecimal.ROUND_HALF_UP);
            vo.setGoodPrice(bg.intValue());
        }

        sGoodPriceMapper.updateGoodPrice(vo);
    }


}
