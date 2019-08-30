package com.lannister.java.demo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

//创建线程的3种方式
class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 继承Thread");
	}
}

class MyRunner implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " 实现Runnable接口");
	}
}

class MyCall implements Callable<String>{

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " 实现Callable接口");
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
