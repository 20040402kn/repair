package com.repaire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repaire.pojo.TItem;
import com.repaire.pojo.TItemType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TItemTypeMapper extends BaseMapper<TItemType> {

    @Select("SELECT i.id, i.item_name FROM t_item i JOIN t_item_type it ON i.id = it.item_id WHERE it.type_id = #{typeId}")
    List<TItem> getItemsByTypeId(Integer typeId);
}
