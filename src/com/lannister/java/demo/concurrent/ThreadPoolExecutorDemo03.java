package com.lannister.java.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;



public class ThreadPoolExecutorDemo03 {
	@Test
	public void test1() throws InterruptedException, ExecutionException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1) );
		for(int i = 0; i < 5; i++) {
			Future<String> future = threadPoolExecutor.submit(()->{
				System.out.println(Thread.currentThread().getName() + " core");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			},"task"+i);
			System.out.println(future.get());
		}
	}
}
