package com.gesoft.admin;

import java.util.concurrent.Callable;  
import java.util.concurrent.ExecutionException;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Future;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.TimeoutException;  

import com.gesoft.MainSearch.Search;
import com.gesoft.config.ConfigManager;

public class TimeoutTest1 {
	public static void runing(String keyword){
		  final ExecutorService service = Executors.newFixedThreadPool(1);

		  TaskThread taskThread = new TaskThread(keyword);
//		  System.out.println("�ύ����...begin");
		  Future<Object> taskFuture = service.submit(taskThread);
//		  System.out.println("�ύ����...end");
		  try {
		   Object re = taskFuture.get(100000, TimeUnit.MILLISECONDS);//��ʱ���ã�100s
//		   System.out.println(re);
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  } catch (ExecutionException e) {
		   e.printStackTrace();
		  } catch (TimeoutException e) {
//		   System.out.println("��ʱ ȡ������");
		   taskFuture.cancel(true);
//		   System.out.println("��ʱ ȡ������OK");
		  } finally {
		   service.shutdown();
		  }

		 }

	 public static void main(String[] args) {
		 runing("5555555");
	 }

	}

	class TaskThread implements Callable<Object> {
		 private String t;  
	        public TaskThread(String temp){  
	            this.t= temp;  
	        }  

	 public Object call() throws Exception {
	  String result = "�ս��";
	  try {
//	   System.out.println("����ʼ....");
//	   Thread.sleep(50000);
//	   System.out.println(t);
//	   Search.xiaoUrl(t, "");
//		  String numno=ConfigManager.getInstance().getConfigValue("NONUM");
//		  System.out.println(numno+"8888888888888888888888888");
//		  if ("0".equals(numno)) {
//			  System.out.println("1111111111111");
			  Search.xiaoUrlbaidu4haoshi(t, "");		
//		  Search.xiaoUrlbaidu5(t, "");		
//		  Search.xiaoUrlsogou(t, "");
//		}else{
//			Search.xiaoUrlsogou(t, "");
//		}
//		  Search.xiaoUrlsogou(t, "");
//	   result = "��ȷ���";
//	   System.out.println("�������....");
	  } catch (Exception e) {
	   System.out.println("Task is interrupted! ");
	  }
	  return result;
	 }

	}