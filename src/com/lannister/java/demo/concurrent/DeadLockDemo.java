package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

//����������֤
//������λ 
//jsp -l
//jstack ���������Ľ��̺�
public class DeadLockDemo {
	String lockA;
	String lockB;
	
	public void method(String lockA, String lockB) {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " �õ�һ���� " + lockA);
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + " ��õڶ����� " + lockB);
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
