package com.grp.ln.core.util;

public class RootNode {

	/**
	 * 请求状态 true false
	 */
	private boolean succeed;
	/**
	 * 描述
	 */
	private String msg;
	private int state;//-1 登录 0 默认

	/**
	 * 返回的参数实体
	 */
	private Object content;
	

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	

}
