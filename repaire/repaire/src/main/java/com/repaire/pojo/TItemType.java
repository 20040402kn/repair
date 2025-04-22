package com.repaire.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报修项目表
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TItemType implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer typeId;

    private Integer itemId;



}
