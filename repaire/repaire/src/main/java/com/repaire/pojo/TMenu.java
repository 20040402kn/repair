package com.repaire.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String linkUrl;

    private String path;

    private Integer priority;

    private String icon;

    private String description;

    @TableField("parent_menuId")
    private Integer parentMenuid;

    private Integer level;

    @TableField(exist = false)
    private List<TMenu> children;


}
