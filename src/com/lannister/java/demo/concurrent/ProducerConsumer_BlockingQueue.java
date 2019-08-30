package com.lannister.java.demo.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//通过阻塞队列实现生产者消费者模式
class Container03{

	private BlockingQueue<String> queue;
	public Container03(BlockingQueue<String> queue) {
		this.queue = queue; 
	}
	
	public void produce(String s) {
		try {
			queue.put(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "  produce " + s + "******");
	}
	
	public void consume() {
		try {
			System.out.println(Thread.currentThread().getName() + "  consume " + queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class ProducerConsumer_BlockingQueue {
	public static void main(String[] args) {
		Container03 container = new Container03(new LinkedBlockingQueue<String>(5));
		Random random = new Random();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for(int i = 0; i < 2; i++) {
			executorService.execute(()->{
				for(int j = 0; j < 10; j++) {
					String v =  random.nextInt(100) + "";
					container.produce(v);
					try {TimeUnit.SECONDS.sleep(random.nextInt(5));} catch (InterruptedException e) {e.printStackTrace();}
				}
			});
		}
		
		for(int i = 0; i < 2; i++) {
			executorService.execute(()->{
				for(int j = 0; j < 10; j++) {
					container.consume();
					try {TimeUnit.SECONDS.sleep(random.nextInt(10));} catch (InterruptedException e) {e.printStackTrace();}
				}
			});
		}
		
		executorService.shutdown();
	}
}
