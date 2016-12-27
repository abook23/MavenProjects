package com.grp.ln.web.service;

import java.util.List;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseService;
import com.grp.ln.web.model.Message;

public interface MessageService extends BaseService<Message, String> {

	/**
	 * 获取群/组中的消息
	 * @param groupId
	 * @return
	 */
	Page<Message> getMsgByGroup(Page<Message> page,String groupId);

	/**
	 * 获取某个用户的 消息
	 * @param page
	 * @param message
	 * @return
	 */
	Page<Message> selectByUsetId(Page<Message> page, String userId);

	List<Message> selectByUsetIds(Page<Message> page, String userId);

}
