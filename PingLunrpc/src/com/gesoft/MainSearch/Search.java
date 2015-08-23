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
		// ���룺
		StrKeyword = java.net.URLEncoder.encode(StrKeyword, "UTF-8");
		// //���룺
		// System.out.println(java.net.URLDecoder.decode("%E6%B5%8B%E8%AF%95%26%3Faaa","UTF-8"));
		return StrKeyword;
	}

	/*
	 * ������url
	 */
	public static String mainUrl(String StrKeyword)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�
		String strUrlAdd = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682";
		return strUrlAdd;

	}

	/**
	 * ��ñ�ǩ����
	 * 
	 * @param strText
	 *            Դ���ַ���
	 * @param strStart
	 *            ��ʼ���
	 * @param strEnd
	 *            �������
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
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�
		System.out.println("�ؼ���Ϊ��" + StrKeyword);

		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		String strUrlAdd = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		try {
			System.out.println("����һҳ��ַΪ" + strUrlAdd);
			String strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null,
					null);
			String stringstr = "";
			// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
			// "\"");
			stringstr = getTagInfo(strHtml, "target=\"_blank\" href=\"", "\"");
			// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
			strUrlAdd = stringstr.replaceAll("amp;", "");
			System.out.println("�����urlΪ" + strUrlAdd);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println();
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println("�ٴδ�urlΪ" + strUrlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����ϸxiaoUrl�����쳣");
		}

		return strUrlAdd;
	}

	/**
	 * ����ҳ �����һ������ ͣ��0.5��� ��ˢҳ--��ҳ�����-- �ٴε��--�ٴε��---��ˢҳ--��ҳ�����---��һҳ--����򿪣�
	 * �����������
	 */

	public static String xiaoUrlsogou(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// ��ҳ�������ݵĲɼ�

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		String strUrlAddone = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		try {
			System.out.println("�򿪵�ַΪ" + strUrlAddone);
			// System.out.println("�ȴ�2��");
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
			System.out.println("��ҳ�����urlΪ" + strUrlAddone);
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
			System.out.println("��һ�ν�����ϸҳ��urlΪ" + shuijidianjiurl);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����ϸxiaoUrl�����쳣");
		}
		System.out.println("�ؼ��ֶ��δ�Ϊ��" + StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		String strUrlAdd = "http://www.sogou.com/web?query="
				+ Keyword
				+ "&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		try {
			System.out.println("����һҳ��ַΪ" + strUrlAdd);
			String strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null,
					null);
			String stringstr = "";
			// stringstr=getTagInfo(strHtml,"<a class=\"result_title\" href=\"",
			// "\"");
			stringstr = getTagInfo(strHtml, "target=\"_blank\" href=\"", "\"");
			// strUrlAdd="http://m.baidu.com"+stringstr.replaceAll("amp;", "");
			strUrlAdd = stringstr.replaceAll("amp;", "");
			System.out.println("�����urlΪ" + strUrlAdd);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			// System.out.println("�ȴ�2��");
			// Thread.sleep(2000);
			Search.seleepTime(5);
			downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
			System.out.println("�ٴδ�urlΪ" + strUrlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����ϸxiaoUrl�����쳣");
		}

		return strUrlAdd;
	}

	/*
	  * 
	  */

	public static String xiaoUrlbaidu(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("�򿪵�ַΪ" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�0.1��");
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
		System.out.println("��ҳ�����urlΪ" + strUrlAddone);
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
		System.out.println("��һ�ν�����ϸҳ��urlΪ" + shuijidianjiurl);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("����ϸxiaoUrl�����쳣");
		// }
		System.out.println("�ؼ��ֶ��δ�Ϊ��" + StrKeyword);
		String strUrlAdd = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=10";
		// String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtime = new DownloadUtil();
		// try {
		System.out.println("����һҳ��ַΪ" + strUrlAdd);
		strHtml = downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		System.out.println("�ȴ�2��");
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
		System.out.println("�����urlΪ" + strUrlAdd);
		downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		System.out.println("�ٴδ�urlΪ" + strUrlAdd);
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("����ϸxiaoUrl�����쳣");
		// }

		return strUrlAdd;
	}

	/*
	 * 2015��7��26��21:50:41 ��ˢҳ��2��ͣ����-----������һ�����ӣ��ڴ򿪵�������ͣ��2�룩
	 */
	public static String xiaoUrlbaidu1(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("��ˢҳ��2��ͣ����" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("�ȴ�2��");
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
		// System.out.println("��ҳ�����urlΪ"+strUrlAddone);
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
		System.out.println("������ϸҳ��urlΪ" + shuijidianjiurl);
		System.out.println("�ȴ�2��");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("����ϸxiaoUrl�����쳣");
		// }
		// System.out.println("�ؼ��ֶ��δ�Ϊ��"+StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		// // String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// DownloadUtil downtime= new DownloadUtil();
		// // try {
		// System.out.println("����һҳ��ַΪ"+strUrlAdd);
		// strHtml=downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�2��");
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
		// System.out.println("�����urlΪ"+strUrlAdd);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("�ٴδ�urlΪ"+strUrlAdd);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("����ϸxiaoUrl�����쳣");
		// // }

		return "";
	}

	/*
	 * 2015��7��26��21:50:41 ��ˢҳ��2��ͣ����-----������һ�����ӣ��ڴ򿪵�������ͣ��2�룩
	 */
	public static String xiaoUrlbaidu2(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		// // String
		// strUrlAddone="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=1&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		DownloadUtil downtimeone = new DownloadUtil();
		// try {
		System.out.println("��ˢҳ��2��ͣ����" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("�ȴ�2��");
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
		System.out.println("������һ�����ӣ��ڴ򿪵�������ͣ��2�룩" + strUrlAddone);
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
		// System.out.println("��һ�ν�����ϸҳ��urlΪ"+shuijidianjiurl);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("����ϸxiaoUrl�����쳣");
		// // }
		// System.out.println("�ؼ��ֶ��δ�Ϊ��"+StrKeyword);
		// String
		// strUrlAdd="http://m.baidu.com/pu=sz%401321_480/s?word="+Keyword+"&sa=ib&ts=1404682"+numString+"&pn=10";
		// // String
		// strUrlAdd="http://www.sogou.com/web?query="+Keyword+"&sut=5962&lkt=0%2C0%2C0&sst0=1437273545415&page=2&ie=utf8&p=40040100&dp=1&w=01019900&dr=1";
		// DownloadUtil downtime= new DownloadUtil();
		// // try {
		// System.out.println("����һҳ��ַΪ"+strUrlAdd);
		// strHtml=downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�2��");
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
		// System.out.println("�����urlΪ"+strUrlAdd);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// downtime.getHtml(strUrlAdd, 5000, "utf-8", null, null);
		// System.out.println("�ٴδ�urlΪ"+strUrlAdd);
		// // } catch (Exception e) {
		// // // TODO: handle exception
		// // System.out.println("����ϸxiaoUrl�����쳣");
		// // }

		return "";
	}

	/*
	 * 2015��7��26��22:24:35 3.��ˢҳ��2��ͣ����-----��һҳ��ҳ��2��ͣ����----������һ��ҳ�棨2��ͣ����
	 */
	public static String xiaoUrlbaidu3(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// ��ҳ�������ݵĲɼ�
		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=ib&ts=1404682" + numString + "&pn=0";
		DownloadUtil downtimeone = new DownloadUtil();
		System.out.println("��ˢҳ��2��ͣ����" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		System.out.println("�ȴ�2��");
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
		System.out.println("��һҳ��ҳ��2��ͣ����");
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
		System.out.println("������һ��ҳ�棨2��ͣ����" + strUrlAddone);
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
	 * 2015��7��26��23:38:07 1.��ˢҳ-----������-----������--������---��ˢҳ
	 * ---������--������---��������ÿ��ҳ��ͣ��2�룩 2015��8��2��12:35:13
	 * 1�ؼ������루1���ּ��0.1�룩--��ˢҳ--��ҳ�����-- �ٴε��--�ٴε��---��ˢҳ--��ҳ�����---��һҳ--�����
	 */
	public static String xiaoUrlbaidu4(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// Keyword = StrKeyword;
		// ��ҳ�������ݵĲɼ�
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		// System.out.println("+++++++++++++++"+StrKeyword+"##########"+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length()));
		// StrKeyword=StrKeyword+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length());
		System.out.println("���ֻ��ٶ�����ҳ:http://m.baidu.com");
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
		// System.out.println("����ؼ�������Ϊ" + keywordss);
		// System.out.println("�ȴ�0.1��");
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
				System.out.println("��ǰ���е�" + i + "����");

				String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
						+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
				stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
						+ keywordss
						+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				System.out.println("����ؼ�������Ϊ" + keywordss);
				System.out.println("�ȴ�0.1��");
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
		System.out.println("��ˢҳ" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�5��");
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
		System.out.println("������" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�5��");
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
			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�5��");
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
			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�5��");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
			System.out.println("������ϸҳ���ֺ�û�з�������������");
		}

		System.out.println("��ˢҳ " + strUrlAddone);
		strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word=" + Keyword
				+ "&sa=td&pn=10";
		System.out.println("���ڽ���ڶ�ҳ�ĵ�ַ��" + strUrlAddone);
		strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�5��");
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
		System.out.println("������" + strUrlAddone);
		htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�0.5��");
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
			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�0.5��");
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
			System.out.println("������" + shuijidianjiurl);
			System.out.println("�ȴ�0.5��");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
			System.out.println("�ڶ�ҳ������ϸҳ���ֺ�û�з�������������");
		}

		return "";
	}

	/*
	 * 2015��8��3��21:46:00 1�ؼ������루1���ּ��0.1�룩--�ؼ���--��ˢҳ-��ҳ�����
	 */
	public static String xiaoUrlbaidu5(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// ��ҳ�������ݵĲɼ�
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		System.out.println("���ֻ��ٶ�����ҳ:http://m.baidu.com");
		String strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word="
				+ Keyword + "&sa=td";
		System.out.println("��ˢҳ" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		Search.seleepTime(5);
		String stringstr = "";
		stringstr = getTagInfo(strHtml, "<a class=\"result_title\" href=\"",
				"\"");
		// stringstr=getTagInfo(strHtml,"target=\"_blank\" href=\"", "\"");
		strUrlAddone = "http://m.baidu.com" + stringstr.replaceAll("amp;", "");
		// strUrlAddone=stringstr.replaceAll("amp;", "");
		System.out.println("������" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�5��");
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
	 * 2015��8��13��22:18:54 class��
	 */
	// public static String xiaoUrlbaidu4cope(String StrKeyword,String
	// numString) throws UnsupportedEncodingException{
	//
	// }
	/*
	 * 2015��8��12��23:33:55 pk��
	 */
	public static String xiaoUrlbaidu4pk(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		Keyword = StrKeyword;
		// ��ҳ�������ݵĲɼ�
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("�ؼ���Ϊ��" + StrKeyword);

//		for (int i = 1; i <= 4; i++) {
			String keywordss = "";
			keywordss = StrKeyword.substring(0, StrKeyword.length()).trim();
			String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
					+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
			downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
			System.out.println("�ٶ�url��" + stringurlword);
			// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
			// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
			stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
					+ keywordss
					+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
			downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
			System.out.println("�ٶ�wapurl��" + stringurlword);
			System.out.println("����ؼ�������Ϊ" + keywordss);
//		}
		return "";
	}
	
	
	/**
	 * 2015��8��16��23:24:30
	 * @param AllKeyWord
	 * @return
	 */
	public static String xiaoUrlbaidu4haoshi(String StrKeyword, String numString)
			throws UnsupportedEncodingException {
		/*
		 * ����ҳ �����һҳ �����һ������ ͣ�ټ����
		 */
		Search bb = new Search();
		String Keyword = bb.URLEncoderURL(StrKeyword);
		// Keyword = StrKeyword;
		// ��ҳ�������ݵĲɼ�
		DownloadUtil downtimeone = new DownloadUtil();

		System.out.println("�ؼ���Ϊ��" + StrKeyword);
		// System.out.println("+++++++++++++++"+StrKeyword+"##########"+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length()));
		// StrKeyword=StrKeyword+StrKeyword.substring(StrKeyword.length()-1,StrKeyword.length());
//		System.out.println("���ֻ��ٶ�����ҳ:http://m.baidu.com");
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
		// System.out.println("����ؼ�������Ϊ" + keywordss);
		// System.out.println("�ȴ�0.1��");
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
//				System.out.println("��ǰ���е�" +keywordss + "����");

				String stringurlword = "http://m.baidu.com/pu=sz%401321_480/s?word="
						+ keywordss + "&sa=ib&ts=1404682" + numString + "&pn=0";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
				// http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word=333&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap
				stringurlword = "http://m.baidu.com/ssid=0/from=0/bd_page_type=1/uid=0/baiduid=C56AD478D216752A535DAD9A230BDDB9/s?word="
						+ keywordss
						+ "&uc_param_str=upssntdnvelami&sa=ib&st_1=111041&st_2=102041&pu=sz%40224_220%2Cta%40middle___3_537&idx=20000&tn_1=middle&tn_2=middle&ct_2=%E6%90%9CWap";
				downtimeone.getHtml(stringurlword, 5000, "utf-8", null, null);
//				System.out.println("����ؼ�������Ϊ" + stringurlword);
//				System.out.println("�ȴ�0.1��");
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
//		System.out.println("��ˢҳ" + strUrlAddone);
		String strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�5��");
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
//		System.out.println("������" + strUrlAddone);
		String htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null,
				null);
		// System.out.println("�ȴ�5��");
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
//			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�5��");
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
//			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�5��");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
//			System.out.println("������ϸҳ���ֺ�û�з�������������");
		}

//		System.out.println("��ˢҳ " + strUrlAddone);
		strUrlAddone = "http://m.baidu.com/pu=sz%401321_480/s?word=" + Keyword
				+ "&sa=td&pn=10";
//		System.out.println("���ڽ���ڶ�ҳ�ĵ�ַ��" + strUrlAddone);
		strHtml = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�5��");
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
//		System.out.println("������" + strUrlAddone);
		htmlStr = downtimeone.getHtml(strUrlAddone, 5000, "utf-8", null, null);
		// System.out.println("�ȴ�0.5��");
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
//			System.out.println("������" + shuijidianjiurl);
			// System.out.println("�ȴ�0.5��");
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
//			System.out.println("������" + shuijidianjiurl);
//			System.out.println("�ȴ�0.5��");
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			Search.seleepTime(5);
		} else {
//			System.out.println("�ڶ�ҳ������ϸҳ���ֺ�û�з�������������");
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
			// System.out.println("�ȴ�1-2��,��ǰ�ȴ�"+t+"��");
//			System.out.println("�ȴ�2��,�ȴ�" + t + "��");
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
		// System.out.println(bb.xiaoUrl("���ջ��ļҺ��ºӺ�",""));
		System.out.println(bb.Analytical("##���Ѽӹ�1##���ϲ���1##Aɨ��1##Cɨ��1"));

	}

}
