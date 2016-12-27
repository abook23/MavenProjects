package com.grp.ln.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.web.model.Groups;
import com.grp.ln.web.model.GroupsExample;
import com.grp.ln.web.model.UserInfo;

public interface GroupsMapper extends BaseDao<Groups, String> {
	int countByExample(GroupsExample example);

	int deleteByExample(GroupsExample example);

	int deleteByPrimaryKey(String grpId);

	int insert(Groups record);

	int insertSelective(Groups record);

	List<Groups> selectByExample(GroupsExample example);

	Groups selectByPrimaryKey(String grpId);

	int updateByExampleSelective(@Param("record") Groups record,
			@Param("example") GroupsExample example);

	int updateByExample(@Param("record") Groups record,
			@Param("example") GroupsExample example);

	int updateByPrimaryKeySelective(Groups record);

	int updateByPrimaryKey(Groups record);

	List<UserInfo> selectGroupUsers(String grpId);
}