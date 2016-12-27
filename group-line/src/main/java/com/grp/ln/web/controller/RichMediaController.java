package com.grp.ln.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.Constant;
import com.grp.ln.web.model.RichMedia;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserInfo;
import com.grp.ln.web.service.RichMediaService;

/**
 * 富媒体消息 图文消息
 * @author abook23
 *
 */
@Controller
@RequestMapping("/mobile/richMsg")
public class RichMediaController {
	
	@Resource
	private RichMediaService richMediaService;
	private RootNode rootNode = new RootNode();
	
	
	/**
	 * 新增
	 * @param richMedia
	 * @return
	 */
	@RequestMapping("/send")
	public RootNode send(RichMedia richMedia){
		int r = richMediaService.insert(richMedia);
		if (r == 1) {
			rootNode.setSucceed(true);
		}
		return rootNode;
		
	}
	
	/**
	 * 获取富媒体消息 通过id
	 * @param richMedia
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public RootNode get(RichMedia richMedia){
		RichMedia msg = richMediaService.selectById(richMedia.getRmId());
		if (msg!= null) {
			rootNode.setSucceed(true);
		}
		rootNode.setContent(msg);
		return rootNode;
		
	}
	
	/**
	 * 获取富媒体消息 通过id
	 * @param mediaMsg
	 * @return
	 */
	@RequestMapping("/delete")
	public RootNode deletel(RichMedia mediaMsg){
		int  r = richMediaService.delete(mediaMsg.getResIds());
		if (r >0 ) {
			rootNode.setSucceed(true);
		}
		return rootNode;
	}
	
	/**
	 * 获取富媒体消息
	 * @param mediaMsg
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public RootNode list(Page<RichMedia> page,HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USER_INFO);
		List<RichMedia> mediaMsgs = richMediaService.list(page,userInfo.getUserId());
		rootNode.setContent(mediaMsgs);
		if (mediaMsgs.size() >0 ) {
			rootNode.setSucceed(true);
		}
		return rootNode;
	}
}
