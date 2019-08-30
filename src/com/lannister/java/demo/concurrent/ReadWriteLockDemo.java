package com.lannister.java.demo.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

//ReentrantReadWriteLock �ܱ�֤ ��д �� д�� �� дд �Ĺ����Ƕ���ģ� ���� ��ʱ���ǹ����
class MyMap{
	private Map<String, Object> map = new HashMap<String, Object>();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private ReadLock readLock = lock.readLock();
	private WriteLock writeLock = lock.writeLock();
	
	public void get(String key) {
		readLock.lock();
		System.out.println(Thread.currentThread().getName() + " ��ʼ��ȡ");
		Object value = map.get(key);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ��ȡ��ɣ���ȡ�����: " + value);
		readLock.unlock();
	}
	
	public void put(String key, Object value) {
		writeLock.lock();
		System.out.println(Thread.currentThread().getName() + " ��ʼд��");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put(key, value);
		System.out.println(Thread.currentThread().getName() + " д�����,д������" + value);
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
