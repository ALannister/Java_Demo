package com.lannister.java.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

//当线程池中线程数目大于corePoolSize,空闲时间超过keepAliveTime的线程就会被销毁
public class ThreadPoolExecutorDemo {
	@Test
	public void test1() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1) );
		for(int i = 0; i < 2; i++) {
			threadPoolExecutor.submit(()->{
				System.out.println(Thread.currentThread().getName() + " core");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		
		threadPoolExecutor.submit(()->{
			System.out.println(Thread.currentThread().getName() + " queue");
		});
		
		for(int i = 0; i < 2; i++) {
			threadPoolExecutor.submit(()->{
				System.out.println(Thread.currentThread().getName() + " non-core");
				try {
					TimeUnit.SECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		Thread[] tarray;
		int i = 10;
		while(i-- > 0) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tarray = new Thread[Thread.activeCount()];
			Thread.enumerate(tarray);
			System.out.println("------------------");
			for (Thread thread : tarray) {
				
				System.out.println(thread.getName());
			}
		}
		threadPoolExecutor.shutdown();
	}
}
