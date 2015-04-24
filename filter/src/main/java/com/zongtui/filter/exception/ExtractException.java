/**
 * Project Name:filter
 * File Name:ExtractException.java
 * Package Name:com.zongtui.filter
 * Date:2015-4-24下午8:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.exception;


/**
 * ClassName: ExtractException <br/>
 * Function: 异常类 <br/>
 * date: 2015-4-24下午8:31:10 <br/>
 *
 * @author Ahaha
 * @since JDK 1.7
 */
public class ExtractException extends Exception{

	private static final long serialVersionUID = 6986594690285479408L;

	public ExtractException(String exception){
		super(exception);
	}

	public ExtractException(Exception e){
		super(e);
	}

	public ExtractException(String exception, Throwable cause){
		super(exception, cause);
	}
	
}
