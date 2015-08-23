package com.gesoft.config;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Global{
	public Document m_xmlDoc = null; /**���ĵ�����*/

	static Global config = null;
	
	/***������־�ָ��ߣ���ʼ��*/
	private static String start_link = "\r\n\r\n\r\n************************************************************\r\n";
	/***������־�ָ��ߣ�������*/
	private static String end_link = "\r\n************************************************************\r\n";
	
			
	public static Global getInstance(){
		if(config == null)
			config = new Global();
		
		return config;
	}
	
	private Global() {
        try {  
        	SAXReader reader = new SAXReader();      	
        	m_xmlDoc = reader.read("config/config.xml");
        	          
        } catch (DocumentException e) {
        	e.printStackTrace();
            return;
        }
	}
	
	
	public boolean getIsOutTestLog(){
		if(getConfigValue("isOutTestLog").equals("True")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean getIsWriteTestLog(){
		if(getConfigValue("isWriteTestLog").equals("True")){
			return true;
		}else{
			return false;
		}
	}
	
	public String getTestLogFilePath(){
		return getConfigValue("testLogFilePath");
	}
	
	public String getStartLink(){
		return start_link;
	}
	
	public String getEndLink(){
		return end_link;
	}
	
	/**
	 * ��ȡ�����ļ���Ϣ
	 * @param strParam
	 * @return
	 */
	public String getConfigValue(String strParam) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode("//" + strParam);
		return data.getText();
	}
	
	/**
	 * ���������ļ�ֵ
	 * lusd 2010-03-31
	 */
	public void setConfigValue(String strKey,String strVal) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode(strKey);
		data.setText(strVal);
		write();
	}
	
	/**
	 * �������ڵ�
	 * @param strId
	 * @param strUrl
	 * @param strPubNo
	 */
	public void addTaskByIndex(String strId,String strUrl){
		
		Element root = m_xmlDoc.getRootElement();
		Element downloadTask = root.element("DownTask");
		
		Element element = downloadTask.addElement("Task");
		element.setAttributeValue("ID", strId);
		element.setText(strId+"##"+strUrl);
		
		write();
	}
	
	/**
	 * ɾ���������ڵ�
	 * @param strPatId
	 * @param strDownloadType
	 */
	public void deleteTaskByIndex(String strUrl){
		
		Element root = m_xmlDoc.getRootElement();
		Element element = (Element)root.selectSingleNode("//Task[@ID='"+strUrl+"']");
		
		if(element == null)
			return;
		
		element.detach();
		write();
	}
	
	
	public ArrayList<String>  initLastTask(){
		
		Element root = m_xmlDoc.getRootElement();
		Element element = root.element("DownTask");
		
		List list = element.elements();
		ArrayList<String> listPatDown = new ArrayList<String>() ; 
		
		if (list.size()==0){
			System.out.println("û������");
			return null;
		}
		
		for(int i=0; i<list.size(); i++){
			Element elementTemp = (Element)list.get(i);
			String strKey = elementTemp.getStringValue();
			listPatDown.add(strKey);
		}
		return listPatDown;
	}
	
	
	public void write() {
        // ָ���ļ�
        XMLWriter writer = null;
        try {
            // ������ʽ
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gb2312");
            writer = new XMLWriter(new FileOutputStream("config/config"), format);
            writer.write(m_xmlDoc);
            writer.close();
        } catch (IOException e) {
        }
    }
	
	public static void main(String[] args){
		Global.getInstance().setConfigValue("NEWNO","000");
//		Global.getInstance().deleteTaskByIndex("4");
	}
}
