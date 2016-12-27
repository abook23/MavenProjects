package com.grp.ln.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.generic.BaseService;
import com.grp.ln.web.model.RichMedia;

public interface RichMediaService extends BaseService<RichMedia, String> {

	List<RichMedia> list(Page<RichMedia> page, String string);

}
