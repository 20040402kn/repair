package com.repaire.mapper;

import com.repaire.pojo.TBuildDorm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repaire.pojo.TDorm;
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
public interface TBuildDormMapper extends BaseMapper<TBuildDorm> {
    //mybatisplus的注解模式
    @Select("SELECT build_id FROM t_build_dorm WHERE dorm_id = #{id}")
    public List<Integer> getBuildIdByDormId(@Param("id") Integer id);

    @Select("SELECT d.id, d.dorm_name FROM t_dorm d JOIN t_build_dorm bd ON d.id = bd.dorm_id WHERE bd.build_id = #{buildId}")
    List<TDorm> getDormsByBuildId(Integer buildId);
}
