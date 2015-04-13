package com.zongtui.filter.simhash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nlpcn.commons.lang.util.StringUtil;

/**
 * 语主信息的判断类
 * 
 * ClassName: RegexLanguageUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月12日 下午11:52:00 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public final class RegexLanguageUtil {
	/**
	 * 中文件信息的判断类
	 */
	static final String CHINESE_PATTERN = "[\u4e00-\u9fa5]";
	/**
	 * 日语字符集
	 */
	static final String JAPANESE_PATTERN = "[\u0800-\u4e00]";
	/**
	 * 英文字符集
	 */
	static final String ENGLISH_PATTERN = "[\u0020-\u007F]+";

	private RegexLanguageUtil() {
	}

	/**
	 * 单一实例
	 */
	public static final RegexLanguageUtil INSTANCE = new RegexLanguageUtil();

	/**
	 * 判断是否只包含英文相关字符
	 * 
	 * @param text
	 *            文本信息
	 * @return
	 */
	public boolean isEnglish(String text) {
		boolean result = false;
		if (StringUtil.isNotBlank(text)) {
			result = text.matches(ENGLISH_PATTERN);
		}
		return result;
	}

	/**
	 * 判断字符串中是否包含英文字符
	 * 
	 * @param text
	 *            文本信息
	 * @return
	 */
	public boolean isChinese(String text) {
		boolean result = false;
		if (StringUtil.isNotBlank(text)) {
			Pattern p = Pattern.compile(CHINESE_PATTERN);
			Matcher m = p.matcher(text);
			if (m.find()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 判断是否为日语
	 * 
	 * @param text
	 *            文本信息
	 * @return
	 */
	public boolean isJapanese(String text) {
		boolean result = false;
		if (StringUtil.isNotBlank(text)) {
			Pattern p = Pattern.compile(JAPANESE_PATTERN);
			Matcher m = p.matcher(text);
			if (m.find()) {
				result = true;
			}
		}
		return result;
	}
}