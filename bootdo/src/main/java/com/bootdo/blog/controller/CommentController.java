package com.bootdo.blog.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
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

import com.bootdo.blog.domain.CommentDO;
import com.bootdo.blog.service.CommentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author haozw
 * @email hao17681124518@163.com
 * @date 2018-08-04 19:58:35
 */
 
@Controller
@RequestMapping("/blog/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;
	@Autowired
	ContentService bContentService;
	@GetMapping()
	@RequiresPermissions("blog:comment:comment")
	String Comment(){
	    return "blog/comment/comment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:comment:comment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommentDO> commentList = commentService.list(query);
		int total = commentService.count(query);
		PageUtils pageUtils = new PageUtils(commentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("blog:comment:add")
	String add(){
	    return "blog/comment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("blog:comment:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommentDO comment = commentService.get(id);
		model.addAttribute("comment", comment);
	    return "blog/comment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( CommentDO comment){
		comment.setCreateUserId(Integer.valueOf(getUserId().toString()));
		comment.setCreateUserName(getUsername());
		comment.setCreateTime(new Date());
		if(commentService.save(comment)>0){
			//评论成功则更新该博客的评论数量
			ContentDO contentDO = bContentService.get(Long.valueOf(comment.getBlogId()));
			if(contentDO.getCommentsNum() == null){
				contentDO.setCommentsNum(0);
			}
			contentDO.setCommentsNum(contentDO.getCommentsNum()+1);
			bContentService.update(contentDO);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("blog:comment:edit")
	public R update( CommentDO comment){
		commentService.update(comment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("blog:comment:remove")
	public R remove( Integer id){
		if(commentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("blog:comment:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		commentService.batchRemove(ids);
		return R.ok();
	}
	
}
