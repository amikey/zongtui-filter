package com.gesoft.admin;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.util.TimeoutController.TimeoutException;
public class ThreadTest {  
  
    public static void main(String[] args) throws InterruptedException,  
            ExecutionException, TimeoutException {  
          
        final ExecutorService exec = Executors.newFixedThreadPool(1);  
          
        Callable<String> call = new Callable<String>() {  
            public String call() throws Exception {  
                //开始执行耗时操作  
                Thread.sleep(1000 * 5);  
                return "线程执行完成.";  
            }  
        };  
          
        try {  
//            Future<String> future = exec.submit(call);  
//            String obj = future.get(1000 * 1, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒  
        	Thread.sleep(10000);
            System.out.println("任务成功返回:" + "333");  
        } catch (Exception e) {  
            System.out.println("处理失败.");  
            e.printStackTrace();  
        }  
        // 关闭线程池  
        exec.shutdown();  
    }  
}  