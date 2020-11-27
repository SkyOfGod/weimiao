package com.sjzx.model.vo.input;

import com.sjzx.utils.NumberUtils;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-11-02
 */
@Data
@Accessors(chain = true)
public class CompanyUpdVO {

    private Integer id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 行业类型
     */
    private Integer type;

    /**
     * 归属市场
     */
    private Integer location;

    /**
     * 类型 1-股票 2-基金 3-Reits 4-可转债 5-逆回购
     */
    private Integer category;

    /**
     * 总股本
     */
    private Long totalEquity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 指标级别
     */
    private Integer targetLevel;

    public void setType(String type) {
        if(NumberUtils.isNumeric(type)) {
            this.type = Integer.parseInt(type);
        }
    }

    public void setLocation(String location) {
        if(NumberUtils.isNumeric(location)) {
            this.location = Integer.parseInt(location);
        }
    }

    public void setCategory(String category) {
        if(NumberUtils.isNumeric(category)) {
            this.category = Integer.parseInt(category);
        }
    }
}
