package com.grp.ln.web.dao;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.web.model.Message;
import com.grp.ln.web.model.MessageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseDao<Message, String> {
    int countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(String msgId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(String msgId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectByExample(Page<Message> page, MessageExample example);

    List<Message> selectMsgByGroup(Page<Message> page, String groupId);
}