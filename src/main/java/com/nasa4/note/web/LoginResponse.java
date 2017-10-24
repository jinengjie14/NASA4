package com.nasa4.note.web;

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
