package com.grp.ln.web.dao;

import com.grp.ln.web.model.MsgGroup;
import com.grp.ln.web.model.MsgGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgGroupMapper {
    int countByExample(MsgGroupExample example);

    int deleteByExample(MsgGroupExample example);

    int insert(MsgGroup record);

    int insertSelective(MsgGroup record);

    List<MsgGroup> selectByExample(MsgGroupExample example);

    int updateByExampleSelective(@Param("record") MsgGroup record, @Param("example") MsgGroupExample example);

    int updateByExample(@Param("record") MsgGroup record, @Param("example") MsgGroupExample example);
}