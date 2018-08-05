package com.bootdo.common.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		//如果没有登录返回游客信息
		UserDO userDO = null;
		if(ShiroUtils.getUser() != null){
			userDO = ShiroUtils.getUser();
		}else{
			userDO = new UserDO();
			userDO.setUserId(Long.valueOf(Constant.TEMPORARY_VISITOR_ID));
			userDO.setName(Constant.TEMPORARY_VISITOR_NAME);
		}
		return userDO;
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}