package com.grp.ln.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.featue.test.TestSupport;
import com.grp.ln.core.util.ApplicationUtils;
import com.grp.ln.core.util.IdUtils;
import com.grp.ln.web.dao.MessageMapper;
import com.grp.ln.web.dao.UserMapper;
import com.grp.ln.web.model.Message;
import com.grp.ln.web.model.MessageExample;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserExample;

public class UserMapperTest extends TestSupport {
    @Resource
    private UserMapper suserMapper;
    
    @Resource
    private MessageMapper messageMapper;

    //@Test
    public void insert() {
    	
    	for(int i=0;i<10;i++){
        	User u = new User();
        	u.setUserId(IdUtils.getId());
        	u.setUserName("张山"+i);
        	suserMapper.insert(u);
    	}
    }
    
    @Test
    public void selectByExampleAndPage(){
    	start();
        Page<User> page = new Page<>(1, 2);
        UserExample example = new UserExample();
        //example.createCriteria().andUserNameLike("张山");
        suserMapper.selectByExamplePage(page, example);
        System.out.println(page.toString());
        end();
    }
    
//    @Test
//    public void selectByExampleAndPage(){
//    	start();
//        Page<Message> page = new Page<>(1, 15);
//        MessageExample example = new MessageExample();
//        messageMapper.selectByExample(page, example);
//        System.out.println(page.toString());
//        end();
//    }
    
    
}
