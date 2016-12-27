package com.grp.ln.web.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.core.generic.BaseServiceImpl;
import com.grp.ln.web.dao.MessageMapper;
import com.grp.ln.web.model.Message;
import com.grp.ln.web.model.MessageExample;
import com.grp.ln.web.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, String> implements MessageService {

	@Resource
	private MessageMapper messageMapper;
	

	@Override
	public BaseDao<Message, String> getDao() {
		// TODO Auto-generated method stub
		return messageMapper;
	}

	@Override
	public Page<Message> getMsgByGroup(Page<Message> page,String groupId) {
		// TODO Auto-generated method stub
		messageMapper.selectMsgByGroup(page,groupId);
		return page;
	}

	@Override
	public Page<Message> selectByUsetId(Page<Message> page,String userId) {
		// TODO Auto-generated method stub
		MessageExample example = new MessageExample();
		example.setOrderByClause("dateTime");
		example.createCriteria().andUserIdEqualTo(userId);
		messageMapper.selectByExample(page, example);
		return page;
	}

	@Override
	public List<Message> selectByUsetIds(Page<Message> page, String userIds) {
		// TODO Auto-generated method stub
		MessageExample example = new MessageExample();
		example.setOrderByClause("dateTime");
		String[] idsArray =userIds.split(",");
		List<String> idsList = Arrays.asList(idsArray);
		example.createCriteria().andUserIdIn(idsList);
		messageMapper.selectByExample(page, example);
		return null;
	}
}
