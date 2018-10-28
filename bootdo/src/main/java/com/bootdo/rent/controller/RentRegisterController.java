package com.bootdo.rent.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.rent.common.RentConstant;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 2018-10-10
 */
@RequestMapping("/rent/register")
@Controller
public class RentRegisterController extends BaseController {
    @Autowired
    UserService userService;
    @Log("易租用户注册")
    @PostMapping("/add")
    @ResponseBody
    R save(UserDO user) {
        user.setName(user.getUsername());
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        user.setStatus(RentConstant.USER_STATUS_NORMAL);
        List<Long> roleIds = new ArrayList<Long>();
        roleIds.add(RentConstant.EASY_RENT_ROLE_ID);
        user.setRoleIds(roleIds);
        Date currDate = new Date();
        user.setGmtCreate(currDate);
        user.setGmtModified(currDate);
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 校验用户名称的唯一性
     * @return
     */
    @PostMapping("/validateUsernameExit")
    @ResponseBody
    String validateUsernameExit(UserDO user){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",user.getUsername());
        Boolean exit = userService.exit(map);
        if(exit){
            return "true";
        }
        return "false";
    }
    /**
     * 校验手机号码的唯一性
     * @return
     */
    @PostMapping("/validateMobileExit")
    @ResponseBody
    String validateMobileExit(UserDO user){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("mobile",user.getMobile());
        Boolean exit = userService.exit(map);
        if(exit){
            return "true";
        }
        return "false";
    }

    /**
     * 校验邮箱的唯一性
     * @return
     */
    @PostMapping("/validateEmailExit")
    @ResponseBody
    String validateEmailExit(UserDO user){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("email",user.getEmail());
        Boolean exit = userService.exit(map);
        if(exit){
            return "true";
        }
        return "false";
    }
}
