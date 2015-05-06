/**
 * Project Name:filter
 * File Name:QQInfo.java
 * Package Name:com.zongtui.filter.extraction
 * Date:2015年5月6日下午10:20:37
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.extraction;

import java.util.Date;

/**
 * ClassName: QQInfo <br/>
 * Function: QQ信息. <br/>
 * date: 2015年5月6日 下午10:20:37 <br/>
 *
 * @author cloudsky
 * @version
 * @since JDK 1.7
 */
public class QQInfo {

	private Date sendTime;
	private String sendName;
	private String sendNum;
	private String sendContent;

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendNum() {
		return sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

}
