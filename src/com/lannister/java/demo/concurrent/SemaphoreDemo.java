package com.lannister.java.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//Semaphore就像车位一样，数目有限，使用前需要申请，使用后需要释放
public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for(int i = 0; i < 6; i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " 抢到车位！");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + " 离开车位！");
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
