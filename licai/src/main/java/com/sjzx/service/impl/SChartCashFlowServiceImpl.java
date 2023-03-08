package com.sjzx.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.SChartCashFlowMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.excel.ChartCashFlowExcelVO;
import com.sjzx.model.vo.input.SChartCashFlowInputVO;
import com.sjzx.model.vo.input.SChartCashFlowUpdVO;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.output.SChartCashFlowVO;
import com.sjzx.service.SChartCashFlowService;
import com.sjzx.service.SCompanyTargetService;
import com.sjzx.utils.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SChartCashFlowServiceImpl implements SChartCashFlowService {

    @Autowired
    private SChartCashFlowMapper sChartCashFlowMapper;

    @Autowired
    private SCompanyTargetService sCompanyTargetService;

    @Override
    public SChartCashFlowVO select(int companyId, int year) {
        SChartCashFlowInputVO schar = new SChartCashFlowInputVO();
        schar.setCompanyId(companyId);
        schar.setYear(year);

        List<SChartCashFlowVO> list = sChartCashFlowMapper.selectList(schar);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public EasyUIResult<SChartCashFlowVO> listpage(SChartCashFlowInputVO vo) {
        long total = sChartCashFlowMapper.selectcount(vo);

        int pageIndex = (vo.getPageNo() - 1) * vo.getPageSize();
        vo.setPageIndex(pageIndex);

        List<SChartCashFlowVO> list = sChartCashFlowMapper.selectList(vo);

        return new EasyUIResult<>(total, list);
    }

    @Override
    public void updateChartCashFlow(SChartCashFlowUpdVO vo) {
        sChartCashFlowMapper.updateChartCashFlow(vo);
    }

    @Override
    public void deletechartCashFlow(SChartCashFlowVO vo) {
        sChartCashFlowMapper.deletechartCashFlow(vo.getId());
    }

    public static long translate(Object obj) {
        String val = (String) obj;
        if (StringUtils.isEmpty(val) || val.contains("--")) {
            return 0;
        }
        if (val.contains(".")) {
            int index = val.indexOf(".");
            val = val.substring(0, index);
        }
        return Long.parseLong(val) / 10000;
    }

    public long translate(int index, List<ChartCashFlowExcelVO> vos, Field field, String name) throws IllegalAccessException {
        ChartCashFlowExcelVO chr = vos.get(index);
        if (chr.getData0().contains(name)) {
            return translate(field.get(chr));
        }
        for (int i = 0; i < vos.size(); i++) {
            ChartCashFlowExcelVO bchr = vos.get(i);
            if (bchr.getData0().contains(name)) {
                return translate(field.get(bchr));
            }
        }
        return 0;
    }

    private void insertUploadData(String compId, List<ChartCashFlowExcelVO> vos, String fieldName) {
        SChartCashFlowVO schr = new SChartCashFlowVO();
        schr.setCompanyId(Integer.parseInt(compId));

        try {
            Class<ChartCashFlowExcelVO> clz = ChartCashFlowExcelVO.class;

            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);

            String year = (String) field.get(vos.get(0));
            schr.setYear(Integer.parseInt(year));
            //经营活动产生的现金流量净额 3
            Long a = translate(3,vos,field,"经营活动产生的现金流量净额");
            schr.setOperatingCashFlow(a.intValue());
            //购建固定资产、无形资产和其他长期资产支付的现金 26
            Long b = translate(26,vos,field,"购建固定资产、无形资产和其他长期资产支付的现金");
            schr.setPurchaseConstructionFixedAssets(b.intValue());
            //分配股利、利润或偿付利息支付的现金 39
            Long c = translate(39,vos,field,"分配股利、利润或偿付利息支付的现金");
            schr.setShareBonus(c.intValue());
            //投资活动产生的现金流量净额 4
            Long d = translate(4,vos,field,"投资活动产生的现金流量净额");
            schr.setCashFlowsInvestingActivities(d.intValue());
            //筹资活动产生的现金流量净额 5
            Long e = translate(5,vos,field,"筹资活动产生的现金流量净额");
            schr.setNetCashFlowsFinancingActivities(e.intValue());

            schr.setCreateTime(new Date());

            sChartCashFlowMapper.insertChartCashFlow(schr);

            SCompanyTargetCalVO scom = new SCompanyTargetCalVO();
            scom.setCompanyId(schr.getCompanyId());
            scom.setYear(schr.getYear());

            sCompanyTargetService.calculateCompanyTarget(scom);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<ChartCashFlowExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), ChartCashFlowExcelVO.class, typeEnum);
            log.info("======>>>vos:{}", vos);
            if (vos != null && vos.size() > 0) {
                insertUploadData(compId, vos, "data6");
                insertUploadData(compId, vos, "data5");
                insertUploadData(compId, vos, "data4");
                insertUploadData(compId, vos, "data3");
                insertUploadData(compId, vos, "data2");
                insertUploadData(compId, vos, "data1");
            }
        } catch (Exception e) {
            log.info("解析Excel系统异常:{}", e);
            throw new ServiceException("文件上传失败:" + e.getMessage());
        }
        return Response.success();
    }


}
