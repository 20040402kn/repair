package com.repaire.service;

import com.repaire.pojo.TUser;
import com.repaire.util.Result;


public interface UserService {
    Result getAllUserInfo();
    TUser login(String username, String password) throws Exception;
    Result getUserAndRoleInfo(Integer id);

    Result getRepairWorkers();
}