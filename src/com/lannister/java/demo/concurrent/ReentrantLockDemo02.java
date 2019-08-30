package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

//lock(),tryLock(),lockInterruptibly()������
public class ReentrantLockDemo02 {
	
	// lock()����interrupt(), �ò������� һֱ������
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
	
	//tryLock()�����Ϸ��أ��õ�lock�ͷ���true����Ȼ����false��
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
	
	//��ʱ�����Ƶ�tryLock()���ò���lock���͵�һ��ʱ�䣬��ʱ����false��
	//�ȴ��ڼ������Ľ��̵��ô˽��̵�interrupt()���������̻߳ᱻ���Ѳ���Ҫ����InterruptedException
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
	
	//lockInterruptibly()�Ƕ�lock()�ĸĽ�����lock()һ���ò�������һֱ����
	//���ǣ��ȴ��ڼ������Ľ��̵��ô˽��̵�interrupt()���������̻߳ᱻ���Ѳ���Ҫ����InterruptedException
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
