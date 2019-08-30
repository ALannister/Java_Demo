package com.lannister.java.demo.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorDemo02 {
	
	//在main函数结束后，虚拟机会自动启动一个DestroyJavaVM线程，该线程会等待所有User Thread 结束后退出
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		threadPoolExecutor.execute(()->{
			System.out.println(Thread.currentThread().getName() + " start");
			Thread[] tarray; 
			while(true) {
				tarray = new Thread[Thread.activeCount()];
				Thread.enumerate(tarray);
				System.out.println("------------------");
				for (Thread thread : tarray) {
					
					System.out.println(thread.getName() + "\t" +Thread.currentThread().isDaemon());
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(Thread.currentThread().getName() + "\t" +Thread.currentThread().isDaemon());
		threadPoolExecutor.shutdown();
	}
	
	//test函数结束后，所有User Thread 被强制退出
	@Test
	public void test() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		threadPoolExecutor.execute(()->{
			System.out.println(Thread.currentThread().getName() + " start");
			Thread[] tarray; 
			while(true) {
				tarray = new Thread[Thread.activeCount()];
				Thread.enumerate(tarray);
				System.out.println("------------------");
				for (Thread thread : tarray) {
					
					System.out.println(thread.getName() + "\t" +Thread.currentThread().isDaemon());
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(Thread.currentThread().getName() + "\t" +Thread.currentThread().isDaemon());
		threadPoolExecutor.shutdown();
	}
}
