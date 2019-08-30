package com.lannister.java.demo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

//�����̵߳�3�ַ�ʽ
class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " �̳�Thread");
	}
}

class MyRunner implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " ʵ��Runnable�ӿ�");
	}
}

class MyCall implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " ʵ��Callable�ӿ�");
		return "MyCall";
	}
	
}
public class ThreadDemo {
	@Test
	public void test1() {
		MyThread t = new MyThread();
		t.start();
	}
	
	@Test
	public void test2() {
		Thread t = new Thread(new MyRunner());
		t.start();
	}
	
	@Test
	public void test3() {
		FutureTask<String> f = new FutureTask<String>(new MyCall());
		Thread t = new Thread(f);
		t.start();
		try {
			System.out.println(f.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
