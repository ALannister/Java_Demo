package com.lannister.java.demo.concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//通过ReentrantLock、await、signal实现生产者消费者
class Container02{
	public static final int  SIZE = 5;
	private LinkedList<String> list = new LinkedList<>();
	private ReentrantLock lock = new ReentrantLock();
	private Condition isFull = lock.newCondition();
	private Condition isEmpty = lock.newCondition();
	
	public void produce(String s) {
		lock.lock();
		while(list.size() >= SIZE) {
			try {
				isFull.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		list.add(s);
		System.out.println(Thread.currentThread().getName() + "  produce " + s + "******");
		isEmpty.signal();
		lock.unlock();
	}
	
	public void consume() {
		lock.lock();
		while(list.isEmpty()) {
			try {
				isEmpty.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() + "  consume " + list.remove());
		isFull.signal();
		lock.unlock();
	}
	
}
public class ProducerConsumer_lock {
	public static void main(String[] args) {
		Container02 container = new Container02();
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
