package com.bootdo.rent.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.rent.common.RentConstant;
import com.bootdo.rent.domain.RecommendDO;
import com.bootdo.rent.service.RecommendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
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
}
