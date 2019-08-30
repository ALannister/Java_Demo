package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

public class WaitDemo {
	
	public void method1(String lockA) throws InterruptedException {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " �õ�һ���� " + lockA);
			//TimeUnit.SECONDS.sleep(10);//sleep�����ͷ���
			//Thread.sleep(10*1000);//sleep�����ͷ���
			lockA.wait();//���ͷ���
			System.out.println(Thread.currentThread().getName() + " �õ�һ���� " + lockA);
		}
	}
	public void method2(String lockA) {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " �õ�һ���� " + lockA);
			lockA.notify();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		WaitDemo demo = new WaitDemo();
		String lockA = "lockA";
		
		new Thread(()->{
			try {
				demo.method1(lockA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"111").start();
		TimeUnit.SECONDS.sleep(1);
		new Thread(()->{
			demo.method2(lockA);
		},"222").start();
	}
}
