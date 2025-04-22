package com.repaire.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.mapper.TRequestMapper;
import com.repaire.pojo.TItem;
import com.repaire.pojo.TRequest;
import com.repaire.service.RequestService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RequestServiceImpl implements RequestService {
    @Resource
    private TRequestMapper requestMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        // 获取客户端传递给后台的查询条件
        String queryString = queryPageBean.getQueryString();
        // 构建分页条件
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        Page<TRequest> page = new Page<>(currentPage, pageSize);
        // 使用分页条件
        IPage<TRequest> resultPage = requestMapper.findPage(page, queryString);
        return new PageResult(resultPage.getTotal(), resultPage.getRecords());

    }

    @Override
    public Result allocate(TRequest request) {
        boolean flag = requestMapper.updateById(request) > 0;
        if (flag) {
            return new Result(flag, "分配成功");
        } else {
            return new Result(flag, "分配失败");
        }
    }

    @Override
    public Result saveRequest(TRequest request) {
        boolean flag = requestMapper.insert(request) > 0;
        if (flag) {
            return new Result(flag, "保存成功");
        } else {
            return new Result(flag, "保存失败");
        }
    }

}
