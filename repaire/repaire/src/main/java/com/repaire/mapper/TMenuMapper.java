package com.repaire.mapper;

import com.repaire.pojo.TMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
public interface TMenuMapper extends BaseMapper<TMenu> {
    @Select("SELECT \n" +
            " * \n" +
            "FROM\n" +
            " t_menu \n" +
            "WHERE id IN \n" +
            " (SELECT \n" +
            " menu_id \n" +
            " FROM\n" +
            " t_role_menu \n" +
            " WHERE role_id = \n" +
            " (SELECT \n" +
            " role_id \n" +
            " FROM\n" +
            " t_user_role \n" +
            " WHERE user_id = #{uid})) AND parent_menuId IS NULL\n" +
            " ")
    public List<TMenu> getParentMenu(@Param("uid") Integer id);

    @Select("SELECT \n" +
            " * \n" +
            "FROM\n" +
            " t_menu \n" +
            "WHERE id IN \n" +
            " (SELECT \n" +
            " menu_id \n" +
            " FROM\n" +
            " t_role_menu \n" +
            " WHERE role_id = \n" +
            " (SELECT \n" +
            " role_id \n" +
            " FROM\n" +
            " t_user_role \n" +
            " WHERE user_id = #{uid})) AND parent_menuId =#{pid}")
    public List<TMenu> getChildMenu(@Param("uid") Integer
                                            uid, @Param("pid") Integer pid);
}

