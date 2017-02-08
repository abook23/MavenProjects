package com.demo.controller;

import com.demo.Key;
import com.demo.common.model.MsgBean;
import com.demo.common.model.User;
import com.demo.common.model.UserInfo;
import com.demo.common.model.UserRole;
import com.demo.controller.base.BaseController;
import com.demo.controller.model.DataTable;
import com.demo.interceptor.BaseInterceptor;
import com.demo.service.UserService;
import com.demo.util.IdUtils;
import com.demo.util.UserAgentUtil;
import com.demo.util.useragent.UserAgent;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends BaseController {

    public void add() {
        render("add.html");
    }

    public void main() {
        render("main.html");
    }

    public void login() {
//        String userAgentStr = getRequest().getHeader("user-agent");
//        UserAgent userAgent = UserAgentUtil.getUserAgent(userAgentStr);
        MsgBean msgBean = new MsgBean();
        User user = getModel(User.class, "");
        user = UserService.login(user.getUserName(), user.getPassword());
        if (user != null) {
            user.setPassword("******");
            setSessionAttr(Key.KEY_SESSION_USER, user);
            msgBean.setStatus(1);
            msgBean.setMsg("欢迎" + user.getUserName());
        } else {
            msgBean.setStatus(0);
            msgBean.setMsg("用户名或者密码错误");
        }
        renderJson(msgBean);
    }

    public void getQrcode() throws UnknownHostException {

        String code = IdUtils.getId();
        long time = new Date().getTime();
        setSessionAttr(Key.KEY_QRCODE, code);

        InetAddress inetAddress = InetAddress.getLocalHost();
        HttpServletRequest request= getRequest();
        String url =  request.getScheme()+"://"+inetAddress.getHostAddress()+":"+request.getServerPort()+"/app/jfinaldemo.apk";

        Map<String,Object> map= new HashMap<>();
        map.put("code",code);
        map.put("url",url);
        map.put("time",time);
        renderJson(map);
    }

    public void qrcodeLoading() throws Exception {
        String code = getPara("code");
        MsgBean msgBean = new MsgBean();
        int count = 20;
        while (count > 0) {
            String code_session = getSessionAttr(Key.KEY_QRCODE);
            if (code.equals(code_session)) {
                User user = UserService.qrcode(code);
                if (user != null) {
                    user.setPassword("******");
                    setSessionAttr(Key.KEY_SESSION_USER, user);
                    msgBean.setStatus(1);
                    msgBean.setMsg("欢迎" + user.getUserName());
                    break;
                }
                Thread.sleep(3000);
                count--;
            } else {
                break;
            }
        }
        renderJson(msgBean);
    }

    public void qrcodeLogin() throws Exception {
        String code = getPara("code");
        MsgBean msgBean = new MsgBean();
        if (code != null && code.length() > 0) {
            User user = getSessionUer();
            if (user != null) {
                User _user = new User();
                _user.setUserId(user.getUserId());
                _user.setQrcode(code);
                _user.update();
                msgBean.setStatus(1);
                msgBean.setMsg("授权成功");
            } else {
                msgBean.setStatus(0);
                msgBean.setMsg("授权失败");//生成的二维码已经 超时
            }
        }
        renderJson(msgBean);
    }

    @Before({BaseInterceptor.class})
    public void list() {
        // int page = getAttrForInt("page");
        // int pageSize = getAttrForInt("pageSize");
        User user = getSessionUer();
        String userName = user.getUserName();
        if ("admin".equals(userName) || "system".equals(userName)) {
            Page<Record> userInfoPage = UserService.list(1, 10);
            setAttr("userInfoPage", userInfoPage);
        }
        render("list.html");
    }

    public void list3() {
        Page<Record> userInfoPage = UserService.list(1, 10);
        renderJson(userInfoPage);
    }

    public void list2() {
        DataTable databTable = getDataTable();
        String search = databTable.getSearchValue();
        User user = getSessionUer();
        String userName = user.getUserName();
        if ("admin".equals(userName) || "system".equals(userName)) {

            int pageNumber = databTable.getStart() / databTable.getLength() + 1;
            int pageSize = databTable.getLength();
            if (search == null || search.length() == 0) {
                Page<Record> page = UserService.list(pageNumber, pageSize);
                databTable.setData(page.getList());
                databTable.setRecordsTotal(page.getTotalRow());
            } else {
                Page<Record> page = UserService.listLike(pageNumber, pageSize, search);
                databTable.setData(page.getList());
                databTable.setRecordsTotal(page.getTotalRow());
            }
            renderJson(databTable.getResponseData());
        } else {
            renderJson();
        }

    }

    public void checkUserName() {

    }

    @Before({BaseInterceptor.class})
    public void edit() {

        MsgBean msgBean = new MsgBean();

        User user = getModel(User.class);
        UserInfo userInfo = getModel(UserInfo.class);

        if ("******".equals(user.getPassword())) {
            user.setPassword(null);
        }

        if (user.getUserId() == null) {
            user.setUserId(IdUtils.getId());
            user.setUserName(userInfo.getName());

            userInfo.setUserId(user.getUserId());
            boolean b = user.save();
            boolean b2 = userInfo.save();
            if (b && b2) {
                msgBean.setStatus(1);
                msgBean.setMsg("新增成功");
            } else {
                user.delete();
                userInfo.delete();

                msgBean.setStatus(0);
                msgBean.setMsg("失败成功");
            }
        } else {

            /**
             * system 可以修改所以用户的密码 普通用户只能修改本用户的密码
             */
            if ("system".equals(getSessionUer().getUserName())
                    || getSessionUer().getUserId().equals(user.getUserId())) {
                user.update();
                userInfo.setUserId(user.getUserId());
                boolean b = userInfo.update();
                if (b) {
                    msgBean.setStatus(1);
                    msgBean.setMsg("更新成功");
                } else {
                    msgBean.setStatus(0);
                    msgBean.setMsg("更新失败");
                }
            } else {
                msgBean.setStatus(0);
                msgBean.setMsg("权限不足,不能修改他人信息");
            }
        }
        renderJson(msgBean);
    }

    @Before({BaseInterceptor.class})
    public void info() {
        String id = getPara("id");
        int type = getPara("type") == null ? 0 : getParaToInt("type");
        UserInfo userInfo = UserInfo.dao.findById(id);
        User user = User.dao.findById(id);
        user.setPassword("******");
        setAttr("userInfo", userInfo);
        setAttr("user", user);
        if (type == 0) {
            render("info.jsp");
        } else {
            render("add.html");
        }
    }

    @Before({BaseInterceptor.class})
    public void delete() {
        MsgBean msgBean = new MsgBean();
        String id = getPara("id");
        boolean b = UserInfo.dao.deleteById(id);
        if (b) {
            msgBean.setStatus(1);
            msgBean.setMsg("删除成功");
        } else {
            msgBean.setStatus(0);
            msgBean.setMsg("删除失败");
        }
        renderJson(msgBean);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void userRoleAdd() {
        String roleId = getPara("roleId");
        String userId = getPara("userId");
        UserRole userRole = new UserRole();
        userRole.setId(IdUtils.getId());
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRole.save();
        renderJson(1);
    }

    public void userRoleDel() {
        String roleId = getPara("roleId");
        String userId = getPara("userId");
        UserRole userRole = UserRole.dao.findFirst("select id from user_role where userId=? and roleId=?", userId, roleId);
        userRole.delete();
        renderJson(1);
    }

    public void findByRoleId() {
        String roleId = getPara("roleId");
        List<UserInfo> userInfos = UserService.findByRoleId(roleId);
        renderJson(userInfos);
    }

    public void findByRoleIdPage() {
        String roleId = getPara("roleId");
        DataTable dt = getDataTable();
        Page<UserInfo> userInfos = UserService.findByRoleId(roleId, dt.getPageNumber(), dt.getPageSize(), dt.getSearchValue());
        dt.setData(userInfos);
        renderJson(dt.getResponseData());
    }

    public void findByRoleIdPage2() {
        String roleId = getPara("roleId");
        DataTable dt = getDataTable();
        Page<Record> userInfos = UserService.findByRoleId2(roleId, dt.getStart(), dt.getPageNumber(), dt.getPageSize(), dt.getSearchValue());
        dt.setData(userInfos);
        renderJson(dt.getResponseData());
    }

    public void findByMenuId() {
        String menuId = getPara("menuId");
        DataTable d = getDataTable();
        Page<UserInfo> userInfos = UserService.findByMenuId(menuId, d.getPageNumber(), d.getPageSize(), d.getSearchValue());
        d.setData(userInfos);
        renderJson(d.getResponseData());
    }
}
