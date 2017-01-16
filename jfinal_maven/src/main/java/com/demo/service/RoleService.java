package com.demo.service;

import com.demo.common.model.Role;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class RoleService {

    public static Page<Role> list(int pageNumber, int pageSize) {
        if (pageSize > 0)
            return Role.dao.paginate(pageNumber, pageSize, "select * ",
                    "from role order by createTime asc");
        else {
            List<Role> roles = Role.dao.find("select * from role order by createTime asc");
            return new Page<>(roles, -1, roles.size(), roles.size(), roles.size());
        }
    }

    public static List<Role> findByPId(String pId) {
        return Role.dao.find("select * from role where pId = ?", pId);
    }

    public static List<Role> findByMenuId(String menuId) {
        return Role.dao.find("select r.* from role r,role_menu rm where rm.menuId = ? and r.roleId = rm.roleId", menuId);
    }
}
