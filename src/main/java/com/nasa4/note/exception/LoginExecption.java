package com.nasa4.note.exception;

public class LoginExecption extends RuntimeException {
	private static final long serialVersionUID = 5919039753180000998L;
	
	private final String msg;
	private final String error;

	public LoginExecption(String msg, String error) {
		this.msg = msg;
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public String getError() {
		return error;
	}
	
}
