package com.lannister.java.demo.concurrent;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

//ReentrantLock
//lock()方法:
//- 如果锁没有被别的线程持有,当前线程获得锁并立即返回,并将锁的持有计数设置为1。
//- 如果当前线程已经持有该锁,锁的持有计数加一并立即返回
//- 如果锁由另一个线程持有,那么当前线程将一直阻塞直到获得锁,得到锁之后将持有计数设置为1
//unlock()方法:
//- 如果当前线程持有该锁,锁的持有计数减一
//     - 若锁的持有计数大于0,该线程仍然持有该锁
//     - 若锁的持有计数等于0,该线程释放该锁
//- 如果当前线程并不持有该锁,抛出IllegalMonitorStateException异常
public class ReentrantLockDemo03 {
	ReentrantLock lock = new ReentrantLock();
	public void method1() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock in method1, lock count " + lock.getHoldCount());
	}
	
	public void method2() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock in method2, lock count " + lock.getHoldCount());
	}
	
	public void method3() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock in method1, lock count " + lock.getHoldCount());
		lock.unlock();
		lock.unlock();
	}
	
	//ReentrantLock是被线程持有的,如果线程拥有锁，那么线程内的方法的方法都将自动获得锁
	@Test
	public void test1() {
		ReentrantLockDemo03 r = new ReentrantLockDemo03();
		Thread t1 = new Thread(()->{
			r.method1();
			r.method2();
		});
		
		Thread t2 = new Thread(()->{
			r.method1();
			r.method2();
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	//如果当前线程并不持有该锁,抛出IllegalMonitorStateException异常
	@Test
	public void test2() {
		ReentrantLockDemo03 r = new ReentrantLockDemo03();
		Thread t1 = new Thread(()->{
			r.method3();
		});
		t1.start();
	}
}

