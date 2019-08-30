package com.lannister.java.demo.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorDemo02 {
	
	//��main������������������Զ�����һ��DestroyJavaVM�̣߳����̻߳�ȴ�����User Thread �������˳�
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
	
	//test��������������User Thread ��ǿ���˳�
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
