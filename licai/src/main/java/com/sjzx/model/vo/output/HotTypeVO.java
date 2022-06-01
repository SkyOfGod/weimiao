package com.sjzx.model.vo.output;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 热点类型
 * </p>
 *
 * @author 
 * @since 2021-02-04
 */
@Data
public class HotTypeVO {

    private Integer id;

    /**
     * 热点名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0-关闭 1-启用
     */
    private Integer state;

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


    private String dataDate;
    private String count1;
    private String count2;
    private String count3;
    private String count4;
    private String count5;
    private String count6;
    private String count7;
    private String count8;
    private String count9;
    private String count10;
    private String count11;
    private String count12;
    private String count13;
    private String count14;
    private String count15;
    private String count16;
    private String count17;
    private String count18;
    private String count19;
    private String count20;


}
