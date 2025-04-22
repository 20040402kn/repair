package com.repaire.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.pojo.TDorm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repaire.pojo.TItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
public interface TDormMapper extends BaseMapper<TDorm> {

    @Select("SELECT d.*, b.build_name AS buildName, b.location AS location " +
            "FROM t_dorm d " +
            "LEFT JOIN t_build_dorm bd ON d.id = bd.dorm_id " +
            "LEFT JOIN t_build b ON bd.build_id = b.id " +
            "WHERE (#{dormName} IS NULL OR d.dorm_name LIKE CONCAT('%', #{dormName}, '%')) " +
            "AND (#{buildName} IS NULL OR b.build_name LIKE CONCAT('%', #{buildName}, '%')) " +
            "AND (#{location} IS NULL OR b.location LIKE CONCAT('%', #{location}, '%')) ")
    IPage<TDorm> findPage(Page<TDorm> page, @Param("dormName") String dormName, @Param("buildName") String buildName, @Param("location") String location);
}
