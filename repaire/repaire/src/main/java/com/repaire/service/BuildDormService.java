package com.repaire.service;

import com.repaire.util.Result;

public interface BuildDormService {
    Result getBuildids(Integer id);

    Result getDormids(Integer buildId);
}
