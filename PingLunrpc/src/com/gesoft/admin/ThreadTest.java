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
                //��ʼִ�к�ʱ����  
                Thread.sleep(1000 * 5);  
                return "�߳�ִ�����.";  
            }  
        };  
          
        try {  
//            Future<String> future = exec.submit(call);  
//            String obj = future.get(1000 * 1, TimeUnit.MILLISECONDS); //������ʱʱ����Ϊ 1 ��  
        	Thread.sleep(10000);
            System.out.println("����ɹ�����:" + "333");  
        } catch (Exception e) {  
            System.out.println("����ʧ��.");  
            e.printStackTrace();  
        }  
        // �ر��̳߳�  
        exec.shutdown();  
    }  
}  