package com.grp.ln.web.service;

import com.grp.ln.core.generic.BaseService;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserInfo;

public interface UserService extends BaseService<User, String> {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    RootNode login(User user);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    RootNode registered(User user);

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    RootNode updateUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    RootNode updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    RootNode getUserInfo(String userId);

    /**
     * 用户验证
     *
     * @param user
     * @return
     */
    User authentication(User user);

}
