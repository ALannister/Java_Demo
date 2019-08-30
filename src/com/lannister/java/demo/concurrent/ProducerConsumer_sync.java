package com.lannister.java.demo.concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//通过synchronized、wait、notify实现生产者消费者
class Container{
	public static final int  SIZE = 5;
	private LinkedList<String> list = new LinkedList<String>();

	public synchronized void produce(String s) {
		while(list.size() >= SIZE) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		list.add(s);
		System.out.println(Thread.currentThread().getName() + "  produce " + s + "******");
		this.notify();
	}
	
	public synchronized void consume() {
		while(list.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() + "  consume " + list.remove());
		this.notify();
		
	}
	
}
public class ProducerConsumer_sync {
	public static void main(String[] args) {
		Container container = new Container();
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
