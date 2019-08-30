package com.lannister.java.demo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//CountDownLatch�����ڻ�����䵹��ʱ������ʱ������ĳЩ�̲߳��ܼ���ִ��
public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(7);
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		for(int i = 0; i < 7; i++) {
			executorService.execute(()->{
				System.out.println(Thread.currentThread().getName() + "\t�뿪����");
				countDownLatch.countDown();//������Ϊ0�󣬻�����е�countDownLatch.await();������
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
			System.out.println(Thread.currentThread().getName() + "\t���Ͻ�����");
			
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t���Ͻ�ѧ¥��");
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
