package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

//死锁编码验证
//死锁定位 
//jsp -l
//jstack 发生死锁的进程号
public class DeadLockDemo {
	String lockA;
	String lockB;
	
	public void method(String lockA, String lockB) {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " 得到一把锁 " + lockA);
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + " 获得第二把锁 " + lockB);
			}
		}
		
	}
	
	public static void main(String[] args) {
		DeadLockDemo demo = new DeadLockDemo();
		String lockA = "lockA";
		String lockB = "lockB";
		
		new Thread(()->{
			demo.method(lockA, lockB);
		},"111").start();
		
		new Thread(()->{
			demo.method(lockB, lockA);
		},"222").start();
	}
}
