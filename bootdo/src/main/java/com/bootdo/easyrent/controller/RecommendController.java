package com.bootdo.easyrent.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.easyrent.domain.RecommendDO;
import com.bootdo.easyrent.service.RecommendService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-14 09:36:30
 */
 
@Controller
@RequestMapping("/rent/recommend")
public class RecommendController {
	@Autowired
	private RecommendService recommendService;
	
	@GetMapping()
	@RequiresPermissions("rent:recommend:recommend")
	String Recommend(){
	    return "rent/recommend/recommend";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:recommend:recommend")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RecommendDO> recommendList = recommendService.list(query);
		int total = recommendService.count(query);
		PageUtils pageUtils = new PageUtils(recommendList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:recommend:add")
	String add(){
	    return "rent/recommend/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:recommend:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RecommendDO recommend = recommendService.get(id);
		model.addAttribute("recommend", recommend);
	    return "rent/recommend/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:recommend:add")
	public R save( RecommendDO recommend){
		if(recommendService.save(recommend)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("rent:recommend:edit")
	public R update( RecommendDO recommend){
		recommendService.update(recommend);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("rent:recommend:remove")
	public R remove( Integer id){
		if(recommendService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("rent:recommend:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		recommendService.batchRemove(ids);
		return R.ok();
	}
	
}
