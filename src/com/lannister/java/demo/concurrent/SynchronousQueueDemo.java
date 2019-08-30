package com.lannister.java.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

//SynchronousQueue���в��ܴ洢Ԫ�أ�
//ÿ�������������ȵ���һ���̵߳����Ƴ�������
//����������һֱ��������״̬
public class SynchronousQueueDemo {
	@Test
	public void test1() {
		SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(()->{
			try {

				synchronousQueue.put("a");
				TimeUnit.SECONDS.sleep(2);
				synchronousQueue.put("b");
				TimeUnit.SECONDS.sleep(2);
				synchronousQueue.put("c");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		executorService.execute(()->{
			try {
				System.out.println(synchronousQueue.take());
				System.out.println(synchronousQueue.take());
				System.out.println(synchronousQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
	@Test
	public void test2() throws InterruptedException {
		SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
		synchronousQueue.put("hello");
	}
}
