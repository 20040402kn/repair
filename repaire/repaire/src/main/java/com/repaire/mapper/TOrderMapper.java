package com.repaire.mapper;

import com.repaire.pojo.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sdlg
 * @since 2024-12-26
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    /*@Select("SELECT o.id,o.order_date,o.order_type,o.order_status,m.name memberName,m.phone_number, s.name setmealName,s.price FROM t_order o,t_member m,t_setmeal s WHERE o.`member_id`=m.`id` AND o.`setmeal_id`=s.`id`")
    public List<Map<String,Object>> getOrderDetailInfo();*/
}
