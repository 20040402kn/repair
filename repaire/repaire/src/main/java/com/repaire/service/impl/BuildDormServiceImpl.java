package com.repaire.service.impl;

import com.repaire.mapper.TBuildDormMapper;
import com.repaire.pojo.TDorm;
import com.repaire.service.BuildDormService;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildDormServiceImpl implements BuildDormService {
    @Resource
    private TBuildDormMapper builddormMapper;
    @Override
    public Result getBuildids(Integer id) {
        List<Integer> ids = builddormMapper.getBuildIdByDormId(id);
        return new Result(true,null,ids);
    }

    @Override
    public Result getDormids(Integer buildId) {
        List<TDorm> dorms = builddormMapper.getDormsByBuildId(buildId);
        return new Result(true, null, dorms);
    }
}
