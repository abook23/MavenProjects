package com.grp.ln.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.grp.ln.web.model.*;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.stereotype.Service;

import com.grp.ln.core.generic.BaseDao;
import com.grp.ln.core.generic.BaseServiceImpl;
import com.grp.ln.core.util.IdUtils;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.core.util.properties;
import com.grp.ln.web.dao.GroupsMapper;
import com.grp.ln.web.dao.UserInfoMapper;
import com.grp.ln.web.dao.UserMapper;
import com.grp.ln.web.service.UserService;
import com.mysql.fabric.xmlrpc.base.Data;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
        UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private GroupsMapper groupsMapper;

    private RootNode root = new RootNode();

    @Override
    public BaseDao<User, String> getDao() {
        // TODO Auto-generated method stub
        return userMapper;
    }

    /**
     * 登录
     */
    @Override
    public RootNode login(User user) {
        // TODO Auto-generated method stub
        RootNode node = new RootNode();
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> users = userMapper.selectByExample(example);
        if (users == null || users.size() <= 0) {
            node.setSucceed(false);
            node.setMsg("用户名不存在");
            return node;
        }
        User _user = users.get(0);
        if (!_user.getPassword().equals(user.getPassword())) {
            node.setSucceed(false);
            node.setMsg("密码错误!");
        } else {
            node.setSucceed(true);
            node.setMsg("欢迎光临");
            node.setContent(_user.getUserInfo());
        }
        return node;
    }

    @Override
    public RootNode registered(User user) {
        // TODO Auto-generated method stub
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        int count = userMapper.countByExample(example);
        if (count != 0) {
            root.setSucceed(false);
            root.setMsg("用户名已存在");
            return root;
        }

        user.setUserId(IdUtils.getId());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setName(user.getUserName());
        userInfo.setDateTime(new Date());

        userMapper.insert(user);// 用户
        userInfoMapper.insert(userInfo);// 用户基础信息

        //默认分组
        String defaultGroup = properties.getInstance().getProperty(
                "default_group");
        String[] arr = defaultGroup.split(",");
        for (int i = 0; i < arr.length; i++) {
            Groups groups = new Groups();
            groups.setGrpId(IdUtils.getId());
            groups.setName(arr[i]);
            groups.setDateTime(new Date());
            groups.setParentId("0");
            groups.setUserId(user.getUserId());
            groupsMapper.insert(groups);
        }
        //添加默认分组end

        root.setMsg("注册成功");
        root.setSucceed(true);
        root.setContent(userInfo);

        return root;
    }

    @Override
    public RootNode updateUserInfo(UserInfo userInfo) {
        // TODO Auto-generated method stub

        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i > 0) {
            root.setSucceed(true);
            root.setMsg("更新成功");
            String id = userInfo.getUserId();
            userInfo = userInfoMapper.selectByPrimaryKey(id);
            root.setContent(userInfo);
        } else {
            root.setSucceed(false);
            root.setMsg("更新失败");
        }

        return root;
    }

    @Override
    public RootNode updateUser(User user) {
        int i = update(user);
        if (i > 0) {
            root.setSucceed(true);
            root.setMsg("更新成功");
        } else {
            root.setSucceed(false);
            root.setMsg("更新失败");
        }
        return root;
    }

    @Override
    public RootNode getUserInfo(String userId) {
        // TODO Auto-generated method stub
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        root.setSucceed(true);
        root.setMsg("获取成功");
        root.setContent(userInfo);
        return root;
    }

    @Override
    public User authentication(User user) {
        // TODO Auto-generated method stub
        UserInfo userInfo = new UserInfo();
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        example.createCriteria().andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0)
            return users.get(0);
        return null;
    }

}
