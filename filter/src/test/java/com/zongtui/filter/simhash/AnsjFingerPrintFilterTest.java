package com.zongtui.filter.simhash;

import org.junit.Before;
import org.junit.Test;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import com.zongtui.filter.simhash.AnsjFingerPrintFilter;

/**
 * 测试ANSJ的指纹去重功能
 * 
 * ClassName: AnsjFingerPrintFilterTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月12日 下午11:31:34 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 */
public class AnsjFingerPrintFilterTest {
	private IFilter ansjFilter = null;

	@Before
	public void setUp() {
		this.ansjFilter = new AnsjFingerPrintFilter();
	}

	@Test
	public void filter() {
		String s = "你妈妈喊你回家吃饭哦，回家罗回家罗";
		Page page = new Page(null, s);
		this.ansjFilter.similar(page);

		s = "你妈妈叫你回家吃饭啦，回家罗回家罗";
		page = new Page(null, s);
		this.ansjFilter.similar(page);
	}
}