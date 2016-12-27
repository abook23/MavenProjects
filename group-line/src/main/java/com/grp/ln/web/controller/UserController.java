package com.grp.ln.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.Constant;
import com.grp.ln.web.model.User;
import com.grp.ln.web.model.UserInfo;
import com.grp.ln.web.service.UserService;

@Controller
@RequestMapping(value = "/mobile/user")
public class UserController {

    @Resource
    private UserService userService;
    private RootNode mRootNode = new RootNode();

    /**
     * 重新登录
     *
     * @return
     */
    @RequestMapping(value = "/tologin")
    @ResponseBody
    public RootNode toLogin() {
        RootNode rootNode = new RootNode();
        rootNode.setMsg("请登录!");
        rootNode.setState(-1);
        rootNode.setSucceed(false);
        return rootNode;
    }

    /**
     * 注册
     *
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public RootNode register(@Valid User user, HttpServletRequest request) {

        // user.setPassWrod(ApplicationUtils.md5Hex(user.getPassWrod()));
        RootNode nRootNode = userService.registered(user);

        return nRootNode;
    }

    /**
     * 登录
     *
     * @param user
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public RootNode login(@Valid User user, BindingResult result, Model model,
                          HttpServletRequest request) {

        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                // 已经登录了
            }
            if (result.hasErrors()) {
                // 输入参数错误
                model.addAttribute("error", "输入参数错误");
            }
            // 验证信息完整性
            subject.login(new UsernamePasswordToken(user.getUserName(), user
                    .getPassword()));
            // 等了判断
            RootNode rootNode = userService.login(user);
            UserInfo userInfo = (UserInfo) rootNode.getContent();
            request.getSession().setAttribute(Constant.USER_INFO, userInfo);
            return rootNode;
        } catch (AuthenticationException e) {
            // TODO: handle exception
            // String msg = e.getMessage();
            mRootNode.setMsg("用户名或者密码错误");
        }
        return mRootNode;
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public RootNode logout(HttpSession session) {
        session.removeAttribute(Constant.USER_INFO);
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        RootNode root = new RootNode();
        root.setSucceed(true);
        root.setMsg("退出成功");
        return root;
    }

    /**
     * 更新用户信息 包括 密码 用户信息,头像
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/userinfo_update")
    @ResponseBody
    public RootNode updateUserInfo(UserInfo userInfo, HttpServletRequest request) {
        UserInfo _userInfo = (UserInfo) request.getSession().getAttribute(Constant.USER_INFO);
        userInfo.setUserId(_userInfo.getUserId());
        return userService.updateUserInfo(userInfo);
    }

    /**
     * 更新用户信息 包括 密码 用户信息,头像
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user_update")
    @ResponseBody
    public RootNode updateUser(User user) {
        return userService.updateUser(user);
    }

    /**
     * 获取用户信息
     * 传用户id uId
     *
     * @return
     */
    @RequestMapping(value = "/userinfo_get", method = RequestMethod.GET)
    @ResponseBody
    public RootNode getUserInfo(String userId, HttpServletRequest request) {
        if (userId == null) {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USER_INFO);
            userId = userInfo.getUserId();
        }
        return userService.getUserInfo(userId);
    }

}
