package com.gesoft.MainSearch;

import java.util.concurrent.Callable;  
import java.util.concurrent.ExecutionException;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Future;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.TimeoutException;  
  
public class TimeOut {  
	
	public static void runing(String word){

        int timeout = 2; //��.  
        ExecutorService executor = Executors.newSingleThreadExecutor();  
        Boolean result = false;     
        Future<Boolean> future = executor.submit(new MyJob(word));// �������ύ���̳߳���     
        try {     
            result = future.get(timeout*1000, TimeUnit.MILLISECONDS);// �趨��200�����ʱ�������   
            System.out.println(result);  
        } catch (InterruptedException e) {  
            System.out.println("�߳��жϳ���");  
            future.cancel(true);// �ж�ִ�д�������߳�     
        } catch (ExecutionException e) {     
            System.out.println("�̷߳������");  
            future.cancel(true);// �ж�ִ�д�������߳�     
        } catch (TimeoutException e) {// ��ʱ�쳣     
            System.out.println("��ʱ��");     
            future.cancel(true);// �ж�ִ�д�������߳�     
        }finally{  
            System.out.println("�̷߳���رա�");  
            executor.shutdown();  
        }  
    
		
	}
	
    public static void main(String[] args){
    	
    	
    }  
      
    static class MyJob implements Callable<Boolean> {    
        private String t;  
        public MyJob(String temp){  
            this.t= temp;  
        }  
        public Boolean call() {
            for(int i=0;i<999999999;i++){
            	System.out.println(t);
                if(i==999999997){  
                    System.out.println(t);  
                }  
//                if (Thread.interrupted()){ //����Ҫ  
//                    return false;     
//                }  
            }   
            System.out.println("����ִ��..........");     
            return true;     
        }     
    }   
}  