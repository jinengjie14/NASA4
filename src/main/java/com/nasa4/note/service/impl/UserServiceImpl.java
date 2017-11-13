package com.nasa4.note.service.impl;

import static com.nasa4.note.utils.HttpUtil.post;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.nasa4.note.dao.UserRepository;
import com.nasa4.note.domain.User;
import com.nasa4.note.exception.ServiceException;
import com.nasa4.note.responsebean.LoginResponse;
import com.nasa4.note.service.BaseService;
import com.nasa4.note.service.UserService;
import com.nasa4.note.utils.MD5Util;
import com.nasa4.note.web.validform.UserForm;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Response;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserRepository userRepository;
	
	public void login(UserForm userForm) throws Exception {
		String url = "http://10.1.65.33:81/login";
		FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("account",userForm.getAccount());//传递键值对参数
        formBody.add("password",userForm.getPassword());
		Response response = post(url, formBody);
		LoginResponse loginResponse = JSON.parseObject(response.body().string(), LoginResponse.class);
		
		log.info("code========"+loginResponse.getCode());
		if(loginResponse.getCode() == 200) {
			User user = userRepository.findOne(loginResponse.getData().getId());
			if(null == user) {
				loginResponse.getData().setPassword(MD5Util.GetMD5Code(userForm.getPassword()));
				userRepository.save(loginResponse.getData());
			} else {
				loginResponse.setData(user);
			}
			httpSession.setAttribute("userId", loginResponse.getData().getId());
			httpSession.setAttribute("user", loginResponse.getData());
		} else {
			User findAccount = userRepository.findByAccount(userForm.getAccount());
			if(null == findAccount) {
				throw new ServiceException("account", "user.account.notfind");
			} else {
				User user = userRepository.findByAccountAndPassword(userForm.getAccount(), MD5Util.GetMD5Code(userForm.getPassword()));
				if(null == user) {
					throw new ServiceException("password", "login.error");
				} else {
					httpSession.setAttribute("userId", user.getId());
					httpSession.setAttribute("user", user);
				}
			}
		}
	}
	
	public void register(UserForm userForm) {
		User findAccount = userRepository.findByAccount(userForm.getAccount());
		if(null == findAccount) {
			userForm.setPassword(MD5Util.GetMD5Code(userForm.getPassword()));
			User user = new User();
			BeanUtils.copyProperties(userForm, user, User.class);
			userRepository.save(user);
		} else {
			throw new ServiceException("apassword", "user.account.isin");
		}
	}

}
