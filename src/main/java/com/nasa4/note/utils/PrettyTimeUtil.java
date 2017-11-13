package com.nasa4.note.utils;

import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

public class PrettyTimeUtil {
	
	private final static String LOCALE_CHINESE = "zh_cn";
	
	/**
	 * 将时间转换为 几分钟前 几小时前 等等
	 * @param date 时间
	 * @return 转换后的时间去除空格
	 */
	public static String format(Date date) {
        PrettyTime p = new PrettyTime(new Locale(LOCALE_CHINESE));
       return p.format(date).replaceAll("\\s*", "");
    }

}
