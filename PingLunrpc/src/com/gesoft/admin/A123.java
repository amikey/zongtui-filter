package com.gesoft.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import com.gesoft.html.DownloadUtil;

public class A123 {
	
	 public static String getURLContent(String urlStr) {               
	       /** 网络的url地址 */        
	    URL url = null;              
	       /** http连接 */    
	    HttpURLConnection httpConn = null;            
	        /**//** 输入流 */   
	    BufferedReader in = null;   
	    StringBuffer sb = new StringBuffer();   
	    try{     
	     url = new URL(urlStr);     
	     in = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8") );   
	     String str = null;    
	     System.out.println(in);
	     while((str = in.readLine()) != null) {    
	      sb.append( str );     
	            }     
	        } catch (Exception ex) {   
	            
	        } finally{    
	         try{             
	          if(in!=null) {  
	           in.close();     
	                }     
	            }catch(IOException ex) {      
	            }     
	        }     
	        String result =sb.toString();     
	        System.out.println(result);     
	        return result;    
	        }    

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		 String N = "01:大汽车";   
		  String L="";   
		  String R="";   
		  int k= N.length();   
		  for (int i = 0; i < N.length(); i++)   
		  {   
//		   if (N.substring(i, i + 1).equals("|"))   
//		   {     
//		    L=N.substring(0,i).trim();   
//		    R=N.substring(i+1,k).trim();   
//		   }   
//		   else   
//		   {   
//		                
//		   }   
		   System.out.println(N.substring(0,i).trim());
//		   System.out.println(L);   
//		   System.out.println(R);   
	}
	} 

}
