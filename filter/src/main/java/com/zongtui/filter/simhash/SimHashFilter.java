package com.zongtui.filter.simhash;

import java.math.BigInteger;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;

/**
 * 根据SIMHASH算法对文档进行过滤
 * 
 * ClassName: SimHashFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月12日 下午11:41:57 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class SimHashFilter implements IFilter {
	@Override
	public float similar(Page page) {
		SimHash simHash = new SimHash(page.getContent());
		BigInteger hash = simHash.simHash();

		float resultNum = 0;
		if (SimHashChecker.INSTANCE.checkSimilar(hash)) {
			resultNum = 1;
		}
		return resultNum;
	}
}