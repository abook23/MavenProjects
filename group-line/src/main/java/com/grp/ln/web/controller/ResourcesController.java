package com.grp.ln.web.controller;

import com.grp.ln.web.dao.ResourcesMapper;
import com.grp.ln.web.model.Resources;
import com.grp.ln.web.model.ResourcesExample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by My on 2016/9/5.
 */
@Controller
@RequestMapping("/mobile/resources")
public class ResourcesController {

    @Resource
    ResourcesMapper resourcesMapper;

    /**
     * 查询这些文件是否存在
     * 秒传
     *
     * @param md5s 文件的MD5
     * @return 文件
     */
    @RequestMapping("/md5")
    @ResponseBody
    public List<Resources> md5(String[] md5s) {
        if (md5s == null)
            return null;
        List<String> list = Arrays.asList(md5s);
        ResourcesExample example = new ResourcesExample();
        example.createCriteria().andMd5In(list);
        List<Resources> resources = resourcesMapper.selectByExample(example);
        return resources;
    }

    /**
     * 查询
     *
     * @param resId
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public Resources query(String resId) {
        return resourcesMapper.selectByPrimaryKey(resId);
    }
}
