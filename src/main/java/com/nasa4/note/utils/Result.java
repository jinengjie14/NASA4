package com.nasa4.note.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Result {
	private String tourl;
	private int code;
	private String msg;
	
	public Result(String tourl, int code, String msg) {
		this.tourl = tourl;
		this.code = code;
		this.msg = msg;
	}
	
	public static Map<String, Object> toUrl(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tourl", url);
		return map;
	}
	
	public static Result reload(String reload, String msg) throws Exception {
		return new Result(AESenc.decrypt(reload), 200, msg);
	}
	
	public static Result success(String tourl, String msg) {
		return new Result(tourl, 200, msg);
	}
	
	public static Result successToReload(String msg) {
		return new Result("reload", 200, msg);
	}

}
