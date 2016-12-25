package com.demo.controller;

import com.demo.common.model.MsgBean;
import com.demo.common.model.Role;
import com.demo.common.model.UserInfo;
import com.demo.controller.base.BaseController;
import com.demo.controller.model.DataTable;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import com.demo.util.IdUtils;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class RoleController extends BaseController {

    public void main() {
        render("main.html");
    }

    public void addPage() {
        render("add.html");
    }

    @Override
    public void add() {
        // TODO Auto-generated method stub
        MsgBean msgBean = new MsgBean();
        Role role = getModel(Role.class);
        role.setRoleId(IdUtils.getId());
        role.setStatus(1);
        if (role.save()) {
            msgBean.setStatus(1);
            msgBean.setMsg("添加成功");
        } else {
            msgBean.setMsg("添加失败");
        }
        renderJson(msgBean);
    }


    @Override
    public void list() {
        // TODO Auto-generated method stub
        DataTable dt = getDataTable();
        Page<Role> page = RoleService.list(dt.getPageNumber(), dt.getPageSize());
        dt.setData(page);
        renderJson(dt.getResponseData());
    }


    public void listAll() {
        Page<Role> page = RoleService.list(0, -1);
        renderJson(page.getList());
    }

    public void info(){
        String roleId = getPara("roleId");
        Role role = Role.dao.findById(roleId);
        List<Role> roles = RoleService.findByPId(role.getRoleId());
        List<UserInfo> userInfos = UserService.findByRoleId(roleId);
        setAttr("role",role);
        setAttr("roles",roles);
        setAttr("userInfos",userInfos);
        render("info.jsp");
    }

    public void findRoleByPid(){
        String pId = getPara("pId");
        List<Role> roles = RoleService.findByPId(pId);
        renderJson(roles);
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
