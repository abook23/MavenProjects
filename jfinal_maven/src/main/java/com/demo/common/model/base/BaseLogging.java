package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLogging<M extends BaseLogging<M>> extends Model<M> implements IBean {

	public void setLogId(java.lang.String logId) {
		set("logId", logId);
	}

	public java.lang.String getLogId() {
		return get("logId");
	}

	public void setUserId(java.lang.String userId) {
		set("userId", userId);
	}

	public java.lang.String getUserId() {
		return get("userId");
	}

	public void setDateTime(java.util.Date dateTime) {
		set("dateTime", dateTime);
	}

	public java.util.Date getDateTime() {
		return get("dateTime");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}

	public java.lang.String getIp() {
		return get("ip");
	}

	public void setAddr(java.lang.String addr) {
		set("addr", addr);
	}

	public java.lang.String getAddr() {
		return get("addr");
	}

}
