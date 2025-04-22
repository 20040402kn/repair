package com.repaire.controller;


import com.repaire.pojo.TUser;
import com.repaire.service.UserService;
import com.repaire.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@Controller
@RequestMapping("/user")
public class TUserController {
    @Resource
    private UserService userService;
    //显示所有的用户数据
    @RequestMapping("/getAllUserInfo")
    @ResponseBody
    public Result getAllUserInfo(){
        Result result=userService.getAllUserInfo();
        return result;
    }
    //登录方法
    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            TUser user=userService.login(username,password);
            if (user!=null){
                return "redirect:/pages/main.html?id="+user.getId();
            }else {
                return "redirect:/index.html";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/index.html";
    }
    @RequestMapping("/getUserAndRoleInfo")
    @ResponseBody
    public Result getUserAndRoleInfo(Integer id){
        Result result = userService.getUserAndRoleInfo(id);
        return result;
    }
    @RequestMapping("/getRepairWorkers")
    @ResponseBody
    public Result getRepairWorkers(){
        Result result = userService.getRepairWorkers();
        return result;
    }
}

