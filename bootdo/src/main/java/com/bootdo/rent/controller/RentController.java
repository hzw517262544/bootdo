package com.bootdo.rent.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.rent.common.RentConstant;
import com.bootdo.rent.domain.RecommendDO;
import com.bootdo.rent.service.RecommendService;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 2018-10-10
 */
@RequestMapping("/rent")
@Controller
public class RentController extends BaseController {
	@Autowired
	private RecommendService recommendService;

	@Autowired
    private DictService dictService;

	@Autowired
	private FileService fileService;

	@GetMapping()
	String rent() {
		return "redirect:/rent/index";
	}

	@GetMapping("/index")
	String rentIndex(Model model) {
		//加载推荐房源信息
		List<RecommendDO> recommendList = recommendService.list(null);
		model.addAttribute("recommendList",recommendList);
		UserDO userDO = getUser();
		model.addAttribute("loginUser",userDO);
		return "rent/index";
	}

	@GetMapping("/about")
	String rentAbout(Model model) {
        List<DictDO> dictDOS = dictService.listByType(RentConstant.EASY_RENT_ABOUT);
        if(dictDOS != null && !dictDOS.isEmpty()){
            for(DictDO dictDO : dictDOS){
                if(RentConstant.COMPANY_PROFILE.equals(dictDO.getName())){
                    model.addAttribute(RentConstant.COMPANY_PROFILE,dictDO.getDescription());
                }else if(RentConstant.BUSINESS_BACKGROUND.equals(dictDO.getName())){
                    model.addAttribute(RentConstant.BUSINESS_BACKGROUND,dictDO.getDescription());
                }
            }
        }
		return "rent/about";
	}

	@GetMapping("/agents")
	String rentAgents(Model model) {
		return "rent/agents";
	}

	@GetMapping("/blog")
	String rentBlog(Model model) {
		return "rent/blog";
	}

	@GetMapping("/contact")
	String rentContact(Model model) {
		UserDO userDO = getUser();
		model.addAttribute("easyRentLoginUser",userDO);
		return "rent/contact";
	}

    @GetMapping("/buysalerent")
    String rentBuysalerent(Model model) {
        return "rent/buysalerent";
    }

	@GetMapping("/register")
	String rentRegister(Model model) {
		return "rent/register";
	}

	@ResponseBody
	@GetMapping("/loadDict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@Log("易租用户登录")
	@PostMapping("/login")
	@ResponseBody
	R rentLogin(String username_login, String password_login,String rememberMe) {
		//判断当前用户是否已经登录，是-提醒无需重复登录
		UserDO currUser = getUser();
		if(username_login.equals(currUser.getUsername())){
			return R.error("当前用户已登录，无需重复登录");
		}
		password_login = MD5Utils.encrypt(username_login, password_login);
		UsernamePasswordToken token = new UsernamePasswordToken(username_login, password_login);
		if("on".equals(rememberMe)){
			token.setRememberMe(true);
		}
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	@Log("易租用户登出")
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/rent/index";
	}

	@PostMapping("/initLoginUserInfo")
	@ResponseBody
	R initLoginUserInfo(Model model){
		R r = new R();
		r.put("code", 0);
		r.put("msg", "操作成功");
		r.put("easyRentLoginUser",getUser());
		String easyRentLoginUserPicUrl = RentConstant.DEFAULT_AVATAR_URL;
		if(getUser().getPicId() != null){
			FileDO fileDO = fileService.get(getUser().getPicId());
			if(fileDO != null&&StringUtils.isNotEmpty(fileDO.getUrl())){
				easyRentLoginUserPicUrl = fileDO.getUrl();
			}
		}
		r.put("easyRentLoginUserPicUrl",easyRentLoginUserPicUrl);
		//加载易租信息
		List<DictDO> easyRentCompanyInfo = dictService.listByType(RentConstant.EASY_RENT_COMPANY_INFO);
		r.put("easyRentCompanyInfo",easyRentCompanyInfo);
		return r;
	}
}
