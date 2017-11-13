package com.nasa4.note.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nasa4.note.service.UserService;
import com.nasa4.note.utils.AESenc;
import com.nasa4.note.utils.Result;
import com.nasa4.note.web.validform.UserForm;
import com.nasa4.note.web.validform.UserForm.LoginForm;
import com.nasa4.note.web.validform.UserForm.RegisterForm;

@Controller
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(defaultValue="") String reload) {
		model.addAttribute("reload", reload);
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Result login(HttpSession session, @Validated(value= {LoginForm.class}) UserForm userForm, @RequestParam(defaultValue="") String reload) throws Exception {
		userService.login(userForm);
		if(StringUtils.isNotBlank(reload)) {
			return new Result(AESenc.decrypt(reload), 200, "登录成功");
		} else {
			return new Result("/", 200, "登录成功");
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model, @RequestParam(defaultValue="/") String reload) {
		session.removeAttribute("userId");
		session.removeAttribute("user");
		return "redirect:"+reload;
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public Result register(@Validated(value= {RegisterForm.class}) UserForm userForm) {
		userService.register(userForm);
		return new Result("/login", 200, "注册成功");
	}
	
	
	@GetMapping("/forget")
	public String forget() {
		return "forget";
	}

}
