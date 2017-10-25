package com.nasa4.note.web;

import static com.nasa4.note.utils.HttpUtil.post;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nasa4.note.exception.LoginExecption;
import com.nasa4.note.exception.ServiceException;
import com.nasa4.note.utils.AESenc;
import com.nasa4.note.utils.Result;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Response;

@Controller
@Slf4j
public class LoginController extends BaseController {
	
	@PostMapping("/login")
	@ResponseBody
	public Result login(HttpSession session, @RequestParam(defaultValue="") String account,
			@RequestParam(defaultValue="") String password,
			@RequestParam(defaultValue="") String reload) throws Exception {
		String url = "http://10.1.65.33:81/login";
		
		FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("account",account);//传递键值对参数
        formBody.add("password",password);
		Response response = post(url, formBody);
		LoginResponse loginResponse = JSON.parseObject(response.body().string(), LoginResponse.class);
		
		log.info("code========"+loginResponse.getCode());
		if(loginResponse.getCode() == 412) {
			if(StringUtils.isNoneBlank(loginResponse.getError())) {
				throw new LoginExecption(loginResponse.getMsg(), loginResponse.getError());
			} else {
				throw new ServiceException("password", "账号或密码错误");
			}
		} else if(loginResponse.getCode() == 200) {
			session.setAttribute("userId", loginResponse.getData().getId());
			session.setAttribute("user", loginResponse.getData());
		}
		else {
			throw new ServiceException("password", "账号或密码错误");
		}
		if(StringUtils.isNotBlank(reload)) {
			return new Result(AESenc.decrypt(reload), 200, "登录成功");
		} else {
			return new Result("/", 200, "登录成功");
		}
	}
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(defaultValue="") String reload) {
		model.addAttribute("reload", reload);
		return "login";
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
	
	@GetMapping("/forget")
	public String forget() {
		return "forget";
	}

}
