package com.demo.service;

import com.demo.common.model.User;
import com.demo.common.model.UserInfo;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class UserService {

    public static User login(String userName, String password) {
        String sql = "select * from user where userName = '" + userName
                + "' and password = '" + password + "'";
        return User.dao.findFirst(sql);
    }

    public static Page<UserInfo> list(int pageNumber, int pageSize) {
        return UserInfo.dao.paginate(pageNumber, pageSize, "select * ",
                "from user_info order by createTime asc");
    }

    public static Page<UserInfo> listLike(int pageNumber, int pageSize,
                                          String value) {
        return UserInfo.dao.paginate(pageNumber, pageSize, "select * ",
                "FROM user_info WHERE concat(name,',',phone,',',email,',',qq) LIKE '%"
                        + value + "%' order by createTime asc");
    }

    public static List<UserInfo> findByRoleId(String roleId) {
        return UserInfo.dao.find("select u.* from user_info u,user_role ur where ur.roleId = ? and u.userId = ur.userId", roleId);
    }

    public static Page<UserInfo> findByMenuId(String menuId, int pageNumber, int pageSize, String selectValue) {
        String sqlEx = "from user_info u,user_menu um where um.menuId = ? and u.userId = um.userId";
        if (selectValue != null && selectValue.length() > 0) {
            sqlEx += " and concat(name,',',phone,',',email) like '%" + selectValue + "%'";
        }
        sqlEx += " order by createTime asc";
        return UserInfo.dao.paginate(pageNumber, pageSize, "select * ", sqlEx, menuId);
    }


}
