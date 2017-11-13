package com.nasa4.note.responsebean;

import com.nasa4.note.domain.User;

import lombok.Data;

@Data
public class LoginResponse {
	private int code;
	private int status;
	private String msg;
	private User data;
	private String error;
}
