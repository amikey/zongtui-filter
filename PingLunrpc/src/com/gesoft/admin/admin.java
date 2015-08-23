package com.gesoft.admin;

import com.gesoft.MainSearch.LookIp;
import com.gesoft.MainSearch.Search;
import com.gesoft.rpc.RpcClient;

public class admin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RpcClient client = new RpcClient();
		while (true) {
			try {
				String strTemp = client.turnHtml(LookIp.getIp(),"","10");
				if (strTemp!=null) {
					Search.Analytical(strTemp);					
				}else{
					System.out.println("服务器没有搭建完成，请等待1分钟");
				}
			} catch (Exception e) {
				System.out.println("服务器出现问题，请调整");
			} 
			System.out.println("本次运行完成");
		}
	}

}
