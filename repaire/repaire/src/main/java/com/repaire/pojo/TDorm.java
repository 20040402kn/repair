package com.repaire.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class TDorm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String dormName;

    private Integer capacity;

    @TableField(exist = false)
    private String buildName;
    @TableField(exist = false)
    private String location;
    @TableField(exist = false)
    private Integer buildId;

}
