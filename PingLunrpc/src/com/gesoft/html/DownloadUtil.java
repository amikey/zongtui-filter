package com.gesoft.html;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DownloadUtil {
	
	
	/**
	 * 下载网页源代码
	 * 输入类型：链接url,超时时间,编码,cookies,代理
	 * 返回 String源码
	 */
	public static String getHtml(String strUrl, int timeout,String strEnCoding, String cookies, Proxy proxy) {
		if (strUrl == null || strUrl.length() == 0) {
			return null;
		}
		boolean isMemoryError = false;
		StringBuffer strHtml = null;
		String strLine = "";
		HttpURLConnection httpConnection = null;// 这里可以定义成HttpURLConnection
		InputStream urlStream = null;
		BufferedInputStream buff = null;
		BufferedReader br = null;
		Reader r = null;
		boolean isError = false;
		try {
			// 链接网络得到网页源代码
			URL url = new URL(strUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			if (proxy != null) {
				httpConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				httpConnection = (HttpURLConnection) url.openConnection();
			}
			httpConnection.addRequestProperty("User-Agent","Mozilla/4.0");
			//httpConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.1) Gecko/20061204 Firefox/2.0.0.1");
    		httpConnection.addRequestProperty("Accept","www/source; text/html; image/gif; */*");
    		httpConnection.addRequestProperty("Accept-Language","zh-cn,zh;q=0.5");
    		
    		httpConnection.setFollowRedirects(true);
			if (proxy == null && strEnCoding != null) {
				httpConnection.addRequestProperty("Accept-Charset", strEnCoding);
			}
			if (cookies != null) {
				httpConnection.setRequestProperty("Cookie", cookies);
			}
			
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setReadTimeout(timeout);
			urlStream = httpConnection.getInputStream();
			buff = new BufferedInputStream(urlStream);
			if (strEnCoding == null || strEnCoding.compareTo("null") == 0) {
				r = new InputStreamReader(buff);
			} else {
				try {
					r = new InputStreamReader(buff, strEnCoding);
				} catch (UnsupportedEncodingException e) {
					r = new InputStreamReader(buff);
				}
			}
			br = new BufferedReader(r);
			strHtml = new StringBuffer("");
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}
			
		} catch (java.lang.OutOfMemoryError out) {
			out.printStackTrace();
			System.out.println(out.getClass() + "下载网页" + strUrl + "失败");
			isError = true;
			isMemoryError = true;
		} catch (Exception e) {
			isError = true;
		} finally {
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
					httpConnection = null;
				}
				if (br != null) {
					br.close();
					br = null;
				}
				if (r != null) {
					r.close();
					r = null;
				}
				if (buff != null) {
					buff.close();
					buff = null;
				}
				if (isMemoryError)
					buff = null;
				if (urlStream != null) {
					urlStream.close();
					urlStream = null;
				}
				if (isMemoryError)
					System.gc();
			} catch (Exception e) {
				return null;
			}
		}
		if (strHtml == null || isError){
			String strcontent=getURLContent(strUrl,strEnCoding);
			if(strcontent!=null){
				return strcontent;
			}else
			   return null;
		}
		if (isMemoryError)
			return null;
		
		BrowserTest.Httpconnt(strUrl);
		return strHtml.toString();
	}
	
	 /**
	   * 获得标签内容
	   * 
	   * @param strText 源码字符串
	   * @param strStart 开始标记
	   * @param strEnd 结束标记
	   * @return
	   */
	  public static String getTagInfo(String strText,String strStart,String strEnd) {
	    try {
	      int iStart = strText.indexOf(strStart);
	      if (iStart != -1) {
	        int iEnd = strText.indexOf(strEnd, iStart + strStart.length());
	        if (iEnd != -1) {
	          strText = strText.substring(iStart + strStart.length(), iEnd);
	          return strText;
	        }
	      }
	    } catch (Exception e) {
	      // TODO: handle exception
	      return "";
	    }
	    return "";
	  }
	
	
	public static String getURLContent(String url, String encoding) {
		if (url == null || "".equals(url.trim()))
			return null;

		StringBuffer content = new StringBuffer();
		try {
			// 新建URL对象
			URL u = new URL(url);
			InputStream in = new BufferedInputStream(u.openStream());
			InputStreamReader theHTML = new InputStreamReader(in,encoding != null ? encoding : "gb2312");
			int c;
			while ((c = theHTML.read()) != -1) {
				content.append((char) c);
			}
		}
		// 处理异常
		catch (MalformedURLException e) {
			System.err.println(e);
			return null;
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
		return content.toString();
	}
	/**
	 * 获取跳转后的url
	 **/
    public static String getUrlTrue(String urlStr){
    	URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return urlStr;
		}
        String strUrl = "";
        try {
        	URLConnection c = url.openConnection();
			c.connect();
			if (c instanceof HttpURLConnection) {
				HttpURLConnection h = (HttpURLConnection) c;
				h.getRequestMethod();
				h.getResponseMessage();
				h.getResponseCode();
				strUrl=h.getURL().toString();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return urlStr;
		}
		return strUrl;
    }
	    


	public static void main(String[] args){
		String urlStr = "http://m.okpush.com/j.html?pk=redirect&adId=7207&planId=1502&webId=6815&channelId=6815&publishId=8469";
		urlStr=DownloadUtil.getUrlTrue(urlStr);
		System.out.println(urlStr);
		String strHtml="";
		strHtml=getHtml(urlStr, 1000*10, "utf-8", null, null);
		System.out.println(strHtml);
	}
}
