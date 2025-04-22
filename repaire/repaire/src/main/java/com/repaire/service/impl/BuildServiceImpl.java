package com.repaire.service.impl;

import com.repaire.mapper.TBuildDormMapper;
import com.repaire.mapper.TBuildMapper;
import com.repaire.pojo.TBuild;
import com.repaire.service.BuildService;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildServiceImpl implements BuildService {
    @Resource
    private TBuildMapper buildMapper;


    @Override
    public Result getAllBuildInfo() {
        List<TBuild> builds = buildMapper.selectList(null);
        return new Result(true,null,builds);

    }
}
