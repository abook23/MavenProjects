package com.grp.ln.web.dao;

import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.web.model.UserGroup;
import com.grp.ln.web.model.UserGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserGroupMapper extends BaseDao<UserGroup, String> {
    int countByExample(UserGroupExample example);

    int deleteByExample(UserGroupExample example);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    List<UserGroup> selectByExample(UserGroupExample example);

    int updateByExampleSelective(@Param("record") UserGroup record, @Param("example") UserGroupExample example);

    int updateByExample(@Param("record") UserGroup record, @Param("example") UserGroupExample example);

	List<UserGroup> selectUserGroup(String userId);
}