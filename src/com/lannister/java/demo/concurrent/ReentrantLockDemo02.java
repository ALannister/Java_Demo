package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

//lock(),tryLock(),lockInterruptibly()的区别
public class ReentrantLockDemo02 {
	
	// lock()忽视interrupt(), 拿不到锁就 一直阻塞：
	@Test
	public void test1() {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock");
		
		Thread t = new Thread(()->{
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " get lock");
			lock.unlock();
		},"t");
		
		t.start();
		System.out.println("t start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.interrupt();
		System.out.println("t was interrupted");
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//tryLock()，马上返回，拿到lock就返回true，不然返回false。
	@Test
	public void test2() {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock");
		
		Thread t = new Thread(()->{
			lock.tryLock();
			System.out.println(Thread.currentThread().getName() + (lock.isHeldByCurrentThread() ? " get lock" : " don't get lock"));
			try {
				lock.unlock();
			} catch (IllegalMonitorStateException e) {
			}
		},"t");
		t.start();
		System.out.println("t start");
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false。
	//等待期间如果别的进程调用此进程的interrupt()方法，此线程会被唤醒并被要求处理InterruptedException
	@Test
	public void test3() {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock");
		
		Thread t = new Thread(()->{
			try {
				lock.tryLock(5,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " handle the interrupte");
			}
			
			System.out.println(Thread.currentThread().getName() + (lock.isHeldByCurrentThread() ? " get lock" : " don't get lock"));
			try {
				lock.unlock();
			} catch (IllegalMonitorStateException e) {
			}
		},"t");
		t.start();
		System.out.println("t start");
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();
		System.out.println("t was interrupted");
		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//lockInterruptibly()是对lock()的改进，和lock()一样拿不到锁会一直阻塞
	//但是，等待期间如果别的进程调用此进程的interrupt()方法，此线程会被唤醒并被要求处理InterruptedException
	@Test
	public void test4() {
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " get lock");
		
		Thread t = new Thread(()->{
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " handle the interrupte");
			}
			System.out.println(Thread.currentThread().getName() + (lock.isHeldByCurrentThread() ? " get lock" : " don't get lock"));
			try {
				lock.unlock();
			} catch (IllegalMonitorStateException e) {
			}

		},"t");
		
		t.start();
		System.out.println("t start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.interrupt();
		System.out.println("t was interrupted");
		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
