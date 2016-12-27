package com.grp.ln.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.core.generic.BaseServiceImpl;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.dao.GroupsMapper;
import com.grp.ln.web.dao.UserGroupMapper;
import com.grp.ln.web.model.Groups;
import com.grp.ln.web.model.GroupsExample;
import com.grp.ln.web.model.GroupsExample.Criteria;
import com.grp.ln.web.model.UserGroup;
import com.grp.ln.web.model.UserInfo;
import com.grp.ln.web.service.GroupService;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Groups, String> implements
		GroupService {

	@Resource
	private GroupsMapper groupsMapper;
	@Resource
	private UserGroupMapper userGroupMapper;

	private RootNode rootNode = new RootNode();

	@Override
	public BaseDao<Groups, String> getDao() {
		// TODO Auto-generated method stub
		return groupsMapper;
	}
	
	@Override
	public RootNode addGroup(Groups groups) {
		// TODO Auto-generated method stub
		insert(groups);
		GroupsExample example = new GroupsExample();
		example.createCriteria().andUserIdEqualTo(groups.getUserId());
		List<Groups> list = groupsMapper.selectByExample(example);
		rootNode.setMsg("添加成功");
		rootNode.setSucceed(true);
		rootNode.setContent(list);

		return rootNode;
	}

	@Override
	public int updateByUId(Groups groups) {
		// TODO Auto-generated method stub
		GroupsExample example = new GroupsExample();
		example.createCriteria().andUserIdEqualTo(groups.getUserId());
		return groupsMapper.updateByExample(groups, example);
	}

	@Override
	public List<Groups> select(Groups groups) {
		// TODO Auto-generated method stub
		GroupsExample example = new GroupsExample();
		Criteria criteria = example.createCriteria();
		if (groups.getUserId() != null) {
			criteria.andUserIdEqualTo(groups.getUserId());
		}
		if (groups.getParentId() != null) {
			criteria.andParentIdEqualTo(groups.getParentId());
		}
		if (groups.getName() != null) {
			criteria.andNameEqualTo(groups.getName());
		}
		criteria.andFlagEqualTo(groups.getFlag());
		return groupsMapper.selectByExample(example);
	}

	@Override
	public List<UserInfo> selectGroupUsers(String grpId) {
		// TODO Auto-generated method stub
		return groupsMapper.selectGroupUsers(grpId);
	}
	
	@Override
	public List<UserGroup> selectUserGroup(String userId) {
		// TODO Auto-generated method stub
		return userGroupMapper.selectUserGroup(userId);
	}

	@Override
	public RootNode addUser(String userId, String grpId) {
		// TODO Auto-generated method stub
		UserGroup userGroup = new UserGroup();
		userGroup.setUserId(userId);
		userGroup.setGrpId(grpId);
		int r = userGroupMapper.insert(userGroup);
		if (r==1) {
			rootNode.setSucceed(true);
		}
		return rootNode;
	}

}
