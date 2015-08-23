package com.gesoft.admin;

import com.thoughtworks.selenium.DefaultSelenium;

public class GoogleSearchTestExample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host = "localhost";  
	      int port = 4444;  
	      String url = "http://www.baidu.com/";  
	      String browserType = "*firefox";  
	   
	      String keyWordsLocator = "document.getElementById('kw')";     
	      String search = "document.getElementById('su')";  
	      DefaultSelenium selenium = new DefaultSelenium(host,port,browserType,url);  
	      selenium.start();  
	      selenium.open(url);  
	      selenium.type(keyWordsLocator,"java selenium");  
	      selenium.click(search);  
	      selenium.waitForPageToLoad("50000");  
	      selenium.stop();  

	}

}
