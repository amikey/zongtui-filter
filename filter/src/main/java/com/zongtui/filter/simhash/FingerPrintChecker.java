package com.zongtui.filter.simhash;

import com.google.common.base.Strings;

/**
 * 检查指纹信息是否已经存在
 * 
 * ClassName: FingerPrintChecker <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月13日 下午11:34:02 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class FingerPrintChecker {
	/**
	 * 单一实例
	 */
	public static final FingerPrintChecker INSTANCE = new FingerPrintChecker();
	/**
	 * Berkeley的数据存储
	 */
	private BerkeleyDataStore dbs = new BerkeleyDataStore();
	/**
	 * 数据库名称
	 */
	private String dbName = "FingerPrint";

	/**
	 * Creates a new instance of FingerPrintChecker.
	 *
	 */
	private FingerPrintChecker() {
		this.dbs.init(dbName);
	}

	/**
	 * 
	 * checkExist:(检查指纹信息是否存在). <br/>
	 *
	 * @author Administrator
	 * @param fingerPrint
	 * @return
	 * @since JDK 1.7
	 */
	public boolean checkExist(String fingerPrint) {
		boolean resultFlag = false;
		String result = this.dbs.getFromStore(dbName, fingerPrint);
		if (Strings.isNullOrEmpty(result)) {
			this.dbs.putToStore(dbName, fingerPrint,
					String.valueOf(System.currentTimeMillis()));
		} else {
			resultFlag = true;
		}

		return resultFlag;
	}

	@Override
	protected void finalize() throws Throwable {
		this.dbs.closeConnection();
		super.finalize();
	}
}