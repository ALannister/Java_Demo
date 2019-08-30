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

	//��ͨ����data.aִ��while(data.a == 0){}
	//ÿ��ʹ�õĶ��ǹ����ڴ��ֵ������ȥ���ڴ����
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
	
	//��ͨ����data.aִ��while(data.a == 0){System.out.println("--------------");}
	//ÿ��ʹ�õĿ����ǹ����ڴ��ֵ��Ҳ���ܻ�ȥ���ڴ����,��whileѭ���﷽���������й�
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
			System.out.println("--------------");//����������һС���������ʹ��whileѭ����ʱ�����a��ֵ
		}
		System.out.println(Thread.currentThread().getName() + " Job is Done!");
	}
	
	//volatile����data.bִ��while(data.a == 0){}
	//ÿ��ʹ�û�ȥ���ڴ����
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
