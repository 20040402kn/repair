package com.repaire.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author sdlg
 * @since 2024-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员会id
     */
    private Integer userId;

    /**
     * 约预日期
     */
    private LocalDate orderDate;

    /**
     * 约预类型 电话预约/微信预约
     */
    private String orderType;

    /**
     * 预约状态（是否到诊）
     */
    private String orderStatus;


    //声明关系表对象
    @TableField(exist = false)
    private TUser tUser;



}
