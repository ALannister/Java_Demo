package com.lannister.java.demo.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

//ReentrantReadWriteLock 能保证 读写 、 写读 和 写写 的过程是独享的， 读读 的时候是共享的
class MyMap{
	private Map<String, Object> map = new HashMap<String, Object>();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ReadLock readLock = lock.readLock();
	private WriteLock writeLock = lock.writeLock();
	
	public void get(String key) {
		readLock.lock();
		System.out.println(Thread.currentThread().getName() + " 开始读取");
		Object value = map.get(key);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 读取完成，读取结果是: " + value);
		readLock.unlock();
	}
	
	public void put(String key, Object value) {
		writeLock.lock();
		System.out.println(Thread.currentThread().getName() + " 开始写入");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put(key, value);
		System.out.println(Thread.currentThread().getName() + " 写入完成,写入结果：" + value);
		writeLock.unlock();
	}
}

public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyMap map = new MyMap();
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		for(int i = 0; i < 5; i++) {
			final int tmpInt = i;
			executorService.execute(()->{
				map.put(tmpInt + "", tmpInt + "");
			});
		}
		for(int i = 0; i < 5; i++) {
			final int tmpInt = i;
			executorService.execute(()->{
				map.get(tmpInt + "");
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
