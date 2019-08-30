package com.lannister.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

class Data1{
	
	volatile int b = 0;
	int a = 0;
	
	 public String toString() {
		 return a + "";
	 }
}
public class VolatileVisibilityDemo {

	//普通变量data.a执行while(data.a == 0){}
	//每次使用的都是工作内存的值，不会去主内存更新
	@Test
	public void loopWithoutDelay() {
		Data1 data = new Data1();
		new Thread(()->{
			
			System.out.println(Thread.currentThread().getName() + " is running");
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			data.a = 1;
			
			System.out.println(Thread.currentThread().getName() +" updates data");
		},"AAA").start(); 
		while(data.a == 0) {

		}
		System.out.println(Thread.currentThread().getName() + " Job is Done!");
	}
	
	//普通变量data.a执行while(data.a == 0){System.out.println("--------------");}
	//每次使用的可能是工作内存的值，也可能会去主内存更新,和while循环里方法体的语句有关
	@Test
	public void loopWithDelay() {
		Data1 data = new Data1();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName() + " is running");
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			data.a = 1;
			
			System.out.println(Thread.currentThread().getName() +" updates data");
		},"AAA").start(); 
		while(data.a == 0) {
			System.out.println("--------------");//这里拖延了一小会儿，可以使得while循环有时间更新a的值
		}
		System.out.println(Thread.currentThread().getName() + " Job is Done!");
	}
	
	//volatile变量data.b执行while(data.a == 0){}
	//每次使用会去主内存更新
	@Test
	public void VolatileVisibilityDemo() {
		Data1 data = new Data1();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName() + " is running");
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			data.b = 1;
			
			System.out.println(Thread.currentThread().getName() +" updates data");
		},"AAA").start(); 
		while(data.b == 0) {

		}
		System.out.println(Thread.currentThread().getName() + " Job is Done!");
	}
}
