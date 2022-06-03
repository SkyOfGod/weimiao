package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.HotCompanyData;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.HotCompanyDataAddVO;
import com.sjzx.model.vo.input.HotCompanyDataInputVO;
import com.sjzx.model.vo.output.HotCompanyDataVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 热点公司复盘表 服务类
 * </p>
 *
 * @author 
 * @since 2022-05-21
 */
public interface HotCompanyDataService extends IService<HotCompanyData> {

    EasyUIResult<HotCompanyDataVO> listPage(HotCompanyDataInputVO vo);

    void addHotCompanyData(HotCompanyDataAddVO vo);

    void updateHotCompanyData(HotCompanyDataAddVO vo);

    void deleteHotCompanyData(HotCompanyDataAddVO vo);

    List<HotCompanyData> select(Integer hotTypeId, List<LocalDate> dataDateList);

    List<Map<String, String>> getDataDateCombobox(String q);

    int selectCountByDataDate(LocalDate date);
}