package com.lannister.java.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//Semaphore����λһ������Ŀ���ޣ�ʹ��ǰ��Ҫ���룬ʹ�ú���Ҫ�ͷ�
public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for(int i = 0; i < 6; i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " ������λ��");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + " �뿪��λ��");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
				
			});
		}
		executorService.shutdown();
	}
}
