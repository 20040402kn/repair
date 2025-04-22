package com.repaire.service.impl;

import com.repaire.mapper.TTypeMapper;
import com.repaire.pojo.TType;
import com.repaire.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private TTypeMapper typeMapper;
    @Override
    public List<TType> getAllTypes() {
        List<TType> list = typeMapper.selectList(null);
        return list;
    }
}
