package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

//synchronized(o) 锁是对象o,锁的拥有者是线程，锁住的是对象o的所有实例方法
//synchronized(o。class) 锁是对象o所属类的Class对象,锁的拥有者是线程，锁住的是对象o所属类的所有静态方法
class Data3{
	//synchronized不能作为变量的修饰符
	int  field = 1;
	static int staticField = 2;
	String lock = "lock";
	
	//同步实例方法的锁是this对象,锁住的是该this对象的所有实例方法
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
	//同步静态方法的锁是该类的Class对象，锁住的是该类的所有静态方法
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
	
	//同步实例方法的锁是this对象,锁住的是该this对象的所有实例方法
	//不同线程争夺同一把锁时,得到锁的方法执行,没得到锁的方法等待
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
	
		//同一个线程在外层方法获取锁之后,在进入内层方法会自动获动该锁	
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
	
	//Data3.staticMethod1()与data.method1()不冲突,因为他们锁住的对象不一样
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
				//同步方法执行过程中,可以自由访问实例成员变量和静态成员变量
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
	
	//当方法锁住了某个变量，另一个线程还是可以读写这个变量
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
