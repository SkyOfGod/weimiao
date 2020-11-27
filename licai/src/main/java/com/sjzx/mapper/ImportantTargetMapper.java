package com.sjzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjzx.entity.ImportantTarget;
import com.sjzx.model.vo.input.ImportantTargetInputVO;
import com.sjzx.model.vo.output.ImportantTargetVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 重要指标表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-27
 */
public interface ImportantTargetMapper extends BaseMapper<ImportantTarget> {

    IPage<ImportantTargetVO> listPage(IPage<ImportantTargetVO> iPage, @Param("vo") ImportantTargetInputVO vo);
}
