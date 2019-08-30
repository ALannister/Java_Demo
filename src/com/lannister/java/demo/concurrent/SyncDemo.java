package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

//synchronized(o) ���Ƕ���o,����ӵ�������̣߳���ס���Ƕ���o������ʵ������
//synchronized(o��class) ���Ƕ���o�������Class����,����ӵ�������̣߳���ס���Ƕ���o����������о�̬����
class Data3{
	//synchronized������Ϊ���������η�
	int  field = 1;
	static int staticField = 2;
	String lock = "lock";
	
	//ͬ��ʵ������������this����,��ס���Ǹ�this���������ʵ������
	public synchronized void method1() {
		System.out.println(Thread.currentThread().getName() + " method1 start");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " method1 end");
	} 
	
	public synchronized void method2() {
		System.out.println(Thread.currentThread().getName() + " method2 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " method2 end");
	}
	
	public synchronized void method3() {
		System.out.println(Thread.currentThread().getName() + " method3 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		method1();
		System.out.println(Thread.currentThread().getName() + " method3 end");
	}
	
	public void method4() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " method4 start");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " method4 end");			
		}
	} 
	//ͬ����̬���������Ǹ����Class������ס���Ǹ�������о�̬����
	public static synchronized void staticMethod1() {
		System.out.println(Thread.currentThread().getName() + " staticMethod1 start");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " staticMethod1 end");
	} 
	
	public static synchronized void staticMethod2() {
		System.out.println(Thread.currentThread().getName() + " staticMethod2 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " staticMethod2 end");
	} 
	
	public static synchronized void staticMethod3() {
		System.out.println(Thread.currentThread().getName() + " staticMethod3 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " staticMethod3 end");
	} 
}
public class SyncDemo {
	
	//ͬ��ʵ������������this����,��ס���Ǹ�this���������ʵ������
	//��ͬ�߳�����ͬһ����ʱ,�õ����ķ���ִ��,û�õ����ķ����ȴ�
	@Test
	public void test1() {
		Data3 data = new Data3();
		Thread t1 = new Thread(()->{
			data.method1();
		},"AAA");
		Thread t2 = new Thread(()->{
			data.method2();
		},"BBB");
		
		t1.start();
		try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
		//ͬһ���߳�����㷽����ȡ��֮��,�ڽ����ڲ㷽�����Զ��񶯸���	
		@Test
		public void test2() {
			Data3 data = new Data3();
			Thread t1 = new Thread(()->{
				data.method3();
			},"AAA");
			
			t1.start();
			
			try {
				t1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	
	//Data3.staticMethod1()��data.method1()����ͻ,��Ϊ������ס�Ķ���һ��
	@Test
	public void test4() {
		Data3 data = new Data3();
		Thread t1 = new Thread(()->{
			Data3.staticMethod1();
		},"AAA");
		Thread t2 = new Thread(()->{
			data.method1();
		},"BBB");
		Thread t3 = new Thread(()->{
			for(int i = 0; i < 10; i++) {
				
				try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
				//ͬ������ִ�й�����,�������ɷ���ʵ����Ա�����;�̬��Ա����
				System.out.println(Thread.currentThread().getName() + "\t field \t" + data.field);
				System.out.println(Thread.currentThread().getName() + "\t staticField \t" + Data3.staticField);
				
			}
		},"CCC");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//��������ס��ĳ����������һ���̻߳��ǿ��Զ�д�������
	@Test
	public void test5() {
		Data3 data = new Data3();
		Thread t1 = new Thread(()->{
			data.method4();
		});
		
		Thread t2 = new Thread(()->{
			for(int i = 0; i < 10; i++) {
				data.lock = "lock change";
				System.out.println(data.lock);
				try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
