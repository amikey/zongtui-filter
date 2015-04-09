/**
 * Project Name:filter
 * File Name:IFilter.java
 * Package Name:com.zongtui.filter
 * Date:2015-4-8下午3:04:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter;

/**
 * ClassName: IFilter <br/>
 * Function: 过滤器定义接口 <br/>
 * date: 2015-4-8 下午3:04:10 <br/>
 *
 * @author feng
 * @version 
 * @since JDK 1.7
 */
public interface IFilter {
	
	/**
	 * Similar:判断内容的相似性，为1表示已经存在，为0表示不存在，为浮点数则判断相似度的值. <br/>
	 *
	 * @author feng
	 * @param page
	 * @return
	 * @since JDK 1.7
	 */
	float Similar(Page page);






}
