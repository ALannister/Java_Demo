package com.lannister.java.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ÊÖÐ´Ò»¸öReentrantLock
class ReentrantLock{
	private boolean locked = false;
	private Thread lockedBy = null;
	private int count = 0;
	
	public synchronized void lock() {
		Thread thread = Thread.currentThread();
		while(locked == true && thread != lockedBy) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		locked = true;
		lockedBy = thread;
		count++;
	}
	
	public synchronized void unlock() {
		Thread thread = Thread.currentThread();
		if(lockedBy == thread) {
			count --;
			if(count == 0) {
				locked = false;
				lockedBy = null;
				this.notify();
			}
		}
	}
}
class NotReentrantLock{
	boolean locked = false;
	Thread lockedBy = null;

	
	public synchronized void lock() {
		Thread thread = Thread.currentThread();
		while(locked == true) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		locked = true;
		lockedBy = thread;
	}
	
	public synchronized void unlock() {
		Thread thread = Thread.currentThread();
		if(lockedBy == thread) {
			locked = false;
			lockedBy = null;
			this.notify();
		}
	}
}
class Data4{
	ReentrantLock lock = new ReentrantLock();
	//NotReentrantLock lock = new NotReentrantLock();
	public void method1() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "\t method1 in");
	
		method2();
		System.out.println(Thread.currentThread().getName() + "\t method1 out");
		lock.unlock();
	}
	public void method2() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "\t method2 in");
		System.out.println(Thread.currentThread().getName() + "\t method2 out");
		lock.unlock();
	}
}
public class ReentrantLockDemo {
	public static void main(String[] args) {
		Data4 data = new Data4();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for(int i = 0; i < 2; i++) {
			executorService.execute(()->{
				data.method1();
			});
		}
		executorService.shutdown();
	}
}
