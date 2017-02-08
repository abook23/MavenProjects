package com.demo.service;

import com.demo.common.model.User;
import com.demo.common.model.UserInfo;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class UserService {

    public static User login(String userName, String password) {
        String sql = "select * from user where userName = '" + userName
                + "' and password = '" + password + "'";
        return User.dao.findFirst(sql);
    }

    public static User qrcode(String code){
        String sql = "select * from user where qrcode = ?";
        return User.dao.findFirst(sql,code);
    }

    public static boolean checkUserName(String userName) {
        Record record = Db.findFirst("select count(1) from user where userName= ?", userName);
        return record != null;
    }

    public static Page<Record> list(int pageNumber, int pageSize) {
        return Db.paginate(pageNumber, pageSize, "select ui.*,u.status ",
                "from user_info ui,user u where ui.userId = u.userId");
    }

    public static Page<Record> listLike(int pageNumber, int pageSize, String value) {
        return Db.paginate(pageNumber, pageSize, "select ui.*,u.status ",
                "from user_info ui,user u where ui.userId = u.userId and concat(name,',',phone,',',email,',',qq) like '%"
                        + value + "%' order by createTime asc");
    }

    public static List<UserInfo> findByRoleId(String roleId) {
        return UserInfo.dao.find("select u.* from user_info u,user_role ur where ur.roleId = ? and u.userId = ur.userId", roleId);
    }

    public static Page<UserInfo> findByRoleId(String roleId, int pageNumber, int pageSize, String selectValue) {
        //select ui.*,ur.roleId from user_info ui LEFT JOIN user_role ur ON ui.userId = ur.userId ORDER BY createTime,roleId DESC
        String sqlEx = "from user_info u,user_role ur where ur.roleId = ? and u.userId = ur.userId";
        if (selectValue != null && selectValue.length() > 0) {
            sqlEx += " and concat(name,',',phone,',',email) like '%" + selectValue + "%'";
        }
        return UserInfo.dao.paginate(pageNumber, pageSize, "select * ", sqlEx, roleId);
    }

    public static Page<Record> findByRoleId2(String roleId, int start, int pageNumber, int pageSize, String selectValue) {
        //select ui.*,ur.roleId from user_info ui LEFT JOIN (SELECT * FROM user_role where roleId = '1b446694ccc44b999192a059272ef7f9')ur ON ui.userId = ur.userId ORDER BY createTime,roleId DESC

        if (selectValue != null && selectValue.length() > 0) {
            String sqlEx = "from ( SELECT ui1.*,ur1.roleId from user_info ui1 LEFT JOIN user_role ur1 on ui1.userId = ur1.userId where  concat(name,',',phone,',',email) like '%" + selectValue + "%' ) tb";
            return Db.paginate(pageNumber, pageSize, "select * ", sqlEx);
        } else {
            String sqlEx = "from user_info ui LEFT JOIN (SELECT * FROM user_role where roleId = ? )ur ON ui.userId = ur.userId";
            if (selectValue != null && selectValue.length() > 0) {
                sqlEx += " and concat(name,',',phone,',',email) like '%" + selectValue + "%'";
            }
            return Db.paginate(pageNumber, pageSize, "select ui.*,ur.roleId", sqlEx, roleId);
        }
    }

    public static Page<Record> findByRoleId4(String roleId, int start, int pageNumber, int pageSize, String selectValue) {

        if (selectValue != null && selectValue.length() > 0) {
            String sqlEx = "from (SELECT ui1.*,ur1.roleId from user_info ui1 LEFT JOIN user_role ur1 on ui1.userId = ur1.userId where  concat(name,',',phone,',',email) like '%" + selectValue + "%' ) tb";
            Page<Record> page = Db.paginate(pageNumber, pageSize, "select * ", sqlEx);
            return page;
        } else {

            String sql =
                    " SELECT * from (SELECT ui1.*,ur1.roleId from user_info ui1 LEFT JOIN user_role ur1 on ui1.userId = ur1.userId where ur1.roleId = ? ORDER BY `name`) tb" +
                            " UNION" +
                            " SELECT ui2.*, null as roleId from user_info ui2 LEFT JOIN user_role ur2 on ui2.userId = ur2.userId where ISNULL(ur2.roleId)" +
                            " LIMIT ?,?;";

            List<Record> list = Db.find(sql, roleId, start, pageSize);

            String sqlCount = "select count(*) from user_info";
            if (selectValue != null && selectValue.length() > 0) {
                sqlCount += " where concat(name,',',phone,',',email) like '%" + selectValue + "%'";
            }
            Record record = Db.findFirst(sqlCount);
            int count = Integer.valueOf(record.getLong("count(*)").toString());

            return new Page<>(list, pageNumber, pageSize, count, count);
        }
    }

    public static Page<Record> findByRoleId3(String roleId, int start, int pageSize, String selectValue) {
//        //select ui.*,ur.roleId from user_info ui LEFT JOIN (SELECT * FROM user_role where roleId = '1b446694ccc44b999192a059272ef7f9')ur ON ui.userId = ur.userId ORDER BY createTime,roleId DESC
//        String sqlEx = "from user_info ui LEFT JOIN (SELECT * FROM user_role where roleId = ?)ur ON ui.userId = ur.userId";
//        if (selectValue != null && selectValue.length() > 0) {
//            sqlEx += " where concat(name,',',phone,',',email) like '%" + selectValue + "%'";
//        }
//      //  sqlEx += " order by roleId DESC";

        String sqlEx = "select *  from (select ui.*,ur.roleId from user_info ui  right JOIN user_role ur ON  ui.userId =ur.userId  where ur.roleId = ?";
        sqlEx += " UNION ALL";
        sqlEx += " select aa.*,bb.roleId from user_info aa  left JOIN user_role bb ON  aa.userId =bb.userId";
        if (selectValue != null && selectValue.length() > 0) {
            sqlEx += " where concat(name,',',phone,',',email) like '%" + selectValue + "%'";
        }
        sqlEx += " limit " + start + "," + pageSize + " ) as tb";

        String sqlCount = "select count(*) from user_info";
        if (selectValue != null && selectValue.length() > 0) {
            sqlCount += " where concat(name,',',phone,',',email) like '%" + selectValue + "%'";
        }

        List<Record> list = Db.find(sqlEx, roleId);
        Record record = Db.findFirst(sqlCount);
        int count = Integer.valueOf(record.getLong("count(*)").toString());
        return new Page<>(list, start, pageSize, count, count);

        //  return Db.paginate(pageNumber, pageSize, "select * ", sqlEx, roleId);
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
