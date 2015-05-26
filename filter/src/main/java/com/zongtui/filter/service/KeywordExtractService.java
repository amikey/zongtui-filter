/**
 * Project Name:filter
 * File Name:KeywordExtractService.java
 * Package Name:com.zongtui.filter
 * Date:2015-4-24下午8:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.service;

import java.util.List;

import com.zongtui.filter.domain.Keyword;
import com.zongtui.filter.exception.ExtractException;

/**
 * ClassName: KeywordExtractService <br/>
 * Function: 提取关键字接口定义 <br/>
 * date: 2015-4-24下午8:31:10 <br/>
 *
 * @author Ahaha
 * @since JDK 1.7
 */
public interface KeywordExtractService {

	/**
	 * 文本提取关键字
	 * @param content 文本内容
	 * @param algorithmType 算法类型
	 * @param extractNum 关键字数量
	 * @return 排序好的关键字列表
	 * @throws ExtractException
	 */
	public List<String> extractContent (String content, String algorithmType, String extractNum) throws ExtractException;

	
}
