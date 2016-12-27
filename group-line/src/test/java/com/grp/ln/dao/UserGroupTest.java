package com.grp.ln.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.grp.ln.core.featue.test.TestSupport;
import com.grp.ln.core.util.IdUtils;
import com.grp.ln.web.dao.UserGroupMapper;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserGroup;
import com.grp.ln.web.service.GroupService;
import com.grp.ln.web.service.UserService;

public class UserGroupTest extends TestSupport {

	@Resource
	private UserGroupMapper userGroupMapper;
	@Resource
	private UserService userService;
	@Resource
	private GroupService groupService;
	
	
	@Test
	public void selectUserByGroup(){
		UserGroup userGroup = new UserGroup();
		userGroup.setGrpId("1");
		//userGroup = userGroupMapper.selectUserByGroup(userGroup);
		System.out.println("");
	}
	
    @Test
    public void insert() {
    	
    	for(int i=0;i<3;i++){
        	User u = new User();
        	u.setUserId(IdUtils.getId());
        	u.setUserName("张山"+i);
        	userService.registered(u);
        	groupService.addUser(u.getUserId(), "7f96110f6e5f431f96eee03b9a673fd8");
    	}
    }
    
}
