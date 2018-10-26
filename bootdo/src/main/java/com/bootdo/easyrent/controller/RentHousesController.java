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

import com.bootdo.easyrent.domain.RentHousesDO;
import com.bootdo.easyrent.service.RentHousesService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-21 21:04:04
 */
 
@Controller
@RequestMapping("/rent/rentHouses")
public class RentHousesController {
	@Autowired
	private RentHousesService rentHousesService;
	
	@GetMapping()
	@RequiresPermissions("rent:rentHouses:rentHouses")
	String RentHouses(){
	    return "rent/rentHouses/rentHouses";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:rentHouses:rentHouses")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RentHousesDO> rentHousesList = rentHousesService.list(query);
		int total = rentHousesService.count(query);
		PageUtils pageUtils = new PageUtils(rentHousesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:rentHouses:add")
	String add(){
	    return "rent/rentHouses/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:rentHouses:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RentHousesDO rentHouses = rentHousesService.get(id);
		model.addAttribute("rentHouses", rentHouses);
	    return "rent/rentHouses/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:rentHouses:add")
	public R save( RentHousesDO rentHouses){
		if(rentHousesService.save(rentHouses)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("rent:rentHouses:edit")
	public R update( RentHousesDO rentHouses){
		rentHousesService.update(rentHouses);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("rent:rentHouses:remove")
	public R remove( Integer id){
		if(rentHousesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("rent:rentHouses:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		rentHousesService.batchRemove(ids);
		return R.ok();
	}
	
}
