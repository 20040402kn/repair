package com.repaire.pojo;

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
public class TBuildDorm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer buildId;

    private Integer dormId;


}
