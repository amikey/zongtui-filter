package com.gesoft.WanfangBykeyWordMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import com.gesoft.MainSearch.staticNumint;
import com.gesoft.config.ConfigManager;

public class WanfangBykeyWordMain  {
	/*
	 * 读取文本文本
	 */
	String[] keys=getKeyWordFromFile("keyword.txt").split("\n");
	
	LinkedList<String> keyList=new LinkedList<String>();
	boolean bb=Collections.addAll(keyList, keys);
//	int numintn=Integer.valueOf(ConfigManager.getInstance().getConfigValue("NEWNO"));
	int numintn=staticNumint.readintNum();
//	Collections.addAll(keyList, keys);
	
	public static String getKeyWordFromFile(String FileName){
		//FileName : Config/keyword.txt
		StringBuilder strTemp =new StringBuilder();
		//String FilePath = System.getProperty("user.dir")+ "\\Config\\"+FileName;
		String FilePath = "Config/"+FileName;
		File file = new File(FilePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				strTemp.append(tempString);
				strTemp.append("\n");
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strTemp.toString();
	}
	public  String Keyword( int intnum ){
		// TODO Auto-generated method stub
		String stringbb="";
		if (keyList.size()<numintn) {
			numintn=0;
			int nnum=Integer.valueOf(ConfigManager.getInstance().getConfigValue("NO"));
			ConfigManager.getInstance().setConfigValue("NO",""+(nnum+1));
		}
		 for (int i = numintn; i < intnum+numintn; i++) {
			 try {
				 stringbb=stringbb+"##"+keyList.get(i);				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		 ConfigManager.getInstance().setConfigValue("NEWNO",""+(intnum+numintn));
		 staticNumint.intNum(intnum+numintn);
		System.out.println(stringbb);
		
		return stringbb;
	}

	
	
	public static void main(String[] args) {
		WanfangBykeyWordMain bb=new WanfangBykeyWordMain();
		bb.Keyword(4);
	}
}
