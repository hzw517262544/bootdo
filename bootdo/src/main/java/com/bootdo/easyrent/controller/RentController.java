package com.bootdo.easyrent.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.easyrent.common.RentConstant;
import com.bootdo.easyrent.domain.RecommendDO;
import com.bootdo.easyrent.service.RecommendService;
import com.bootdo.system.domain.UserDO;
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

	@GetMapping()
	String rentIndex(Model model) {
		//加载推荐房源信息
		List<RecommendDO> recommendList = recommendService.list(null);
		model.addAttribute("recommendList",recommendList);
		UserDO userDO = getUser();
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
	R rentLogin(String username, String password,String rememberMe) {
		//判断当前用户是否已经登录，是-提醒无需重复登录
		UserDO currUser = getUser();
		if(username.equals(currUser.getUsername())){
			return R.error("当前用户已登录，无需重复登录");
		}
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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
}
