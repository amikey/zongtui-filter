package com.gesoft.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BrowserTest {
	public static String Httpconnt(String urls) {
		long begintime = System.currentTimeMillis();
		StringBuffer bs = new StringBuffer();

		URL url;
		try {
			// url = new URL("http://www.yhfund.com.cn");
			
			Document doc = Jsoup.connect(urls).get();
			url = new URL(urls);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(is));
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l).append("/n");
			}
//			System.out.println(bs.toString());

			// System.out.println(" content-encode："+urlcon.getContentEncoding());
			// System.out.println(" content-length："+urlcon.getContentLength());
			// System.out.println(" content-type："+urlcon.getContentType());
			// System.out.println(" date："+urlcon.getDate());

//			System.out.println("总共执行时间为："
//					+ (System.currentTimeMillis() - begintime) + "毫秒");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bs.toString();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// while (true) {
		// BrowserTest.Httpconnt("http://baidu.com");
		// }
		String N = "01:大汽车";
		String L = "";
		String R = "";
		int k = N.length();
		for (int i = 0; i < N.length(); i++) {
//			if (N.substring(i, i + 1).equals("|")) {
//				L = N.substring(0, i).trim();
//				R = N.substring(i + 1, k).trim();
//			} else {
//
//			}
			System.out.println(N.substring(0, i).trim());
			System.out.println(L);
			System.out.println(R);
		}

		// //启用cmd运行IE的方式来打开网址。
		// String str = "cmd /c start iexplore http://blog.csdn.net/powmxypow";
		// try {
		// Runtime.getRuntime().exec(str);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// 启用系统默认浏览器来打开网址。
		// try {
		// URI uri = new URI("http://blog.csdn.net/powmxypow");
		// Desktop.getDesktop().browse(uri);
		// } catch (URISyntaxException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// finally{
		//
		// }
		// 方法一
		// URL url = new URL("http://www.sina.com.cn");
		// URLConnection urlcon = url.openConnection();
		// InputStream is = urlcon.getInputStream();

		// 方法二
		// URL url;
		// try {
		// url = new URL("http://www.yhfund.com.cn");
		// HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
		// InputStream is = urlcon.getInputStream();
		// System.out.println(is);
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// //方法三
		// URL url;
		// try {
		// url = new URL("http://www.yhfund.com.cn");
		// InputStream is = url.openStream();
		// System.out.println(is);
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
