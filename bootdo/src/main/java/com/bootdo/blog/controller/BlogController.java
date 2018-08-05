package com.bootdo.blog.controller;

import com.bootdo.blog.domain.CommentDO;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.domain.VoteDO;
import com.bootdo.blog.service.CommentService;
import com.bootdo.blog.service.ContentService;
import com.bootdo.blog.service.VoteService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/blog")
@Controller
public class BlogController extends BaseController {
	@Autowired
    ContentService bContentService;
    @Autowired
    VoteService voteService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FileService fileService;
	@GetMapping()
	String blog() {
		return "blog/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		//点击博客添加一条阅读量
		if(bContentDO.getReadNum() == null){
			bContentDO.setReadNum(0);
		}
		bContentDO.setReadNum(bContentDO.getReadNum()+1);
		bContentService.update(bContentDO);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", bContentDO.getGtmModified());
		//查询当前用户的点赞信息
        Map<String,Object> voteMap = new HashMap<String,Object>();
        voteMap.put("blogId",cid);
        //非游客登录时加载点赞信息
		boolean ifVote = false;
        if(!Constant.TEMPORARY_VISITOR_ID.equals(getUserId().toString())){
			voteMap.put("userId",getUserId());
			List<VoteDO> voteDOS = voteService.list(voteMap);

			if(voteDOS !=null&&!voteDOS.isEmpty()){
				ifVote = true;
			}
		}
        model.addAttribute("ifVote",ifVote);
        //加载评论
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("blogId",cid);
		List<CommentDO> commentDOS = commentService.list(commentMap);
		model.addAttribute("comments",commentDOS);
		//用户信息

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
	/**
	 *取消点赞
	 * @return
	 */
	@ResponseBody
	@GetMapping("/open/cancelVote")
	public R cancelVote(Long cid){
		ContentDO contentDO = bContentService.get(cid);
		if(contentDO.getVoteNum() == null|| contentDO.getVoteNum() == 0){
			return R.ok();
		}else {
			contentDO.setVoteNum(contentDO.getVoteNum()-1);
			//更新博客表的点赞数量
			bContentService.update(contentDO);
			//删除当前用的点赞信息
			Map<String,Object> voteMap = new HashMap<String,Object>();
			voteMap.put("blogId",cid);
			voteMap.put("userId",getUserId());
			List<VoteDO> voteDOS = voteService.list(voteMap);
			if(voteDOS != null&&!voteDOS.isEmpty()){
				Integer [] voteIds = new Integer[voteDOS.size()];
				for(int i=0;i<voteDOS.size();i++){
					voteIds[i] = voteDOS.get(i).getId();
				}
				voteService.batchRemove(voteIds);
			}
		}
		return R.ok();
	}

	/**
	 * 点赞
	 * @return
	 */
	@ResponseBody
	@GetMapping("/open/submitVote")
	public R submitVote(Long cid){
		ContentDO contentDO = bContentService.get(cid);
		if(contentDO.getVoteNum() == null){
			contentDO.setVoteNum(0);
		}
		contentDO.setVoteNum(contentDO.getVoteNum()+1);
		bContentService.update(contentDO);
		//点赞表添加一条记录
		VoteDO voteDO = new VoteDO();
		voteDO.setBlogId(Integer.valueOf(cid.toString()));
		voteDO.setUserId(Integer.valueOf(getUserId().toString()));
		voteDO.setUserName(getUser().getName());
		voteDO.setCreateTime(new Date());
		voteService.save(voteDO);
		return R.ok();
	}

	/**
	 * 保存--评论
	 */
	@ResponseBody
	@PostMapping("/open/saveComment")
	public CommentDO saveComment(CommentDO comment){
		comment.setCreateUserId(Integer.valueOf(getUserId().toString()));
		comment.setCreateUserName(getUser().getName());
		comment.setCreateTime(new Date());
		if(commentService.save(comment)>0){
			//评论成功则更新该博客的评论数量
			ContentDO contentDO = bContentService.get(Long.valueOf(comment.getBlogId()));
			if(contentDO.getCommentsNum() == null){
				contentDO.setCommentsNum(0);
			}
			contentDO.setCommentsNum(contentDO.getCommentsNum()+1);
			bContentService.update(contentDO);
		}
		if(getUser().getPicId()==null){
            comment.setPicUrl(Constant.TEMPORARY_VISITOR_PICURL);
        }else {
			comment.setPicUrl(fileService.get(getUser().getPicId()).getUrl());
		}
		//查询已有评论数量
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("blogId",comment.getBlogId());
		int commentCount = commentService.count(commentMap);
		comment.setRownum(commentCount+"");
		return comment;
	}
}
