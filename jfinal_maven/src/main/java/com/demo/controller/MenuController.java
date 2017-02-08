package com.demo.controller;

import java.util.List;

import com.demo.common.model.Menu;
import com.demo.common.model.RoleMenu;
import com.demo.common.model.User;
import com.demo.common.model.UserMenu;
import com.demo.controller.base.BaseController;
import com.demo.controller.model.DataTable;
import com.demo.interceptor.BaseInterceptor;
import com.demo.service.MenuService;
import com.demo.util.IdUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

@Before({BaseInterceptor.class})
public class MenuController extends BaseController {


    public void main() {
        render("main.html");
    }

    public void list() {
        User user = getSessionUer();
        if ("system".equals(user.getUserName())) {
            List<Menu> menus = MenuService.list(null);
            renderJson(menus);
        }else {
            List<Menu> menus = MenuService.list(user.getUserId());
            renderJson(menus);
        }
    }

    public void list_all() {
        User user = getSessionUer();
        List<Menu> menus = MenuService.list(null);
        renderJson(menus);
    }

    public void listAll() {
        DataTable dt = getDataTable();
        Page<Menu> menuPage = MenuService.listPage(dt.getPageNumber(), dt.getPageSize(), dt.getSearchValue());
        dt.setData(menuPage);
        renderJson(dt.getResponseData());
    }

    public void add() {
        Menu menu = getModel(Menu.class);
        menu.setMenuId(IdUtils.getId());
        boolean b = menu.save();
        renderJson(b);
    }

    public void setUserMenu() {
        User user = getSessionUer();
        UserMenu userMenu = getModel(UserMenu.class);
    }

    public void setRoleMenu() {
        RoleMenu roleMenu = getModel(RoleMenu.class);
        roleMenu.setId(IdUtils.getId());
        boolean b= roleMenu.save();
        renderJson(b);
    }

    public void delRoleMenu() {
        RoleMenu roleMenu = getModel(RoleMenu.class);
        roleMenu = roleMenu.findFirst("select * from role_menu where roleId=? and menuId=?",roleMenu.getRoleId(), roleMenu.getMenuId());
        boolean b= roleMenu.delete();
        renderJson(b);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        Menu menu = getModel(Menu.class,"");
        boolean b = menu.update();
        renderJson(b);
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        Menu menu = getModel(Menu.class,"");
        boolean b = menu.delete();
        renderJson(b);
    }

}
