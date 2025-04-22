package com.repaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repaire.mapper.TItemMapper;
import com.repaire.mapper.TItemTypeMapper;
import com.repaire.mapper.TTypeMapper;
import com.repaire.pojo.TItem;
import com.repaire.pojo.TItemType;
import com.repaire.pojo.TType;
import com.repaire.service.ItemService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private TItemMapper itemMapper;
    @Resource
    private TItemTypeMapper itemTypeMapper;
    @Resource
    private TTypeMapper typeMapper;


    @Override
    @Transactional
    public Result deleteItemById(Integer id) {
        boolean flag = false;
        //删除该项目对应的指标数据关系
        //构建条件构造器
        LambdaUpdateWrapper<TItemType>
                wrapper = new LambdaUpdateWrapper<>();
        //添加条件
        wrapper.eq(TItemType::getItemId,id);
        flag = itemTypeMapper.delete(wrapper)>0;
        //删除项目的信息
        flag = itemMapper.deleteById(id)>0;
        if (flag){
            return  new Result(flag,"删除报修成功");
        }else{
            return  new Result(flag,"删除报修失败");
        }
    }



    @Override
    public Result getAllItemInfo() {
        List<TItem> items = itemMapper.selectList(null);
        return new Result(true,null,items);
    }



    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        // 获取客户端传递给后台的查询条件
        String queryString = queryPageBean.getQueryString();
        // 构建分页条件
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        Page<TItem> page = new Page<>(currentPage, pageSize);
        // 使用分页条件
        IPage<TItem> resultPage = itemMapper.selectPageWithTypeName(page, queryString);
        return new PageResult(resultPage.getTotal(), resultPage.getRecords());
    }

    @Override
    @Transactional
    public Result saveItem(TItem item,Integer id) {
        boolean flag = false;

        Integer itemId = item.getId();
        if (itemId!=null && itemId>0){
            //删除该项目对应的原来的指标数据
            LambdaUpdateWrapper<TItemType>
                    wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(TItemType::getItemId,itemId);
            flag = itemTypeMapper.delete(wrapper)>0;

            //更新数据
            flag = itemMapper.updateById(item)>0;
        }else{
            //添加主表数据
            flag = itemMapper.insert(item)>0;
        }
        //添加关系表数据
        if (id!=null){
            TItemType it = new TItemType();
            it.setItemId(item.getId());
            it.setTypeId(id);
            flag=itemTypeMapper.insert(it)>0;
        }
        if (flag){
            return  new Result(flag,"操作报修项成功");
        }else{
            return  new Result(flag,"操作报修项失败");
        }
    }
    @Override
    public Result getItemTypeByItemId(Integer id) {
        TType type = itemMapper.getItemTypeByItemId(id);
        if (type != null) {
            return new Result(true, "获取类别信息成功", type);
        } else {
            return new Result(false, "获取类别信息失败");
        }
    }
}
