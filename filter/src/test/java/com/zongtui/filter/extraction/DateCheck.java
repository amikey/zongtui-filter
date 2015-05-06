/**
 * Project Name:filter
 * File Name:DateCheck.java
 * Package Name:com.zongtui.filter.extraction
 * Date:2015年5月6日下午10:40:56
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.extraction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: DateCheck <br/>
 * Function: datecheck. <br/>
 * date: 2015年5月6日 下午10:40:56 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class DateCheck {
	public static void main(String[] args) {
		String checkValue = "2007-02-29";
		// String eL =
		// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(checkValue);
		boolean b = m.matches();
		if (b) {
			System.out.println("格式正确");
		} else {
			System.out.println("格式错误");
		}

	}
}
