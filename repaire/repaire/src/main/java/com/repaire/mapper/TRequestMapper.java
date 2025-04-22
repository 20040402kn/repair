package com.repaire.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.pojo.TRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
public interface TRequestMapper extends BaseMapper<TRequest> {

    @Select("SELECT " +
            "r.id AS id, " +
            "r.status_id AS status_id , " +
            "b.build_name AS buildName, " +
            "d.dorm_name AS dormName, " +
            "w.username AS repairman, " +
            "i.item_name AS itemName, " +
            "t.type_name AS typeName, " +
            "r.request_date AS requestDate, " +
            "st.status_name AS statusName, " +
            "r.description AS description, " +
            "r.img AS img " +
            "FROM " +
            "t_request r " +
            "LEFT JOIN t_user s ON r.student_id = s.id " +
            "LEFT JOIN t_user w ON r.worker_id = w.id " +
            "LEFT JOIN t_status st ON r.status_id = st.id " +
            "LEFT JOIN t_item i ON r.item_id = i.id " +
            "LEFT JOIN t_item_type it ON i.id = it.item_id " +
            "LEFT JOIN t_type t ON it.type_id = t.id " +
            "LEFT JOIN t_user_build ub ON s.id = ub.user_id " +
            "LEFT JOIN t_build_dorm bd ON ub.builddorm_id = bd.id " +
            "LEFT JOIN t_build b ON b.id = bd.build_id " +
            "LEFT JOIN t_dorm d ON bd.dorm_id = d.id " +
            "WHERE " +
            "(#{queryString} IS NULL OR r.id LIKE CONCAT('%', #{queryString}, '%') OR w.username LIKE CONCAT('%', #{queryString}, '%') OR i.item_name LIKE CONCAT('%', #{queryString}, '%')) " + // 添加空格
            "ORDER BY r.id")
    IPage<TRequest> findPage(Page<TRequest> page, @Param("queryString") String queryString);

    // 今日报修数据
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date = #{today}")
    public long getCountToday(@Param("today") String today);

    // 本周报修数
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date BETWEEN #{firstDayThisWeek} AND #{today}")
    public long getCountWeek(@Param("today") String today, @Param("firstDayThisWeek") String firstDayThisWeek);

    // 本月报修数
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date BETWEEN #{firstDayThisMonth} AND #{today}")
    public long getCountMonth(@Param("today") String today, @Param("firstDayThisMonth") String firstDayThisMonth);

    // 今日维修数据
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date = #{today} AND status_id = 4")
    public long getCountTodayEnd(@Param("today") String today);

    // 本周维修数
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date BETWEEN #{firstDayThisWeek} AND #{today} AND status_id = 4")
    public long getCountWeekEnd(@Param("today") String today, @Param("firstDayThisWeek") String firstDayThisWeek);

    // 本月维修数
    @Select("SELECT COUNT(*) FROM t_request WHERE request_date BETWEEN #{firstDayThisMonth} AND #{today} AND status_id = 4")
    public long getCountMonthEnd(@Param("today") String today, @Param("firstDayThisMonth") String firstDayThisMonth);

    @Select("SELECT item.item_name AS itemName, COUNT(request.id) AS repairCount, " +
            "CONCAT(ROUND(COUNT(request.id) * 100.0 / (SELECT COUNT(*) FROM t_request), 2), '%') AS proportion, " +
            "item.remark AS remark " +
            "FROM t_request request " +
            "JOIN t_item item ON request.item_id = item.id " +
            "GROUP BY item.item_name, item.remark " +
            "ORDER BY repairCount DESC")
    List<Map<String, Object>> hotRepairItems();
}
