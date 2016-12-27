package com.grp.ln.web.dao;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.web.model.MediaMsg;
import com.grp.ln.web.model.MediaMsgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MediaMsgMapper  extends BaseDao<MediaMsg, String> {
    int countByExample(MediaMsgExample example);

    int deleteByExample(MediaMsgExample example);

    int deleteByPrimaryKey(String mmsgId);

    int insert(MediaMsg record);

    int insertSelective(MediaMsg record);

    List<MediaMsg> selectByExample(MediaMsgExample example);

    MediaMsg selectByPrimaryKey(String mmsgId);

    int updateByExampleSelective(@Param("record") MediaMsg record, @Param("example") MediaMsgExample example);

    int updateByExample(@Param("record") MediaMsg record, @Param("example") MediaMsgExample example);

    int updateByPrimaryKeySelective(MediaMsg record);

    int updateByPrimaryKey(MediaMsg record);

	List<MediaMsg> selectByExample(Page<MediaMsg> page, MediaMsgExample example);
}