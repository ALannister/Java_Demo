package com.lannister.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicReference;

//ÊÖÐ´×ÔÐýËø
class SpinLock {
	private AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();
	
	public void lock() {
		do {
			
		}while(!atomicReference.compareAndSet(null, Thread.currentThread()));
		
		System.out.println(Thread.currentThread().getName() + " get the lock");
	}
	
	public void unlock() {
		do {
			
		}while(!atomicReference.compareAndSet(Thread.currentThread(), null));
		System.out.println(Thread.currentThread().getName() + " release the lock");
	}
}

public class SpinLockDemo{
	public static void main(String[] args) {
		SpinLock lock = new SpinLock();
		
		for(int i = 0; i < 6; i++) {
			new Thread(()->{
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " is working");
				lock.unlock();
			}).start();
		}
	}
}