package com.nasa4.note.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.nasa4.note.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {
	@Autowired
	private HttpServletRequest httpRequest;

	protected String sessionUserId = "";
	protected User sessionUser;
	
	protected String keyword;
	protected String reload;
	protected int pageSize;
	protected int page;
	
	@ModelAttribute
	public void getSessionUser(HttpServletRequest request, HttpSession session, Model model) {
		sessionUserId = (String) session.getAttribute("userId");
		sessionUser = (User) session.getAttribute("user");
		log.info(sessionUserId);
		
		if (StringUtils.isNotBlank(sessionUserId)) {
			model.addAttribute("sessionUser", sessionUser);
		}
	}
	
	@ModelAttribute
	public void params_init(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
			@RequestParam(value = "reload", required = false) String reload) {
		this.keyword = keyword;
		this.pageSize = pageSize;
		this.page = page;
		this.reload = reload;
	}
	
	public void getUrl(Model model) {
		String url = httpRequest.getRequestURL().toString();
		if (StringUtils.isNotBlank(httpRequest.getQueryString()))
			url += "?" + httpRequest.getQueryString();
		log.info("url====================="+url);
		model.addAttribute("url", url);
	}
}