package com.htrj.core.model;

public  class Msg {
	private String msg;
	private String success;
	public Msg(String msg,String success){
		this.msg=msg;
		this.success=success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
}
