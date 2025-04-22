package com.repaire.service.impl;

import com.repaire.mapper.TMenuMapper;
import com.repaire.mapper.TUserMapper;
import com.repaire.pojo.TMenu;
import com.repaire.pojo.TUser;
import com.repaire.service.UserService;
import com.repaire.util.EncryptionUtils;
import com.repaire.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private TUserMapper userMapper;

    @Resource
    private TMenuMapper tMenuMapper;
    @Override
    public Result getAllUserInfo() {
        List<TUser> users = userMapper.selectList(null);
        return new Result(true,null,users);
    }
    @Override
    public TUser login(String username, String password) throws Exception{
        LambdaQueryWrapper<TUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getUsername,username);
        TUser user=userMapper.selectOne(wrapper);
        if (user!=null){
            //根据用户名获取了对象数据
            String encrypt= EncryptionUtils.encrypt(password);
            if (user.getPassword().equals(encrypt)){
                return user;
            }else{
                return null;
            }
        }else{
            //根据用户名没有获取了对象数据
            return null;
        }
    }


    //根据用户id获取登陆用户的名称和菜单信息
    @Override
    public Result getUserAndRoleInfo(Integer id) {
        Map<String,Object> map = new HashMap<>();
        TUser user = userMapper.selectById(id);
        map.put("unameInfo",user.getUsername());
        //查询一级目录
        List<TMenu> parentMenu = tMenuMapper.getParentMenu(id);
        for (TMenu menu : parentMenu) {
            Integer id1 = menu.getId();
            //根据父级id 和用户id 查询二级菜单
            List<TMenu> childMenu = tMenuMapper.getChildMenu(id, id1);
            menu.setChildren(childMenu);
        }
        map.put("menuInfo",parentMenu);
        return new Result(true,null,map);
    }

    @Override
    public Result getRepairWorkers() {
        List<TUser> workers = userMapper.getRepairWorkers();
        return new Result(true,null,workers);
    }
}
