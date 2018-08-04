package com.bootdo.blog.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.blog.domain.VoteDO;
import com.bootdo.blog.service.VoteService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-08-04 22:17:13
 */
 
@Controller
@RequestMapping("/blog/vote")
public class VoteController {
	@Autowired
	private VoteService voteService;
	
	@GetMapping()
	@RequiresPermissions("blog:vote:vote")
	String Vote(){
	    return "blog/vote/vote";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:vote:vote")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VoteDO> voteList = voteService.list(query);
		int total = voteService.count(query);
		PageUtils pageUtils = new PageUtils(voteList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("blog:vote:add")
	String add(){
	    return "blog/vote/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("blog:vote:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		VoteDO vote = voteService.get(id);
		model.addAttribute("vote", vote);
	    return "blog/vote/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("blog:vote:add")
	public R save( VoteDO vote){
		if(voteService.save(vote)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("blog:vote:edit")
	public R update( VoteDO vote){
		voteService.update(vote);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("blog:vote:remove")
	public R remove( Integer id){
		if(voteService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("blog:vote:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		voteService.batchRemove(ids);
		return R.ok();
	}
	
}
