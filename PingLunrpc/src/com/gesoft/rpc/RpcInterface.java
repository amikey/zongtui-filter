package com.gesoft.rpc;

import com.gesoft.MainSearch.staticNumPag;
import com.gesoft.MainSearch.staticNumint;
import com.gesoft.WanfangBykeyWordMain.WanfangBykeyWordMain;
import com.gesoft.config.ConfigManager;

public class RpcInterface {
	
	/**
	 * 发布服务提供的方法
	 * @param spact
	 * @return
	 */
	public String pp2(String spact){
		System.out.println("-pp2-");	
		System.out.println(spact);
		return "SUCCESS";
	}
//	
//	public String pp3(String spact){
//		System.out.println("-pp3-");	
//		System.out.println(spact);
//		return "SUCCESS";
//	}
//	
//	public String pp4(String spact){
//		System.out.println("-pp4-");	
//		System.out.println(spact);
//		return "SUCCESS";
//	}
	
	public String Keyword(String ip,String Keyword,String intNum){
//		System.out.println("----------本次运行第"+ConfigManager.getInstance().getConfigValue("NO")+",其中的第"+ConfigManager.getInstance().getConfigValue("NEWNO")+"------------");
		System.out.println("----------本次运行第"+staticNumPag.readintNum()+",其中的第"+staticNumint.readintNum()+"------------");
		System.out.println("用户ip:"+ip+"，调用方法名称Keyword");	
		String returnstringkey="";
		try {
			WanfangBykeyWordMain bb=new WanfangBykeyWordMain();
			returnstringkey=bb.Keyword(10);
//		System.out.println("获取关键字为"+returnstringkey);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("抛出异常");
		}
		return returnstringkey;
	}
	
	
}
