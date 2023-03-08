package com.sjzx.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.sjzx.exception.ServiceException;
import com.sjzx.mapper.SBalanceSheetMapper;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.Response;
import com.sjzx.model.vo.excel.BalanceSheetExcelVO;
import com.sjzx.model.vo.input.SBalanceSheetUpdVO;
import com.sjzx.model.vo.input.SCompanyTargetCalVO;
import com.sjzx.model.vo.input.SLiabilitiesSearchInputVO;
import com.sjzx.model.vo.output.SBalanceSheetVO;
import com.sjzx.service.SBalanceSheetService;
import com.sjzx.service.SCompanyTargetService;
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
public class SBalanceSheetServiceImpl implements SBalanceSheetService {

    @Autowired
    private SBalanceSheetMapper sBalanceSheetMapper;

    @Autowired
    private SCompanyTargetService sCompanyTargetService;

    @Override
    public SBalanceSheetVO select(int companyId, int year) {
        SLiabilitiesSearchInputVO slia = new SLiabilitiesSearchInputVO();
        slia.setCompanyId(companyId);
        slia.setYear(year);

        List<SBalanceSheetVO> list = sBalanceSheetMapper.selectList(slia);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }


    @Override
    public EasyUIResult<SBalanceSheetVO> listPage(SLiabilitiesSearchInputVO vo) {
        long total = sBalanceSheetMapper.selectcount(vo);

        int pageIndex = (vo.getPageNo() - 1) * vo.getPageSize();
        vo.setPageIndex(pageIndex);

        List<SBalanceSheetVO> list = sBalanceSheetMapper.selectList(vo);

        return new EasyUIResult<>(total, list);
    }

    @Override
    public void updateBalanceSheet(SBalanceSheetUpdVO vo) {
        sBalanceSheetMapper.updateBalanceSheet(vo);
    }

    @Override
    public void deleteBalanceSheet(SBalanceSheetVO vo) {
        sBalanceSheetMapper.deleteBalanceSheet(vo.getId());
    }


    private long translate(Object obj) {
        return SChartCashFlowServiceImpl.translate(obj);
    }

    private long translate(int index, List<BalanceSheetExcelVO> vos, Field field, String... name) throws IllegalAccessException {
        BalanceSheetExcelVO bal = vos.get(index);
        if (bal.getData0().contains(name[0])) {
            if(name.length > 1 ){
                if(!bal.getData0().contains(name[1])){
                    return translate(field.get(bal));
                }
            }else {
                return translate(field.get(bal));
            }
        }
        for (BalanceSheetExcelVO bbal : vos) {
            if (bbal.getData0().contains(name[0])) {
                if (name.length > 1) {
                    if (!bbal.getData0().contains(name[1])) {
                        return translate(field.get(bbal));
                    }
                } else {
                    return translate(field.get(bbal));
                }
            }
        }
        return 0;
    }


    private void insertUploadData(String compId, List<BalanceSheetExcelVO> vos, String fieldName) {
        SBalanceSheetVO sbal = new SBalanceSheetVO();
        sbal.setCompanyId(Integer.parseInt(compId));

        try {
            Class<BalanceSheetExcelVO> clz = BalanceSheetExcelVO.class;

            Field field = clz.getDeclaredField(fieldName);
            field.setAccessible(true);

            String year = (String) field.get(vos.get(0));
            sbal.setYear(Integer.parseInt(year));
            //资产合计
            Long a = translate(3, vos, field, "资产合计");
            sbal.setTotalAssets(a.intValue());
            //负债合计
            Long b = translate(4, vos, field, "负债合计");
            sbal.setTotalLiabilities(b.intValue());
            //货币资金
            Long c = translate(8, vos, field, "货币资金");
            sbal.setMonetaryFunds(c.intValue());
            //交易性金融资产
            Long d = translate(9, vos, field, "交易性金融资产");
            sbal.setTradingFinancialAssets(d.intValue());
            //其他流动资产
            Long e = translate(19, vos, field, "其他流动资产");
            sbal.setOtherCurrentAssets(e.intValue());
            //短期借款
            Long f = translate(43, vos, field, "短期借款");
            sbal.setShortTermBorrowing(f.intValue());
            //一年到期的非流动负债
            Long g = translate(57, vos, field, "一年内到期的非流动负债");
            sbal.setNonCurrentLiability(g.intValue());
            //长期借款
            Long h = translate(61, vos, field, "长期借款");
            sbal.setLongTermBorrowing(h.intValue());
            //长期应付款合计 62
            Long i = translate(62, vos, field, "长期应付款合计");
            sbal.setTotalLongTermPayables(i.intValue());
            //应付债券 62
            Long b1 = translate(63, vos, field, "应付债券");
            sbal.setBondsPayable(b1.intValue());
            //应付票据 47
            Long j = translate(47, vos, field, "应付票据(元)");
            sbal.setNotesPayable(j.intValue());
            //应付账款 48
            Long k = translate(48, vos, field, "应付账款","应付票据");
            sbal.setAccountsPayable(k.intValue());
            //应付票据及应付账款 46
            Long l = translate(46, vos, field, "应付票据及应付账款");
            sbal.setNotesAccounts(l.intValue());
            //预收款项 49
            Long m = translate(49, vos, field, "预收款项");
            sbal.setAccountCollectedAdvance(m.intValue());
            //合同负债 50
            Long n = translate(50, vos, field, "合同负债");
            sbal.setLiabilityForContract(n.intValue());
            //应收票据 11
            Long o = translate(11, vos, field, "应收票据");
            sbal.setNotesReceivable(o.intValue());
            //应收账款 12
            Long p = translate(12, vos, field, "应收账款");
            sbal.setAccountsReceivable(p.intValue());
            //预付款项 13
            Long q = translate(13, vos, field, "预付款项");
            sbal.setPrepayment(q.intValue());
            //固定资产合计 29
            Long r = translate(29, vos, field, "固定资产合计");
            sbal.setTotalFixedAssets(r.intValue());
            //固定资产 30
            Long s = translate(30, vos, field, "固定资产(元)");
            sbal.setFixedAssets(s.intValue());
            //在建工程合计 32
            Long t = translate(32, vos, field, "在建工程合计");
            sbal.setTotalWorksInProgress(t.intValue());
            //在建工程 33
            Long u = translate(33, vos, field, "在建工程(元)");
            sbal.setWorkInProgress(u.intValue());
            //工程物资 34
            Long v = translate(34, vos, field, "工程物资");
            sbal.setMaterialsForConstruction(v.intValue());
            //可供出售的金融资产 23
            Long w = translate(23, vos, field, "可供出售金融资产");
            sbal.setFinancialAssetsSale(w.intValue());
            //持有到期的金融资产 24
            Long x = translate(24, vos, field, "持有至到期投资");
            sbal.setHoldFinancialAssets(x.intValue());
            //长期股权投资 25
            Long y = translate(25, vos, field, "长期股权投资");
            sbal.setLongTermEquityInvestment(y.intValue());
            //投资性房地产 28
            Long z = translate(28, vos, field, "投资性房地产");
            sbal.setInvestmentRealEstate(z.intValue());
            //其他权益工具投资 26
            Long a1 = translate(26, vos, field, "其他权益工具投资");
            sbal.setInvestmentInOther(a1.intValue());
            //其他非流动的金融资产 27
            Long a2 = translate(27, vos, field, "其他非流动金融资产");
            sbal.setOtherNonCurrentFinancialAssets(a2.intValue());
            //存货 17
            Long a3 = translate(17, vos, field, "存货");
            sbal.setInventory(a3.intValue());
            //商誉 36
            Long a4 = translate(36, vos, field, "商誉");
            sbal.setGoodwill(a4.intValue());
            //归属于母公司的所有者权益合计 5
            Long a5 = translate(5, vos, field, "归属于母公司所有者权益合计");
            sbal.setTotalOwnersEquity(a5.intValue());

            sbal.setCreateTime(new Date());

            sBalanceSheetMapper.insertBalanceSheet(sbal);
            SCompanyTargetCalVO scom = new SCompanyTargetCalVO();
            scom.setCompanyId(sbal.getCompanyId());
            scom.setYear(sbal.getYear());

            sCompanyTargetService.calculateCompanyTarget(scom);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public Response uploadExcel(MultipartFile file, HttpServletRequest request, ExcelTypeEnum typeEnum) {
        try {
            String compId = request.getParameter("id");
            List<BalanceSheetExcelVO> vos = EasyExcelUtils.readExcelWithModel(file.getInputStream(), BalanceSheetExcelVO.class, typeEnum);
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


