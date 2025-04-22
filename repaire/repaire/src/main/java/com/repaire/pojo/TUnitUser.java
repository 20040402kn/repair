package com.repaire.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 维修单位与用户联系表
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUnitUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer unitId;

    private Integer userId;


}
