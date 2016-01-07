package yjt.frameapp.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseBean implements Serializable{
	private boolean success=false;
	private int retCode=0;
	private String msg="";
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	
}
