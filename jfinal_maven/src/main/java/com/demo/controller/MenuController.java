package com.demo.controller;

import java.util.List;

import com.demo.common.model.Menu;
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


	public void mainPage(){
		render("main.html");
	}

	public void list() {
		User user = getSessionUer();
		List<Menu> menus = MenuService.list(user.getUserId());
		renderJson(menus);
	}

	public void listAll(){
		DataTable dt = getDataTable();
		Page<Menu> menuPage = MenuService.listPage(dt.getPageNumber(),dt.getPageSize(),dt.getSearchValue());
		dt.setData(menuPage);
		renderJson(dt.getResponseData());
	}
	
	public void add() {
		Menu menu = getModel(Menu.class);
		menu.setMenuId(IdUtils.getId());
		menu.save();
	}
	
	public void setUserMenu() {
		User user = getSessionUer();
		UserMenu userMenu = getModel(UserMenu.class);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
