package com.grp.ln.web.service;

import java.util.List;

import com.grp.ln.core.generic.BaseService;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.model.Groups;
import com.grp.ln.web.model.UserGroup;
import com.grp.ln.web.model.UserInfo;

public interface GroupService extends BaseService<Groups, String> {

	/**
	 * 添加
	 * @param groups
	 * @return
	 */
	RootNode addGroup(Groups groups);
	RootNode addUser(String userId,String grpId);
	
	int updateByUId(Groups groups);

	List<Groups> select(Groups groups);

	List<UserInfo> selectGroupUsers(String userId);
	
	/**
	 * 获取用户的组
	 * @param userId
	 * @return
	 */
	List<UserGroup> selectUserGroup(String userId);
	
	
}
