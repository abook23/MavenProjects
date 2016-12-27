package com.grp.ln.web.dao;

import com.grp.ln.web.model.FkRichMediaMessage;
import com.grp.ln.web.model.FkRichMediaMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FkRichMediaMessageMapper {
    int countByExample(FkRichMediaMessageExample example);

    int deleteByExample(FkRichMediaMessageExample example);

    int insert(FkRichMediaMessage record);

    int insertSelective(FkRichMediaMessage record);

    List<FkRichMediaMessage> selectByExample(FkRichMediaMessageExample example);

    int updateByExampleSelective(@Param("record") FkRichMediaMessage record, @Param("example") FkRichMediaMessageExample example);

    int updateByExample(@Param("record") FkRichMediaMessage record, @Param("example") FkRichMediaMessageExample example);
}