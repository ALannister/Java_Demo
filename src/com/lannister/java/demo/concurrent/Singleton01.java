package com.lannister.java.demo.concurrent;

//线程不安全的单例模式
public class Singleton01 {
	private static Singleton01 instance = null;

	private Singleton01() {
		System.out.println(Thread.currentThread().getName() + " Construction");
	}

	public static Singleton01 getInstance() {
		if (instance == null) {
			instance = new Singleton01();
		}

		return instance;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				Singleton01.getInstance();
			},String.valueOf(i)).start();
		}
	}
}
