package com.repaire.service.impl;

import com.repaire.mapper.TItemTypeMapper;
import com.repaire.mapper.TItemMapper;
import com.repaire.mapper.TTypeMapper;
import com.repaire.pojo.TDorm;
import com.repaire.pojo.TItem;
import com.repaire.pojo.TItemType;
import com.repaire.pojo.TType;
import com.repaire.service.TItemTypeService;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TItemTypeServiceImpl implements TItemTypeService {
    @Resource
    private TItemTypeMapper itemTypeMapper;

    @Resource
    private TTypeMapper typeMapper;

    @Resource
    private TItemMapper itemMapper;

    @Override
    public Result getAllItemTypeInfo() {
        List<TType> types = typeMapper.selectList(null);
        List<Map<String, Object>> itemTypeInfoList = new ArrayList<>();

        for (TType type : types) {
            Map<String, Object> itemTypeInfo = new HashMap<>();
            itemTypeInfo.put("typeId", type.getId());
            itemTypeInfo.put("typeName", type.getTypeName());

            List<TItem> items = itemMapper.getItemsByTypeId(type.getId());
            itemTypeInfo.put("items", items);

            itemTypeInfoList.add(itemTypeInfo);
        }

        return new Result(true, null, itemTypeInfoList);
    }

    @Override
    public Result getItemids(Integer typeId) {
        List<TItem> items = itemTypeMapper.getItemsByTypeId(typeId);
        return new Result(true, null, items);
    }
}
