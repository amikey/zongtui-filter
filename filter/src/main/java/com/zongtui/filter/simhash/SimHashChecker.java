package com.zongtui.filter.simhash;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Lists;

/**
 * SimHash信息的检查器，判断是否有相同的SimHash信息存在
 * 
 * ClassName: SimHashChecker <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月13日 下午11:17:07 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class SimHashChecker {
	/**
	 * Hash的上下文对象
	 */
	private ConcurrentHashMap<BigInteger, List<BigInteger>> context = new ConcurrentHashMap<BigInteger, List<BigInteger>>();
	/**
	 * 单一实例
	 */
	public static final SimHashChecker INSTANCE = new SimHashChecker();

	private SimHashChecker() {
	}

	public boolean checkSimilar(BigInteger s) {
		boolean resultFlag = false;

		Short key = s.shortValue();
		List<BigInteger> hashList = this.context.get(key);
		if (hashList == null) {
			hashList = Lists.newArrayList();
			hashList.add(s);
		} else {
			int distance = 0;
			for (BigInteger value : hashList) {
				while (value.signum() != 0) {
					distance += 1;
					value = value.and(value.subtract(new BigInteger("1")));
					if(distance==3){
						break;
					}
				}
				if (distance < 3) {
					resultFlag = true;
					break;
				}
			}
		}

		return resultFlag;
	}
}