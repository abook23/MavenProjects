package com.grp.ln.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.Constant;
import com.grp.ln.web.model.Groups;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserGroup;
import com.grp.ln.web.model.UserInfo;
import com.grp.ln.web.service.GroupService;

@Controller
@RequestMapping("/mobile/group")
public class GroupController {

	@Resource
	private GroupService groupService;

	/**
	 * 添加分组
	 * 
	 * @param groups
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RootNode add(Groups groups, HttpServletRequest request) {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USER_INFO);
		groups.setUserId(userInfo.getUserId());
		return groupService.addGroup(groups);
	}

	/**
	 * 更新
	 * <p>
	 * 包括物理删除
	 * 
	 * @param groups
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RootNode update(Groups groups) {
		RootNode rootNode = new RootNode();
		try {
			groupService.update(groups);
			rootNode.setMsg("添加成功");
			rootNode.setSucceed(true);
		} catch (Exception e) {
			// TODO: handle exception
			rootNode.setMsg("添加失败");
			rootNode.setSucceed(false);
		}
		return rootNode;

	}

	/**
	 * 查询
	 * 
	 * @param groups
	 * @return
	 */
	@RequestMapping("/users")
	@ResponseBody
	public RootNode selectGroup(Groups groups, HttpSession session) {
		//UserInfo userInfo = (UserInfo) session.getAttribute(Constant.USER_INFO);
		RootNode rootNode = new RootNode();
		List<UserInfo> userInfos = groupService.selectGroupUsers(groups.getGrpId());
		rootNode.setSucceed(true);
		rootNode.setContent(userInfos);
		return rootNode;
	}

	/**
	 * 组/群
	 * @param groups
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public RootNode selectUserGroup(Groups groups,HttpSession session) {
		RootNode rootNode = new RootNode();
		UserInfo userInfo = (UserInfo) session.getAttribute(Constant.USER_INFO);
		groups.setUserId(userInfo.getUserId());
		if (groups.getFlag() == null) {
			groups.setFlag(0);
		}
		if (groups.getFlag() == 0) {
			List<Groups> listGroups = groupService.select(groups);
			rootNode.setContent(listGroups);
			rootNode.setSucceed(true);
		}else {
			List<UserGroup> userGroups = groupService.selectUserGroup(userInfo.getUserId());
			rootNode.setContent(userGroups);
			rootNode.setSucceed(true);
		}
		return rootNode;
	}

}
