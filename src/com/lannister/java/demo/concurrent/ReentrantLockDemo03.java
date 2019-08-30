package com.lannister.java.demo.concurrent;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

//ReentrantLock
//lock()����:
//- �����û�б�����̳߳���,��ǰ�̻߳��������������,�������ĳ��м�������Ϊ1��
//- �����ǰ�߳��Ѿ����и���,���ĳ��м�����һ����������
//- ���������һ���̳߳���,��ô��ǰ�߳̽�һֱ����ֱ�������,�õ���֮�󽫳��м�������Ϊ1
//unlock()����:
//- �����ǰ�̳߳��и���,���ĳ��м�����һ
//     - �����ĳ��м�������0,���߳���Ȼ���и���
//     - �����ĳ��м�������0,���߳��ͷŸ���
//- �����ǰ�̲߳������и���,�׳�IllegalMonitorStateException�쳣
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
	
	//ReentrantLock�Ǳ��̳߳��е�,����߳�ӵ��������ô�߳��ڵķ����ķ��������Զ������
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
	
	//�����ǰ�̲߳������и���,�׳�IllegalMonitorStateException�쳣
	@Test
	public void test2() {
		ReentrantLockDemo03 r = new ReentrantLockDemo03();
		Thread t1 = new Thread(()->{
			r.method3();
		});
		t1.start();
	}
}

