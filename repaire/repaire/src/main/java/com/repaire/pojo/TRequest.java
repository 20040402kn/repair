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
 * @author lzy
 * @since 2024-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private String reason;

    private Integer workerId;

    private LocalDate requestDate;

    private String description;

    private Integer statusId;

    /**
     * 报修项目ID
     */
    private Integer itemId;
    private String img;
    @TableField(exist = false)
    private String itemName;
    @TableField(exist = false)
    private String dormName;
    @TableField(exist = false)
    private String buildName;
    @TableField(exist = false)
    private String repairman;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String statusName;


}
