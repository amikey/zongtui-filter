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
					System.out.println("������û�д��ɣ���ȴ�1����");
				}
			} catch (Exception e) {
				System.out.println("�������������⣬�����");
			} 
			System.out.println("�����������");
		}
	}

}
