package com.repaire.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.pojo.TItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repaire.pojo.TType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repaire.pojo.TItemType;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报修项目表 Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
public interface TItemMapper extends BaseMapper<TItem> {
    @Select("SELECT i.*, t.type_name AS typeName " +
            "FROM t_item i " +
            "LEFT JOIN t_item_type it ON i.id = it.item_id " +
            "LEFT JOIN t_type t ON it.type_id = t.id " +
            "WHERE (#{queryString} IS NULL OR i.item_name LIKE CONCAT('%', #{queryString}, '%')) " +
            "OR t.type_name LIKE CONCAT('%', #{queryString}, '%')")
    IPage<TItem> selectPageWithTypeName(Page<TItem> page, @Param("queryString") String queryString);

    @Select("SELECT t.id AS id, t.type_name AS typeName " +
            "FROM t_item i " +
            "LEFT JOIN t_item_type it ON i.id = it.item_id " +
            "LEFT JOIN t_type t ON it.type_id = t.id " +
            "WHERE i.id = #{id}")
    TType getItemTypeByItemId(@Param("id") Integer id);

    @Select("SELECT * FROM t_item WHERE type_id = #{typeId}")
    List<TItem> getItemsByTypeId(Integer id);
}
