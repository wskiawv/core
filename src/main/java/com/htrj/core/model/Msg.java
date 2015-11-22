package com.htrj.core.model;

public  class Msg {
	private String msg;
	private boolean success;
	private Object object;
	public Msg(String msg,boolean success){
		this.msg=msg;
		this.success=success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
