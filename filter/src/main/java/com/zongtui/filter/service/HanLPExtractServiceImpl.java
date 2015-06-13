package com.zongtui.filter.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.zongtui.filter.exception.ExtractException;
import com.zongtui.filter.utils.JsnOperation;
import com.zongtui.filter.utils.MapValueComparator;


/**
 * 
* @ClassName: HanLPExtractServiceImpl
* @Description: 关键词提取
* @author yifan
* @date 2015年6月12日 下午2:05:48
*
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
	
	/**
	* @Title: getJSONKeyWordInfo
	* @Description: 获取指定数量的关键字以及关键词出现的次数，并封装成JSON的格式返回
	* @param @param content
	* @param @param extractNum
	* @param @return
	* @param @throws NumberFormatException
	* @param @throws IOException    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getJSONKeyWordInfo(String content,String extractNum) throws NumberFormatException, IOException{
		
		//先将文本中的换行符去掉
		content = content.replaceAll("\n", "");
		//List<Term> str = HanLP.segment(text);
		//与直接new一个分词器相比，使用本方法的好处是，以后HanLP升级了，总能用上最合适的分词器:当前使用的分词器：Viterbi分词器是目前效率和效果的最佳平衡
		List<Term> lists = HanLP.newSegment().seg(content);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		int count ;
		for (Term term : lists) {
			count = 1;
			//去除符号
			if(!term.nature.toString().startsWith("w")){
				//System.out.println(term.word+"--"+term.nature);
				if(map.containsKey(term.word)){
					count += map.get(term.word);
					map.put(term.word, count);
				}else{
					map.put(term.word, count);
				}
			}
		}
		MapValueComparator vc = new MapValueComparator(map);
		TreeMap<String, Integer> sortMapTmp = new TreeMap<String, Integer>(vc);
		TreeMap<String, Integer> sortMap = new TreeMap<String, Integer>(vc);
		//HashMap<String, Integer> sortMap = new HashMap<String, Integer>();
		sortMapTmp.putAll(map);
		
		System.out.println(sortMapTmp);
		
		Set<String> keys = sortMapTmp.keySet();
		int i=0;
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if(i<Integer.parseInt(extractNum)){
				sortMap.put(key, map.get(key));
				i++;
			}else{
				break;
			}
		}
		String str = JsnOperation.getInstance().toJSon(sortMap);
		
		return str;
	}
}
