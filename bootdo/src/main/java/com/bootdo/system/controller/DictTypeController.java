package com.bootdo.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
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

import com.bootdo.system.domain.DictTypeDO;
import com.bootdo.system.service.DictTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;


/**
 *
 *
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-07-29 16:00:46
 */

@Controller
@RequestMapping("/system/dictType")
public class DictTypeController extends BaseController {
	@Autowired
	private DictTypeService dictTypeService;

	@GetMapping()
	@RequiresPermissions("system:dictType:dictType")
	String DictType(){
		return "system/dictType/dictType";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:dictType:dictType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<DictTypeDO> dictTypeList = dictTypeService.list(query);
		int total = dictTypeService.count(query);
		PageUtils pageUtils = new PageUtils(dictTypeList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("system:dictType:add")
	String add(){
		return "system/dictType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:dictType:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DictTypeDO dictType = dictTypeService.get(id);
		model.addAttribute("dictType", dictType);
		return "system/dictType/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:dictType:add")
	public R save( DictTypeDO dictType){
		dictType.setCreateBy(Integer.valueOf(getUserId().toString()));
		dictType.setCreateDate(new Date());
		if(dictTypeService.save(dictType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:dictType:edit")
	public R update( DictTypeDO dictType){
		dictType.setUpdateBy(getUserId());
		dictType.setUpdateDate(new Date());
		dictTypeService.update(dictType);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:dictType:remove")
	public R remove( Long id){
		if(dictTypeService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:dictType:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		dictTypeService.batchRemove(ids);
		return R.ok();
	}

}
