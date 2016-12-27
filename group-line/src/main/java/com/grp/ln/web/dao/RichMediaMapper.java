package com.grp.ln.web.dao;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.web.model.RichMedia;
import com.grp.ln.web.model.RichMediaExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RichMediaMapper extends BaseDao<RichMedia, String> {
    int countByExample(RichMediaExample example);

    int deleteByExample(RichMediaExample example);

    int deleteByPrimaryKey(String rmId);

    int insert(RichMedia richMedia);

    int insertSelective(RichMedia record);

    List<RichMedia> selectByExample(RichMediaExample example);

    RichMedia selectByPrimaryKey(String rmId);

    int updateByExampleSelective(@Param("record") RichMedia record, @Param("example") RichMediaExample example);

    int updateByExample(@Param("record") RichMedia record, @Param("example") RichMediaExample example);

    int updateByPrimaryKeySelective(RichMedia record);

    int updateByPrimaryKey(RichMedia record);

	List<RichMedia> selectByExample(Page<RichMedia> page,
			RichMediaExample example);
}