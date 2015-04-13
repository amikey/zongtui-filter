package com.zongtui.filter.simhash;

import org.nlpcn.commons.lang.finger.FingerprintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;

/**
 * 根据SIMHASH的值对结果进行去重
 * 
 * ClassName: SimHashFilter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月12日 下午6:51:27 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class AnsjFingerPrintFilter implements IFilter {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(IFilter.class);
	/**
	 * 日志信息存储
	 */
	private static final BerkeleyDataStore bds = new BerkeleyDataStore();

	static {
		bds.init("FingerPrint");
	}

	public AnsjFingerPrintFilter() {
	}

	@Override
	public float similar(Page page) {
		String fingerprint = new FingerprintService().fingerprint(page
				.getContent());
		logger.info("生成的指纹信息为：{}", fingerprint);
		// 判断指纹信息是否存在
		float resultNum = 0;
		boolean result = FingerPrintChecker.INSTANCE.checkExist(fingerprint);
		if (result) {
			resultNum = 1;
		}
		return resultNum;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}