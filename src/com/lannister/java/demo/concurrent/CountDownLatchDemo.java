package com.lannister.java.demo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//CountDownLatch类似于火箭发射倒计时，倒计时结束，某些线程才能继续执行
public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(7);
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		for(int i = 0; i < 7; i++) {
			executorService.execute(()->{
				System.out.println(Thread.currentThread().getName() + "\t离开教室");
				countDownLatch.countDown();//倒计数为0后，会把所有的countDownLatch.await();都唤醒
			});
		}
		
		ExecutorService executorService2 = Executors.newCachedThreadPool();
		executorService2.execute(()->{
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t锁上教室门");
			
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t锁上教学楼门");
		});
		
		try {
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
		
		try {
			executorService2.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService2.shutdown();	
		
	}
}
