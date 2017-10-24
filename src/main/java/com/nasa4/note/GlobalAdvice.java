package com.nasa4.note;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nasa4.note.exception.LoginExecption;
import com.nasa4.note.exception.NotLoginExecption;
import com.nasa4.note.exception.ServiceException;
import com.nasa4.note.utils.AESenc;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalAdvice {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public Map<String, Object> validExceptionHandler(BindException ex) {
		final Locale locale = LocaleContextHolder.getLocale();
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); //获取所有BindException中的错误
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 2301071153729245822L;
			{ 
				for (FieldError error : fieldErrors) {// error.getField() 获取参数名， error.getDefaultMessage() 获取错误信息就是@NotNull(message="aaaaaaa")中的message
					String message = messageSource.getMessage(error.getDefaultMessage(), new Object[] { ex.getFieldValue(error.getField()) },
							locale);
					put(error.getField(), message);
				}
			}
		};
	}
	
	@ResponseBody
	@ExceptionHandler(NotLoginExecption.class)
	public HashMap<String, Object> handleNotLogin(Model model, NotLoginExecption ex) {
		HashMap<String, Object> map = new HashMap<>();
		final Locale locale = LocaleContextHolder.getLocale();
		final String message = messageSource.getMessage(ex.getMsg(), new Object[0], locale);
		
		String tourl = "/login";
		try {
			String reload = AESenc.encrypt(ex.getReload());
			tourl = "/login?reload=" + URLEncoder.encode(reload, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put(ex.getField(), message);
		map.put("tourl", tourl);
		log.info("url====================="+tourl);
		return map;
	}
	
	@ResponseBody
	@ExceptionHandler(LoginExecption.class)
	public Map<String, Object> handleLogin(LoginExecption ex) {
		final Locale locale = LocaleContextHolder.getLocale();
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = -9213307884944727965L;
			{
				@SuppressWarnings("unchecked")
				Map<String, String> errorMap = (Map<String, String>) JSON.parse(ex.getError());
				for(Map.Entry<String, String> error : errorMap.entrySet()){
					String message = messageSource.getMessage(error.getValue(), new Object[0], locale);
					log.error(message);
					put(error.getKey(), message);
				}
				
			}
		};
	}
	
	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	public Map<String, Object> businessExceptionHandler(ServiceException ex) {
		final Locale locale = LocaleContextHolder.getLocale();
		final String message = messageSource.getMessage(ex.getMsg(), new Object[0], locale);
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = -2147774529887359231L;
			{
				put(ex.getField(), message);
			}
		};
	}
}
