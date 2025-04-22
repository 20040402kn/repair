package com.repaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.mapper.TBuildDormMapper;
import com.repaire.mapper.TDormMapper;
import com.repaire.pojo.TBuildDorm;
import com.repaire.pojo.TDorm;
import com.repaire.pojo.TItemType;
import com.repaire.service.DormService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DormServiceImpl implements DormService {
    @Resource
    private TDormMapper dormMapper;
    @Resource
    private TBuildDormMapper buildDormMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        // 获取客户端传递给后台的查询条件
        String dormName = queryPageBean.getDormName();
        String buildName = queryPageBean.getBuildName();
        String location = queryPageBean.getLocation();

        // 构建分页条件
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        Page<TDorm> page = new Page<>(currentPage, pageSize);
        // 使用分页条件
        IPage<TDorm> resultPage = dormMapper.findPage(page, dormName, buildName, location);
        return new PageResult(resultPage.getTotal(), resultPage.getRecords());
    }

    @Override
    @Transactional
    public Result saveDorm(TDorm dorm, Integer id) {
        boolean flag = false;

        Integer dormId = dorm.getId();
        if (dormId != null && dormId > 0) {
            // 删除该项目对应的原来的指标数据
            LambdaUpdateWrapper<TBuildDorm> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(TBuildDorm::getDormId, dormId);
            flag = buildDormMapper.delete(wrapper) > 0;

            // 更新数据
            flag = dormMapper.updateById(dorm) > 0;

        } else {
            // 添加主表数据
            flag = dormMapper.insert(dorm) > 0;
        }
        // 添加关系表数据
        if (id != null) {
            TBuildDorm bd = new TBuildDorm();
            bd.setDormId(dorm.getId());
            bd.setBuildId(id);
            flag = buildDormMapper.insert(bd) > 0;
        }
        if (flag) {
            return new Result(flag, "操作报修项成功");
        } else {
            return new Result(flag, "操作报修项失败");
        }
    }

    @Override
    @Transactional
    public Result deleteDormById(Integer id) {
        boolean flag = false;
        //删除该项目对应的指标数据关系
        //构建条件构造器
        LambdaUpdateWrapper<TBuildDorm>
                wrapper = new LambdaUpdateWrapper<>();
        //添加条件
        wrapper.eq(TBuildDorm::getDormId,id);
        flag = buildDormMapper.delete(wrapper)>0;
        //删除项目的信息
        flag = dormMapper.deleteById(id)>0;
        if (flag){
            return  new Result(flag,"删除宿舍成功");
        }else{
            return  new Result(flag,"删除宿舍失败");
        }
    }
}

