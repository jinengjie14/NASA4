package com.nasa4.note.service;

import org.apache.commons.lang3.StringUtils;

import com.nasa4.note.exception.NotLoginExecption;
import com.nasa4.note.exception.ServiceException;

public class BaseService {

	public void checkLogin(String sessionUserId, String reload) {
		if (!StringUtils.isNotBlank(sessionUserId))
			throw new NotLoginExecption("user.not.login", reload);
	}
	
	public void checkOwn(String sessionUserId, String userId) {
		if(!StringUtils.equals(sessionUserId, userId))
			throw new ServiceException("service", "is.not.own");
	}
}
