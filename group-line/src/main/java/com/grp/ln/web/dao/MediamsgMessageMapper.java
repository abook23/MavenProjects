package com.grp.ln.web.dao;

import com.grp.ln.web.model.MediamsgMessage;
import com.grp.ln.web.model.MediamsgMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MediamsgMessageMapper {
    int countByExample(MediamsgMessageExample example);

    int deleteByExample(MediamsgMessageExample example);

    int insert(MediamsgMessage record);

    int insertSelective(MediamsgMessage record);

    List<MediamsgMessage> selectByExample(MediamsgMessageExample example);

    int updateByExampleSelective(@Param("record") MediamsgMessage record, @Param("example") MediamsgMessageExample example);

    int updateByExample(@Param("record") MediamsgMessage record, @Param("example") MediamsgMessageExample example);
}