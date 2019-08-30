package com.lannister.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

//如何实现原子性
//volatile                         X
//synchronized                     √
//ReentrantLock                    √
//AtomicInteger.getAndIncrement()  √
//AtomicInteger.set()              X
class Data2{
	volatile int a = 0;
	int b = 0;
	int c = 0;
	AtomicInteger atomicInteger = new AtomicInteger();
	AtomicInteger atomicInteger2 = new AtomicInteger();
	ReentrantLock lock = new ReentrantLock();
	
	void volatileIncrement() {
		a++;
	}
	synchronized void syncIncrement() {
		b++;
	}
	void lockIncrement() {
		lock.lock();
		c++;
		lock.unlock();
	}
	void atomicIncrement() {
		atomicInteger.getAndIncrement();
	}
	
	void atomicIncrement2() {
		atomicInteger2.set(atomicInteger2.get() + 1);
	}
}
public class AtomicDemo {
	public static void main(String[] args) {
		Data2 data = new Data2();
		for(int i = 0; i < 30; i++) {
			new Thread(()->{
				for(int j = 0; j < 1000; j++) {
					data.volatileIncrement();
					data.syncIncrement();
					data.lockIncrement();
					data.atomicIncrement();
					data.atomicIncrement2();
				}
			}).start();
		}
		while(Thread.activeCount() > 1) {}
		
		System.out.println("volatileIncrement\t" + data.a);
		System.out.println("syncIncrement\t" + data.b);
		System.out.println("lockIncrement\t" + data.c);
		System.out.println("atomicIncrement\t" + data.atomicInteger);
		System.out.println("atomicIncrement2\t" + data.atomicInteger2);
	}
}
