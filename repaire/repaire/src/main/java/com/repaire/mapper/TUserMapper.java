package com.repaire.mapper;

import com.repaire.pojo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface TUserMapper extends BaseMapper<TUser> {

    @Select("SELECT \n" +
            "    u.id ,\n" +
            "    u.username,\n" +
            "    unit.unit_name,\n" +
            "    unit.contact_person,\n" +
            "    unit.contact_phone,\n" +
            "    unit.address\n" +
            "FROM \n" +
            "    t_user u\n" +
            "JOIN \n" +
            "    t_user_role ur ON u.id = ur.user_id\n" +
            "JOIN \n" +
            "    t_role r ON ur.role_id = r.id\n" +
            "LEFT JOIN \n" +
            "    t_unit_user uu ON u.id = uu.user_id\n" +
            "LEFT JOIN \n" +
            "    t_unit unit ON uu.unit_id = unit.id\n" +
            "WHERE \n" +
            "    r.keyword = 'ROLE_REPAIRMAN';\n")
    List<TUser> getRepairWorkers();
}
