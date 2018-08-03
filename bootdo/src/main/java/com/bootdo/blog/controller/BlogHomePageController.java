package com.bootdo.blog.controller;

import com.bootdo.blog.domain.CatalogDO;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.CatalogService;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/blog/homepage")
@Controller
public class BlogHomePageController extends BaseController {
	@Autowired
    ContentService bContentService;

	@Autowired
	FileService fileService;

	@Autowired
	CatalogService catalogService;

	@GetMapping()
	@RequiresPermissions("blog:homepage")
	String blogHomePage(Model model) {
		//查询用户信息
		UserDO userDO = getUser();
		model.addAttribute("user",userDO);
		//查询用户头像
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.jpg");
		}
		//查询分类信息
		Map<String,Object> catalogMap = new HashMap<String,Object>(16);
		catalogMap.put("createBy",userDO.getUserId());
		List<CatalogDO> catalogDOList = catalogService.list(catalogMap);
		if(catalogDOList==null){
			catalogDOList = new ArrayList<CatalogDO>();
		}
		model.addAttribute("catalogs",catalogDOList);
		//查询最新的博客，展示前10条
		Map<String,Object> topNew10 = new HashMap<String,Object>();
		topNew10.put("limit","8");
		topNew10.put("offset","0");
		topNew10.put("created",userDO.getUserId());
		List<ContentDO> newBlogs = bContentService.list(topNew10);
		model.addAttribute("newBlogs",newBlogs);
		return "blog/homepage/blog_homepage";
	}
	@GetMapping("/add_catalog")
	String addCatalog(){
		return "blog/homepage/add_catalog";
	}

    @GetMapping("/edit_catalog/{id}")
    String editCatalog(@PathVariable("id")Long id,Model model){
        CatalogDO catalogDO  = catalogService.get(id);
        model.addAttribute("catalog",catalogDO);
        return "blog/homepage/edit_catalog";
    }

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "blog/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}
}
