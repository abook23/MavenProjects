package com.demo.service;

import java.util.List;

import javax.management.relation.Role;

import com.demo.common.model.Menu;
import com.demo.common.model.UserMenu;
import com.jfinal.plugin.activerecord.Page;

public class MenuService {
    public static List<Menu> list(String userId) {
        String sql;
        if (userId != null) {
            sql = "SELECT m.* from user_role ur,role_menu rm,menu m where ur.userId = '"+userId+"' AND ur.roleId = rm.roleId and rm.menuId = m.menuId and m.status='1' order by createTime asc";
        } else {
            sql = "select * from menu order by createTime asc";
        }
        return Menu.dao.find(sql);
    }

    public static Page<Menu> listPage(int pageMumber, int pageSize, String selectStr) {
        String sqlEx = "from menu WHERE name LIKE '%" + selectStr + "%' order by createTime asc";
        return Menu.dao.paginate(pageMumber, pageSize, "select *", sqlEx);
    }
}
