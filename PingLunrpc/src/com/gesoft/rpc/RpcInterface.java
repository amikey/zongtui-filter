package com.gesoft.rpc;

import com.gesoft.MainSearch.staticNumint;
import com.gesoft.WanfangBykeyWordMain.WanfangBykeyWordMain;
import com.gesoft.config.ConfigManager;

public class RpcInterface {
	
	/**
	 * ���������ṩ�ķ���
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
//		System.out.println("----------�������е�"+ConfigManager.getInstance().getConfigValue("NO")+",���еĵ�"+ConfigManager.getInstance().getConfigValue("NEWNO")+"------------");
		System.out.println("----------�������е�"+ConfigManager.getInstance().getConfigValue("NO")+",���еĵ�"+staticNumint.readintNum()+"------------");
		System.out.println("�û�ip:"+ip+"�����÷�������Keyword");	
		String returnstringkey="";
		try {
			WanfangBykeyWordMain bb=new WanfangBykeyWordMain();
			returnstringkey=bb.Keyword(10);
//		System.out.println("��ȡ�ؼ���Ϊ"+returnstringkey);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�׳��쳣");
		}
		return returnstringkey;
	}
	
	
}
