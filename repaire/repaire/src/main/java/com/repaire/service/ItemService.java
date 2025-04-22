package com.repaire.service;

import com.repaire.pojo.TItem;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;

public interface ItemService {
    PageResult findPage(QueryPageBean queryPageBean);

    Result saveItem(TItem item,Integer id);

    Result deleteItemById(Integer id);

    Result getAllItemInfo();

    Result getItemTypeByItemId(Integer id);
}
