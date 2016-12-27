package com.grp.ln.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.core.generic.BaseServiceImpl;
import com.grp.ln.web.dao.RichMediaMapper;
import com.grp.ln.web.model.RichMedia;
import com.grp.ln.web.model.RichMediaExample;
import com.grp.ln.web.service.RichMediaService;

@Service
public class RichMediaServiceImpl extends BaseServiceImpl<RichMedia, String> implements RichMediaService {

    @Resource
    private RichMediaMapper richMediaMapper;

    @Override
    public BaseDao<RichMedia, String> getDao() {
        // TODO Auto-generated method stub
        return richMediaMapper;
    }

    @Override
    public List<RichMedia> selectList() {
        // TODO Auto-generated method stub
        RichMediaExample example = new RichMediaExample();
        //example.setOrderByClause(orderByClause);
        return richMediaMapper.selectByExample(example);
    }

    @Override
    public List<RichMedia> list(Page<RichMedia> page, String userId) {
        // TODO Auto-generated method stub
        RichMediaExample example = new RichMediaExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return richMediaMapper.selectByExample(page, example);
    }

}
