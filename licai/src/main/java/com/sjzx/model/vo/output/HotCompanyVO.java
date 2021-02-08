package com.sjzx.model.vo.output;

import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HotCompanyVO {

    private Long id;

    /**
     * 公司代码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 热点1
     */
    private String hotType1;

    /**
     * 热点2
     */
    private String hotType2;

    /**
     * 热点3
     */
    private String hotType3;

    /**
     * 最近连扳次数
     */
    private Integer continuityTime;

    /**
     * 首板日期
     */
    private String firstTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
