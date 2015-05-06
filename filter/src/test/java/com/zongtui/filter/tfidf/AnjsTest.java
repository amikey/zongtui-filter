/**
 * Project Name:filter
 * File Name:AnjsTest.java
 * Package Name:com.zongtui.filter.tfidf
 * Date:2015年4月26日下午9:42:28
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.tfidf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.junit.Test;

/**
 * ClassName: AnjsTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月26日 下午9:42:28 <br/>
 * 
 * @author Ahaha
 * @version
 * @since JDK 1.7
 */
public class AnjsTest {

	@Test
	public void test1() {
		try {
			String filePath = "D:\\data\\C000008\\10.txt";
			String tt = new String();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "GBK"));
			String str;
			while ((str = in.readLine()) != null) {
				tt += str;
			}
			test11(tt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		try {
			System.out.println("######测试英文关键字提取############");
			String filePath = "./test1.txt";
			BufferedReader in2 = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "UTF8"));
			String str2;
			String tt2 = new String();
			while ((str2 = in2.readLine()) != null) {
				tt2 += str2;
			}
			test11(tt2);
			System.out.println("######测试英文关键字提取结束############");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		KeyWordComputer kwc = new KeyWordComputer(20);
		String title = "中国海上搜救中心确定中国搜救船舶搜救方案";
		String content = " \n \n中新网北京3月10日电 10日上午，中国海上搜救中心组织召开马航失联客机海上搜救紧急会商会议，中国交通运输部副部长、中国海上搜救中心主任何建中对当前搜救工作做出部署：一要加强与马来西亚等多方搜救组织的沟通协调；二要根据搜救现场情况进一步完善搜救方案；三要加强信息交流共享，做好内外联动。 \n \n马航客机失联事件发生后，交通运输部启动一级应急响应，3月8日、9日4次召开马航失联客机应急反应领导小组工作会议，研判形势，部署搜寻工作。根据《国家海上搜救和重大海上溢油应急处置紧急会商工作制度》，交通运输部、国家海洋局、中国海警局、总参、海军等共同研究制定了中国船舶及航空器赴马航客机失联海域搜救方案，初步明确了“海巡31”、“南海救101”、“南海救115”、中国海警3411、海军528和999舰等6艘中国搜救船舶的海上搜救区域。 \n \n截至3月10日8时，中国海军528舰和中国海警3411舰已在相关区域开展搜救工作，预计交通运输部所属“南海救115”、“海巡31”轮、“南海救101”将先后于10日16时、11时17时、11日22时抵达马航客机疑似失联海域。中国海上搜救中心已将有关情况通报马来西亚海上搜救机构，并将与马来西亚、越南海上搜救机构保持密切联系，开展深度配合。同时，继续协调中国商船参与搜救。(完)(周音)";
		Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
		System.out.println(result);
	}

	@Test
	public void test5() {
		KeyWordComputer kwc = new KeyWordComputer(20);
		String title = "china sea save person";
		String content = "中Like most computing terms, concurrency is tricky to pin down. Informally, a concurrent program is one that does more than one thing at a time. For example, a web browser may be simultaneously performing an HTTP GET request to get an HTML page, playing an audio clip, displaying the number of bytes received of some image, and engaging in an advisory dialog with a user. However, this simultaneity is sometimes an illusion. On some computer systems these different activities might indeed be performed by different CPUs. But on other systems they are all performed by a single timeshared CPU that switches among different activities quickly enough that they appear to be simultaneous, or at least nondeterministically interleaved, to human observers. A more precise, though not very interesting definition of concurrent programming can be phrased"
				+ "operationally: A Java virtual machine and its underlying operating system (OS) provide mappings from apparent simultaneity to physical parallelism (via multiple CPUs), or lack thereof, by allowing independent activities to proceed in parallel when possible and desirable, and otherwise by timesharing. Concurrent programming consists of using programming constructs that are mapped in this way. Concurrent programming in the Java programming language entails using Java programming";
		Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
		System.out.println(result);
	}

	public static void test11(String content) {
		KeyWordComputer key = new KeyWordComputer(10);
		Iterator it = key.computeArticleTfidf(content).iterator();
		while (it.hasNext()) {
			Keyword key2 = (Keyword) it.next();
			System.out.println(key2.toString() + key2.getScore());
		}
	}

}
