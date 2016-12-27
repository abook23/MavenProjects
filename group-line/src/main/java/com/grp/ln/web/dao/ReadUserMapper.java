package com.grp.ln.web.dao;

import com.grp.ln.web.model.ReadUser;
import com.grp.ln.web.model.ReadUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReadUserMapper {
    int countByExample(ReadUserExample example);

    int deleteByExample(ReadUserExample example);

    int insert(ReadUser record);

    int insertSelective(ReadUser record);

    List<ReadUser> selectByExample(ReadUserExample example);

    int updateByExampleSelective(@Param("record") ReadUser record, @Param("example") ReadUserExample example);

    int updateByExample(@Param("record") ReadUser record, @Param("example") ReadUserExample example);
}