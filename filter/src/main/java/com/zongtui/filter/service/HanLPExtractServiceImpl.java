package com.zongtui.filter.service;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.zongtui.filter.domain.Keyword;
import com.zongtui.filter.exception.ExtractException;

/**
 * ClassName: HanLPExtractServiceImpl <br/>
 * Function: HanLP关键词抽取 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Dean
 * @since JDK 1.7
 */
public class HanLPExtractServiceImpl implements KeywordExtractService{

	public List<String> extractContent(String content, String algorithmType,
			String extractNum) throws ExtractException {
		List<String> keywordList = null;
		try {
			keywordList = HanLP.extractKeyword(content, Integer.parseInt(extractNum));
		} catch (NumberFormatException e) {
			throw new ExtractException("extractNum format exception");
		}
		return keywordList;
	}

}
