package com.bootdo.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.blog.domain.CatalogDO;
import com.bootdo.blog.service.CatalogService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-07-30 21:36:53
 */
 
@Controller
@RequestMapping("/blog/catalog")
public class CatalogController extends BaseController {
	@Autowired
	private CatalogService catalogService;
	
	@GetMapping()
	@RequiresPermissions("blog:catalog:catalog")
	String Catalog(){
	    return "blog/catalog/catalog";
	}

	@ResponseBody
	@GetMapping("/list")
	public String list(Model model){
		//查询列表数据
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("createBy",getUserId());
		List<CatalogDO> catalogList = catalogService.list(params);
		model.addAttribute("catalogs",catalogList);
		return "blog/homepage/blog_homepage :: #catalogRepleace";
}
	
	@GetMapping("/add")
	String add(){
	    return "blog/catalog/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		CatalogDO catalog = catalogService.get(id);
		model.addAttribute("catalog", catalog);
	    return "blog/catalog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( CatalogDO catalog){
		catalog.setCreateBy(Integer.valueOf(getUserId().toString()));
		catalog.setCreateDate(new Date());
		if(catalogService.save(catalog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( CatalogDO catalog){
		catalog.setUpdateBy(getUserId());
		catalog.setUpdateDate(new Date());
		catalogService.update(catalog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(catalogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		catalogService.batchRemove(ids);
		return R.ok();
	}
	
}
