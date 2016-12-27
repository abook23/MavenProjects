package com.grp.ln.servcie;

import javax.annotation.Resource;

import org.junit.Test;

import com.grp.ln.core.featue.test.TestSupport;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.model.User;
import com.grp.ln.web.service.UserService;

public class UserServiceTest extends TestSupport{
	
	@Resource
	private UserService userService;
	
	//@Test
	public void login(){
		
		User user = new User();
		user.setUserName("张山");
		user.setPassword("123");
		RootNode rootNode = userService.login(user);
		System.out.println(rootNode.getMsg());
	}
	
	@Test
	public void register(){
		
		User user = new User();
		user.setUserName("张山");
		user.setPassword("123");
		userService.registered(user);
	}

}
