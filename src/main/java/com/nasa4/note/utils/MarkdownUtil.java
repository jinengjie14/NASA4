package com.nasa4.note.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.youbenzi.mdtool.tool.MDTool;

public class MarkdownUtil {
	
	/**
	 * 截取字段
	 * @param makdown
	 * @param num 截取多少个字符
	 * @param suffix 后缀
	 * @return
	 */
	public static String substring(String makdown, int num, String suffix) {
		if(StringUtils.isNoneBlank(makdown)) {
			String html = MDTool.markdown2Html(makdown);
			String htmlText = Jsoup.parseBodyFragment(html).text();
			if(htmlText.length() > num) {
				htmlText = new StringBuffer(htmlText).substring(0, num) + suffix;
			}
			return htmlText;
		}
		return null;
	}
	
	public static String getFirstImage(String makdown) {
		if(StringUtils.isNoneBlank(makdown)) {
			String html = MDTool.markdown2Html(makdown);
			String image = null;
			Element element = Jsoup.parseBodyFragment(html).getElementsByTag("img").first();
			if(null != element) {
				image = element.attr("src");
			}
			return image;
		}
		return null;
	}

}
