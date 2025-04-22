package com.repaire.controller;


import com.repaire.pojo.TItem;
import com.repaire.service.ItemService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 报修项目表 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@RestController
@RequestMapping("/item")
public class TItemController {
    @Resource
    private ItemService itemService;
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = itemService.findPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/saveItem")
    public Result saveItem(@RequestBody TItem item,Integer id){
        Result result=itemService.saveItem(item,id);
        return result;
    }
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Integer id){
        Result result=itemService.deleteItemById(id);
        return result;
    }
    //显示所有的报修项数据
    @RequestMapping("/getAllItemInfo")
    public Result getAllItemInfo(){
        Result result=itemService.getAllItemInfo();
        return result;
    }
    @RequestMapping("/getItemTypeByItemId")
    public Result getItemTypeByItemId(Integer id) {
        Result result=itemService.getItemTypeByItemId(id);
        return result;
    }

}

