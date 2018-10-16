package com.bootdo.rent.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.rent.domain.RecommendDO;
import com.bootdo.rent.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.List;

/**
 * 2018-10-10
 */
@RequestMapping("/rent")
@Controller
public class RentController extends BaseController {
	@Autowired
	private RecommendService recommendService;

	@GetMapping()
	String rentIndex(Model model) {
		//加载推荐房源信息
		List<RecommendDO> recommendList = recommendService.list(null);
		model.addAttribute("recommendList",recommendList);
		return "rent/index";
	}





}
