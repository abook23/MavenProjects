package com.grp.ln.web.dao;

import com.grp.ln.web.model.Logging;
import com.grp.ln.web.model.LoggingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoggingMapper {
    int countByExample(LoggingExample example);

    int deleteByExample(LoggingExample example);

    int deleteByPrimaryKey(String logId);

    int insert(Logging record);

    int insertSelective(Logging record);

    List<Logging> selectByExample(LoggingExample example);

    Logging selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") Logging record, @Param("example") LoggingExample example);

    int updateByExample(@Param("record") Logging record, @Param("example") LoggingExample example);

    int updateByPrimaryKeySelective(Logging record);

    int updateByPrimaryKey(Logging record);
}