package com.nasa4.note.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -5369719525959803330L;
	
	private final String field;
	private final String msg;
	
	public ServiceException(String msg) {
		super(msg);
		this.field = "message";
		this.msg = msg;
	}
	
	public ServiceException(String field, String msg) {
		super(msg);
		this.field = field;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public String getMsg() {
		return msg;
	}

}
