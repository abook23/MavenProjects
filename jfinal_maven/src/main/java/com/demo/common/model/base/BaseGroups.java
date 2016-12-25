package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseGroups<M extends BaseGroups<M>> extends Model<M> implements IBean {

	public void setGrpId(java.lang.String grpId) {
		set("grpId", grpId);
	}

	public java.lang.String getGrpId() {
		return get("grpId");
	}

	public void setUserId(java.lang.String userId) {
		set("userId", userId);
	}

	public java.lang.String getUserId() {
		return get("userId");
	}

	public void setParentId(java.lang.String parentId) {
		set("parentId", parentId);
	}

	public java.lang.String getParentId() {
		return get("parentId");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setDateTime(java.util.Date dateTime) {
		set("dateTime", dateTime);
	}

	public java.util.Date getDateTime() {
		return get("dateTime");
	}

	public void setFlag(java.lang.String flag) {
		set("flag", flag);
	}

	public java.lang.String getFlag() {
		return get("flag");
	}

}
