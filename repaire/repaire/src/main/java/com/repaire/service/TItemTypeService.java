package com.repaire.service;

import com.repaire.util.Result;

public interface TItemTypeService {
    Result getAllItemTypeInfo();

    Result getItemids(Integer typeId);
}
