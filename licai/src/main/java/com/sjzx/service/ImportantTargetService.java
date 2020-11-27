package com.sjzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjzx.entity.ImportantTarget;
import com.sjzx.model.EasyUIResult;
import com.sjzx.model.vo.input.ImportantTargetInputVO;
import com.sjzx.model.vo.output.ImportantTargetVO;

/**
 * <p>
 * 重要指标表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
public interface ImportantTargetService extends IService<ImportantTarget> {

    EasyUIResult<ImportantTargetVO> listPage(ImportantTargetInputVO vo);

    void statistics(Integer companyId, Integer year, Integer reportType);
}
