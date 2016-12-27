package com.grp.ln.web.dao;

import com.grp.ln.web.model.Appversions;
import com.grp.ln.web.model.AppversionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppversionsMapper {
    int countByExample(AppversionsExample example);

    int deleteByExample(AppversionsExample example);

    int deleteByPrimaryKey(String resId);

    int insert(Appversions record);

    int insertSelective(Appversions record);

    List<Appversions> selectByExample(AppversionsExample example);

    Appversions selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") Appversions record, @Param("example") AppversionsExample example);

    int updateByExample(@Param("record") Appversions record, @Param("example") AppversionsExample example);

    int updateByPrimaryKeySelective(Appversions record);

    int updateByPrimaryKey(Appversions record);
}