package com.nasa4.note.service;

import com.nasa4.note.web.validform.UserForm;

public interface UserService {
	
	public void login(UserForm userForm) throws Exception;
	
	public void register(UserForm userForm);

}
