package com.repaire.service;

import com.repaire.pojo.TDorm;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;

public interface DormService {
    PageResult findPage(QueryPageBean queryPageBean);


    Result saveDorm(TDorm dorm, Integer id);

    Result deleteDormById(Integer id);
}
