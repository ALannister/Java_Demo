package com.lannister.java.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//CyclicBarrier有点像集齐7颗龙珠才能召唤神龙
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
			System.out.println("***********************");
		});
		
		ExecutorService executorService = Executors.newFixedThreadPool(7);
		for(int i = 0; i < 7; i++) {
			executorService.execute(()->{
				
				System.out.println(Thread.currentThread().getId() + "\t 来到会议室");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getId() + "\t 开始开会");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}
		
		try {
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();

	}
}
