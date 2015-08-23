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
//		  System.out.println("提交任务...begin");
		  Future<Object> taskFuture = service.submit(taskThread);
//		  System.out.println("提交任务...end");
		  try {
		   Object re = taskFuture.get(100000, TimeUnit.MILLISECONDS);//超时设置，100s
//		   System.out.println(re);
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  } catch (ExecutionException e) {
		   e.printStackTrace();
		  } catch (TimeoutException e) {
//		   System.out.println("超时 取消任务");
		   taskFuture.cancel(true);
//		   System.out.println("超时 取消任务OK");
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
	  String result = "空结果";
	  try {
//	   System.out.println("任务开始....");
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
//	   result = "正确结果";
//	   System.out.println("任务结束....");
	  } catch (Exception e) {
	   System.out.println("Task is interrupted! ");
	  }
	  return result;
	 }

	}