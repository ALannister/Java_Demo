package com.lannister.java.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

//SynchronousQueue队列不能存储元素，
//每个插入操作必须等到另一个线程调用移除操作，
//否则插入操作一直处于阻塞状态
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
