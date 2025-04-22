package com.repaire.service;

import com.repaire.pojo.TRequest;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;

public interface RequestService {
    PageResult findPage(QueryPageBean queryPageBean);

    Result allocate(TRequest request);

    Result saveRequest(TRequest request);
}
