package com.gesoft.MainSearch;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gesoft.admin.TimeoutTest1;
import com.gesoft.html.DownloadUtil;

public class Search {

	public String URLEncoderURL(String StrKeyword)
			throws UnsupportedEncodingException {
		// 编码：
		StrKeyword = java.net.URLEncoder.encode(StrKeyword, "UTF-8");
		// //解码：
		// System.out.println(java.net.URLDecoder.decode("%E6%B5%8B%E8%AF%95%26%3Faaa","UTF-8"));
		return StrKeyword;
	}

	/*
	 * 返回主url
	 */
	public static String mainUrl(String StrKeyword)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集
		String strUrlAdd = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682";
		return strUrlAdd;

	}

	/**
	 * 获得标签内容
	 * 
	 * @param strText
	 *            源码字符串
	 * @param strStart
	 *            开始标记
	 * @param strEnd
	 *            结束标记
	 * @return
	 */
	public static String getTagInfo(String strText, String strStart,
			String strEnd) {
		try {
			int iStart = strText.indexOf(strStart);
			if (iStart != -1) {
				int iEnd = strText.indexOf(strEnd, iStart + strStart.length());
				if (iEnd != -1) {
					strText = strText.substring(iStart + strStart.length(),
							iEnd);
					return strText;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		return "";
	}

	/*
	  * 
	  */

	public static String xiaoUrl(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集
		System.out.println("关键字为：" + StrKeyword);

		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		String strUrlAdd = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		try {
			System.out.println("打开下一页地址为" + strUrlAdd);
			String strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null,
					null);
			String stringstr = "";
			// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
			// "\"");
			stringstr = getTagInfo(strHtml, "target=\"_blank\" href=\"", "\"");
			// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
			strUrlAdd = stringstr.replaceAll("amp;", "");
			System.out.println("任意打开url为" + strUrlAdd);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println();
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println("再次打开url为" + strUrlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("打开详细xiaoUrl出现异常");
		}

		return strUrlAdd;
	}

	/**
	 * 打开首页 随机打开一个链接 停顿0.5秒吧 起刷页--首页任意打开-- 再次点击--再次点击---起刷页--首页任意打开---下一页--任意打开，
	 * 是这个过程吗
	 */

	public static String xiaoUrlsogou(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// 首页进行数据的采集

		System.out.println("关键字为：" + StrKeyword);
		String strUrlAddone = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		try {
			System.out.println("打开地址为" + strUrlAddone);
			// System.out.println("等待2秒");
			// Thread.sleep(2000);
			Search.seleepTime(5);
			String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8",
					null, null);
			String stringstr = "";
			// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
			// "\"");
			stringstr = getTagInfo(strHtml, "target=\"_blank\" href=\"", "\"");
			// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
			strUrlAddone = stringstr.replaceAll("amp;", "");
			System.out.println("首页任意打开url为" + strUrlAddone);
			String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8",
					null, null);
			String html = htmlStr;
			Document doc = Jsoup.parse(html);
			Elements imports = doc.select("link[href]");
			Element urlone = imports
					.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			String shuijidianjiurl = urlone.attr("abs:href");
			;
			downtimeone
					.getHtml(shuijidianjiurl + "", 5000, "utf-8", null, null);
			System.out.println("第一次进入详细页打开url为" + shuijidianjiurl);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("打开详细xiaoUrl出现异常");
		}
		System.out.println("关键字二次打开为：" + StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		String strUrlAdd = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		try {
			System.out.println("打开下一页地址为" + strUrlAdd);
			String strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null,
					null);
			String stringstr = "";
			// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
			// "\"");
			stringstr = getTagInfo(strHtml, "target=\"_blank\" href=\"", "\"");
			// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
			strUrlAdd = stringstr.replaceAll("amp;", "");
			System.out.println("任意打开url为" + strUrlAdd);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			// System.out.println("等待2秒");
			// Thread.sleep(2000);
			Search.seleepTime(5);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println("再次打开url为" + strUrlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("打开详细xiaoUrl出现异常");
		}

		return strUrlAdd;
	}

	/*
	  * 
	  */

	public static String xiaoUrlbaidu(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集

		System.out.println("关键字为：" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("打开地址为" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待0.1秒");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("首页任意打开url为" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		String html = htmlStr;
		Document doc = Jsoup.parse(html);
		Elements imports = doc.select("link[href]");
		Element urlone = imports.get((int) (imports.size() * Math.random()));
		urlone.attr("abs:href");
		String shuijidianjiurl = urlone.attr("abs:href");
		;
		downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8", null, null);
		System.out.println("第一次进入详细页打开url为" + shuijidianjiurl);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("打开详细xiaoUrl出现异常");
		// }
		System.out.println("关键字二次打开为：" + StrKeyword);
		String strUrlAdd = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=10";
		// String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		// try {
		System.out.println("打开下一页地址为" + strUrlAdd);
		strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		System.out.println("等待2秒");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String stringstr="";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAdd = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAdd=stringstr.replaceAll("amp;", "");
		System.out.println("任意打开url为" + strUrlAdd);
		downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		System.out.println("再次打开url为" + strUrlAdd);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("打开详细xiaoUrl出现异常");
		// }

		return strUrlAdd;
	}

	/*
	 * 2015年7月26日21:50:41 起刷页（2秒停留）-----任意点击一个链接（在打开的链接面停留2秒）
	 */
	public static String xiaoUrlbaidu1(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集

		System.out.println("关键字为：" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("起刷页（2秒停留）" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("等待2秒");
		// try {
		// // Thread.sleep(2000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// String stringstr="";
		// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
		// "\"");
		// // stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		// strUrlAddone="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
		// // strUrlAddone=stringstr.replaceAll("amp;", "");
		// System.out.println("首页任意打开url为"+strUrlAddone);
		// String htmlStr=downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
		// null);
		String html = strHtml;
		Document doc = Jsoup.parse(html);
		// System.out.println(doc);
		// Elements imports = doc.select("link[href]");

		Elements links = doc.getElementsByTag("a");
		System.out.println(links.size());
		// for (Element link : links) {
		// String linkHref = link.attr("href");
		// String linkText = link.text();
		// }
		Element urlone = links.get((int) (links.size() * Math.random()));
		System.out.println(urlone);
		// Element urlone =imports.get((int) (imports.size()*Math.random()));
		String shuijidianjiurl = urlone.attr("abs:href");
		System.out.println(shuijidianjiurl);
		downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8", null, null);
		System.out.println("进入详细页打开url为" + shuijidianjiurl);
		System.out.println("等待2秒");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("打开详细xiaoUrl出现异常");
		// }
		// System.out.println("关键字二次打开为："+StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		// // String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// DownloadUtil downtime= new DownloadUtil();
		// // try {
		// System.out.println("打开下一页地址为"+strUrlAdd);
		// strHtml=downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("等待2秒");
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// // String stringstr="";
		// String
		// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
		// "\"");
		// // stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
		// // strUrlAdd=stringstr.replaceAll("amp;", "");
		// System.out.println("任意打开url为"+strUrlAdd);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("再次打开url为"+strUrlAdd);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("打开详细xiaoUrl出现异常");
		// // }

		return "";
	}

	/*
	 * 2015年7月26日21:50:41 起刷页（2秒停留）-----任意点击一个链接（在打开的链接面停留2秒）
	 */
	public static String xiaoUrlbaidu2(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集

		System.out.println("关键字为：" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("起刷页（2秒停留）" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("等待2秒");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("任意点击一个链接（在打开的链接面停留2秒）" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String html = htmlStr;
		// Document doc = Jsoup.parse(html);
		// Elements imports = doc.select("link[href]");
		// Element urlone =imports.get((int) (imports.size()*Math.random()));
		// urlone.attr("abs:href");
		// String shuijidianjiurl=urlone.attr("abs:href");;
		// downtimeone.getHtml(shuijidianjiurl+"", 5000, "utf-8", null, null);
		// System.out.println("第一次进入详细页打开url为"+shuijidianjiurl);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("打开详细xiaoUrl出现异常");
		// // }
		// System.out.println("关键字二次打开为："+StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		// // String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// DownloadUtil downtime= new DownloadUtil();
		// // try {
		// System.out.println("打开下一页地址为"+strUrlAdd);
		// strHtml=downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("等待2秒");
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// // String stringstr="";
		// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
		// "\"");
		// // stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
		// // strUrlAdd=stringstr.replaceAll("amp;", "");
		// System.out.println("任意打开url为"+strUrlAdd);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("再次打开url为"+strUrlAdd);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("打开详细xiaoUrl出现异常");
		// // }

		return "";
	}

	/*
	 * 2015年7月26日22:24:35 3.起刷页（2秒停留）-----下一页（页面2秒停留）----随机点击一个页面（2秒停留）
	 */
	public static String xiaoUrlbaidu3(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// 首页进行数据的采集
		System.out.println("关键字为：" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		DownloadUtil downtimeone = new DownloadUtil();
		System.out.println("起刷页（2秒停留）" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("等待2秒");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word=" + Keyword
				+ "&sa=ib&ts=1404682" + numString + "&pn=10";
		System.out.println(strUrlAddone);
		strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		System.out.println("下一页（页面2秒停留）");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		System.out.println("随机点击一个页面（2秒停留）" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "";
	}

	/*
	 * 2015年7月26日23:38:07 1.起刷页-----随机点击-----随机点击--随机点击---起刷页
	 * ---随机点击--随机点击---随机点击（每个页面停留2秒） 2015年8月2日12:35:13
	 * 1关键词敲入（1个字间隔0.1秒）--起刷页--首页任意打开-- 再次点击--再次点击---起刷页--首页任意打开---下一页--任意打开
	 */
	public static String xiaoUrlbaidu4(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// Keyword = StrKeyword;
		// 首页进行数据的采集
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("关键字为：" + StrKeyword);
		// System.out.println("+++++++++++++++"+StrKeyword+"##########"+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length()));
		// StrKeyword=StrKeyword+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length());
		System.out.println("打开手机百度输入页:http://m.baidu.com");
		downtimeone.getHtml("http://m.baidu.com", 5000, "utf-8", null, null);
		// for (int i = 1; i <= StrKeyword.length(); i++) {
		// String keywordss = "";
		// keywordss = StrKeyword.substring(0, i).trim();
		// String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
		// + keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
		// //
		// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
		// stringurlword =
		// "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
		// + keywordss
		// +
		// "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
		// downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
		// System.out.println("输入关键字依次为" + keywordss);
		// System.out.println("等待0.1秒");
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// }

		for (int i = 4; i <= StrKeyword.length(); i++) {
			String keywordss = "";
			if (i == 4 || i == 10 || i == StrKeyword.length()) {

				keywordss = StrKeyword.substring(0, i).trim();
				System.out.println("当前运行第" + i + "个字");

				String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
						+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
				stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
						+ keywordss
						+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				System.out.println("输入关键字依次为" + keywordss);
				System.out.println("等待0.1秒");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

		// 888888888888888888888888888888888888888

		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=td";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// try {
		System.out.println("起刷页" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("随机点击" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		String html = htmlStr;
		Document doc = Jsoup.parse(html);
		Elements imports = doc.select("link[href]");
		if (imports.size() != 0) {
			Element urlone = imports
					.get((int) (imports.size() * Math.random()));
			String shuijidianjiurl = urlone.attr("abs:href");
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待5秒");
			// try {
			// Thread.sleep(5000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
			html = htmlStr;
			doc = Jsoup.parse(html);
			imports = doc.select("link[href]");
			urlone = imports.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			shuijidianjiurl = urlone.attr("abs:href");
			;
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待5秒");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
			System.out.println("进入详细页出现后没有发现随机点击链接");
		}

		System.out.println("起刷页 " + strUrlAddone);
		strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word=" + Keyword
				+ "&sa=td&pn=10";
		System.out.println("现在进入第二页的地址：" + strUrlAddone);
		strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("随机点击" + strUrlAddone);
		htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("等待0.5秒");
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		html = htmlStr;
		doc = Jsoup.parse(html);
		imports = doc.select("link[href]");
		if (imports.size() > 0) {
			Element urlone = imports
					.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			String shuijidianjiurl = urlone.attr("abs:href");
			;
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待0.5秒");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);

			html = htmlStr;
			doc = Jsoup.parse(html);
			imports = doc.select("link[href]");
			urlone = imports.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			shuijidianjiurl = urlone.attr("abs:href");
			;
			downtimeone
					.getHtml(shuijidianjiurl + "", 5000, "utf-8", null, null);
			System.out.println("随机点击" + shuijidianjiurl);
			System.out.println("等待0.5秒");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
			System.out.println("第二页进入详细页出现后没有发现随机点击链接");
		}

		return "";
	}

	/*
	 * 2015年8月3日21:46:00 1关键词敲入（1个字间隔0.1秒）--关键词--起刷页-首页任意打开
	 */
	public static String xiaoUrlbaidu5(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// 首页进行数据的采集
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("关键字为：" + StrKeyword);
		System.out.println("打开手机百度输入页:http://m.baidu.com");
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=td";
		System.out.println("起刷页" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		Search.seleepTime(5);
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("随机点击" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);

		return "";
	}

	/*
	 * 2015年8月13日22:18:54 class版
	 */
	// public static String xiaoUrlbaidu4cope(String StrKeyword,String
	// numString) throws UnsupportedEncodingException{
	//
	// }
	/*
	 * 2015年8月12日23:33:55 pk版
	 */
	public static String xiaoUrlbaidu4pk(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// 首页进行数据的采集
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("关键字为：" + StrKeyword);

//		for (int i = 1; i <= 4; i++) {
			String keywordss = "";
			keywordss = StrKeyword.substring(0, StrKeyword.length()).trim();
			String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
					+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
			downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
			System.out.println("百度url：" + stringurlword);
			// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
			// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
			stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
					+ keywordss
					+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
			downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
			System.out.println("百度wapurl：" + stringurlword);
			System.out.println("输入关键字依次为" + keywordss);
//		}
		return "";
	}
	
	
	/**
	 * 2015年8月16日23:24:30
	 * @param AllKeyWord
	 * @return
	 */
	public static String xiaoUrlbaidu4haoshi(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * 打开首页 点击下一页 随机打开一个链接 停顿几秒吧
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// Keyword = StrKeyword;
		// 首页进行数据的采集
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("关键字为：" + StrKeyword);
		// System.out.println("+++++++++++++++"+StrKeyword+"##########"+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length()));
		// StrKeyword=StrKeyword+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length());
//		System.out.println("打开手机百度输入页:http://m.baidu.com");
//		downtimeone.getHtml("http://m.baidu.com", 5000, "utf-8", null, null);
		// for (int i = 1; i <= StrKeyword.length(); i++) {
		// String keywordss = "";
		// keywordss = StrKeyword.substring(0, i).trim();
		// String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
		// + keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
		// //
		// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
		// stringurlword =
		// "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
		// + keywordss
		// +
		// "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
		// downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
		// System.out.println("输入关键字依次为" + keywordss);
		// System.out.println("等待0.1秒");
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// }

//		for (int i = 4; i <= StrKeyword.length(); i++) {
			String keywordss = "";
//			if (i == 4 || i == 10 || i == StrKeyword.length()) {

				keywordss = StrKeyword.substring(0, StrKeyword.length()).trim();
//				System.out.println("当前运行第" +keywordss + "个字");

				String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
						+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
				stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
						+ keywordss
						+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
//				System.out.println("输入关键字依次为" + stringurlword);
//				System.out.println("等待0.1秒");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

//			}
//		}

		// 888888888888888888888888888888888888888

		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=td";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// try {
//		System.out.println("起刷页" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
//		System.out.println("随机点击" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		String html = htmlStr;
		Document doc = Jsoup.parse(html);
		Elements imports = doc.select("link[href]");
		if (imports.size() != 0) {
			Element urlone = imports
					.get((int) (imports.size() * Math.random()));
			String shuijidianjiurl = urlone.attr("abs:href");
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
//			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待5秒");
			// try {
			// Thread.sleep(5000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
			html = htmlStr;
			doc = Jsoup.parse(html);
			imports = doc.select("link[href]");
			urlone = imports.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			shuijidianjiurl = urlone.attr("abs:href");
			;
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
//			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待5秒");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
//			System.out.println("进入详细页出现后没有发现随机点击链接");
		}

//		System.out.println("起刷页 " + strUrlAddone);
		strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word=" + Keyword
				+ "&sa=td&pn=10";
//		System.out.println("现在进入第二页的地址：" + strUrlAddone);
		strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("等待5秒");
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
//		System.out.println("随机点击" + strUrlAddone);
		htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("等待0.5秒");
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		Search.seleepTime(5);
		html = htmlStr;
		doc = Jsoup.parse(html);
		imports = doc.select("link[href]");
		if (imports.size() > 0) {
			Element urlone = imports
					.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			String shuijidianjiurl = urlone.attr("abs:href");
			;
			htmlStr = downtimeone.getHtml(shuijidianjiurl + "", 5000, "utf-8",
					null, null);
//			System.out.println("随机点击" + shuijidianjiurl);
			// System.out.println("等待0.5秒");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);

			html = htmlStr;
			doc = Jsoup.parse(html);
			imports = doc.select("link[href]");
			urlone = imports.get((int) (imports.size() * Math.random()));
			urlone.attr("abs:href");
			shuijidianjiurl = urlone.attr("abs:href");
			;
			downtimeone
					.getHtml(shuijidianjiurl + "", 5000, "utf-8", null, null);
//			System.out.println("随机点击" + shuijidianjiurl);
//			System.out.println("等待0.5秒");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
//			System.out.println("第二页进入详细页出现后没有发现随机点击链接");
		}

		return "";
	}

	public static String Analytical(String AllKeyWord) {

		String[] keys = AllKeyWord.split("##");
		// System.out.println(keys);
		for (int i = 1; i < keys.length; i++) {
			if (keys[i] != null && keys[i] != "") {
				// xiaoUrl(keys[i], "");
				TimeoutTest1.runing(keys[i]);
			}
		}
		return "true";
	}

	public static void seleepTime(int t) {
		t = (int) (2 * Math.random());
		t = 2;
		try {
			// System.out.println("等待1-2秒,当前等待"+t+"秒");
//			System.out.println("等待2秒,等待" + t + "秒");
			Thread.sleep(t * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		Search bb = new Search();
		// System.out.println(bb.xiaoUrl("启闭机哪家好新河好",""));
		System.out.println(bb.Analytical("##蜂窝加工1##复合材料1##A扫描1##C扫面1"));

	}

}
