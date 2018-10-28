package com.bootdo.rent.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.system.domain.UserDO;
import com.sun.xml.internal.rngom.parse.host.Base;
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

import com.bootdo.rent.domain.RentAdviseMessageDO;
import com.bootdo.rent.service.RentAdviseMessageService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-10-27 09:08:59
 */
 
@Controller
@RequestMapping("/rent/rentAdviseMessage")
public class RentAdviseMessageController extends BaseController {
	@Autowired
	private RentAdviseMessageService rentAdviseMessageService;
	
	@GetMapping()
	@RequiresPermissions("rent:rentAdviseMessage:rentAdviseMessage")
	String RentAdviseMessage(){
	    return "rent/rentAdviseMessage/rentAdviseMessage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:rentAdviseMessage:rentAdviseMessage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RentAdviseMessageDO> rentAdviseMessageList = rentAdviseMessageService.list(query);
		int total = rentAdviseMessageService.count(query);
		PageUtils pageUtils = new PageUtils(rentAdviseMessageList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:rentAdviseMessage:add")
	String add(){
	    return "rent/rentAdviseMessage/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:rentAdviseMessage:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RentAdviseMessageDO rentAdviseMessage = rentAdviseMessageService.get(id);
		model.addAttribute("rentAdviseMessage", rentAdviseMessage);
	    return "rent/rentAdviseMessage/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( RentAdviseMessageDO rentAdviseMessage){
        rentAdviseMessage.setCreator(rentAdviseMessage.getUsername());
        Date currDate = new Date();
        rentAdviseMessage.setCreateTime(currDate);
        rentAdviseMessage.setUpdateTime(currDate);
		if(rentAdviseMessageService.save(rentAdviseMessage)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("rent:rentAdviseMessage:edit")
	public R update( RentAdviseMessageDO rentAdviseMessage){
		rentAdviseMessageService.update(rentAdviseMessage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("rent:rentAdviseMessage:remove")
	public R remove( Long id){
		if(rentAdviseMessageService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("rent:rentAdviseMessage:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		rentAdviseMessageService.batchRemove(ids);
		return R.ok();
	}
	
}
