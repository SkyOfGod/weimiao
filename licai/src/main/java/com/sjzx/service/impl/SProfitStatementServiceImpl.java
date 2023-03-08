package com.sjzx.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.SProfitStatementMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.excel.ProfitStatementExcelVO;
import com.sjzx.model.vo.input.*;
import com.sjzx.model.vo.output.SGoodPriceVO;
import com.sjzx.model.vo.output.SProfitStatementVO;
import com.sjzx.service.SCompanyTargetService;
import com.sjzx.service.SGoodPriceService;
import com.sjzx.service.SProfitStatementService;
import com.sjzx.utils.EasyExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class SProfitStatementServiceImpl implements SProfitStatementService {

    @Autowired
    private SProfitStatementMapper sProfitStatementMapper;

    @Autowired
    private SCompanyTargetService sCompanyTargetService;


    @Override
    public SProfitStatementVO select(int companyId, int year) {
        SProfitStatementInputVO spro = new SProfitStatementInputVO();
        spro.setCompanyId(companyId);
        spro.setYear(year);

        List<SProfitStatementVO> list = sProfitStatementMapper.selectList(spro);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SProfitStatementVO> selectCompanyId(int companyId){
        SProfitStatementInputVO spro1 = new SProfitStatementInputVO();
        spro1.setCompanyId(companyId);

        return sProfitStatementMapper.selectList(spro1);
    }

    @Override
    public EasyUIResult<SProfitStatementVO> listPage(SProfitStatementInputVO vo) {
        long total = sProfitStatementMapper.selectcount(vo);

        int pageIndex = (vo.getPageNo() - 1) * vo.getPageSize();
        vo.setPageIndex(pageIndex);

        List<SProfitStatementVO> list = sProfitStatementMapper.selectList(vo);

        return new EasyUIResult<>(total, list);
    }

    @Override
    public void updateProfitStatement(SProfitStatementUpdVO vo) {
        sProfitStatementMapper.updateProfitStatement(vo);
    }


    @Override
    public void deleteProfitStatement(SProfitStatementVO vo) {
        sProfitStatementMapper.deleteProfitStatement(vo.getId());
    }

    private long translate(Object obj) {
        return SChartCashFlowServiceImpl.translate(obj);
    }

    private long translate(int index, List<ProfitStatementExcelVO> vos ,Field field, String name) throws IllegalAccessException {
        ProfitStatementExcelVO pro = vos.get(index);
        if(pro.getData0().contains(name)){
            return translate(field.get(pro));
        }
        for (int i = 0; i < vos.size() ; i++) {
            ProfitStatementExcelVO bpro = vos.get(i);
            if(bpro.getData0().contains(name)){
                return translate(field.get(bpro));
            }
        }
        return 0;
    }

    private void insertUploadData(String compId, List<ProfitStatementExcelVO> vos, String fieldName) {
        SProfitStatementVO sPro = new SProfitStatementVO();
        sPro.setCompanyId(Integer.parseInt(compId));

        try {
            Class<ProfitStatementExcelVO> clz = ProfitStatementExcelVO.class;

            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);

            String year = (String) field.get(vos.get(0));
            sPro.setYear(Integer.parseInt(year));
            //营业收入
            Long a = translate(9,vos,field,"营业收入");
            sPro.setOperatingIncome(a.intValue());
            //营业成本
            Long b = translate(11,vos,field,"营业成本");
            sPro.setOperatingCost(b.intValue());
            //销售费用 13
            Long c = translate(13,vos,field,"销售费用");
            sPro.setExpenseSales(c.intValue());
            //管理费用 14
            Long d = translate(14,vos,field,"管理费用");
            sPro.setAdministrativeExpenses(d.intValue());
            //研发费用 15
            Long e = translate(15,vos,field,"研发费用");
            sPro.setResearchDevelopmentExpenses(e.intValue());
            //财务费用 16
            Long f = translate(16,vos,field,"财务费用");
            sPro.setFinancialExpenses(f.intValue());
            //营业税金及附加 12
            Long g = translate(12,vos,field,"营业税金及附加");
            sPro.setBusinessTaxesSurcharges(g.intValue());
            //营业利润 25
            Long h = translate(25,vos,field,"营业利润");
            sPro.setOperatingProfit(h.intValue());
            //净利润 2
            Long i = translate(2,vos,field,"净利润");
            sPro.setNetProfit(i.intValue());
            //归属于母公司所有者的净利润 5
            Long j = translate(5,vos,field,"归属于母公司所有者的净利润");
            sPro.setTtm(j.intValue());

            sPro.setCreateTime(new Date());

            sProfitStatementMapper.insertProfitStatement(sPro);

            SCompanyTargetCalVO scom = new SCompanyTargetCalVO();
            scom.setCompanyId(sPro.getCompanyId());
            scom.setYear(sPro.getYear());
            sCompanyTargetService.calculateCompanyTarget(scom);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void insertUploadData1(String compId, List<ProfitStatementExcelVO> vos) {
//        SProfitStatementVO sPro = new SProfitStatementVO();
//        sPro.setCompanyId(Integer.parseInt(compId));
//        sPro.setYear(Integer.parseInt(vos.get(0).getData2()));
//
//        Long a = Long.parseLong(vos.get(3).getData2()) / 10000;
//        sPro.setOperatingIncome(a.intValue());
//
//        Long b = Long.parseLong(vos.get(4).getData2()) / 10000;
//        sPro.setOperatingCost(b.intValue());
//
//        Long c = Long.parseLong(vos.get(13).getData2()) / 10000;
//        sPro.setExpenseSales(c.intValue());
//
//        Long d = Long.parseLong(vos.get(14).getData2()) / 10000;
//        sPro.setAdministrativeExpenses(d.intValue());
//
//        Long e = Long.parseLong(vos.get(15).getData2()) / 10000;
//        sPro.setResearchDevelopmentExpenses(e.intValue());
//
//        Long f = Long.parseLong(vos.get(16).getData2()) / 10000;
//        sPro.setFinancialExpenses(f.intValue());
//
//        Long g = Long.parseLong(vos.get(12).getData2()) / 10000;
//        sPro.setBusinessTaxesSurcharges(g.intValue());
//
//        Long h = Long.parseLong(vos.get(26).getData2()) / 10000;
//        sPro.setOperatingProfit(h.intValue());
//
//        Long i = Long.parseLong(vos.get(2).getData2()) / 10000;
//        sPro.setNetProfit(i.intValue());
//
//        Long j = Long.parseLong(vos.get(5).getData2()) / 10000;
//        sPro.setTtm(j.intValue());
//
//        sPro.setCreateTime(new Date());
//
//        sProfitStatementMapper.insertProfitStatement(sPro);
//    }


    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<ProfitStatementExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), ProfitStatementExcelVO.class, typeEnum);
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
