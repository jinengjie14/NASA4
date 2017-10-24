package com.nasa4.note.exception;

public class NotLoginExecption extends ServiceException {
	private static final long serialVersionUID = 7244094746988776566L;
	
	private String msg;
	private String reload;

	public NotLoginExecption(String msg, String reload) {
		super(msg);
		this.msg = msg;
		this.reload = reload;
	}
	
	public String getMsg() {
		return msg;
	}

	public String getReload() {
		return reload;
	}

}