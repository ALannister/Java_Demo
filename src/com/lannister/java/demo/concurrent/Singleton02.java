package com.lannister.java.demo.concurrent;

//�̰߳�ȫ�ĵ���ģʽ
public class Singleton02 {
	private static Singleton02 instance = null;

	private Singleton02() {
		System.out.println(Thread.currentThread().getName() + " Construction");
	}

	public static Singleton02 getInstance() {
		if (instance == null) {
			synchronized (Singleton02.class) {
				if(instance == null) {
					instance = new Singleton02();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				Singleton02.getInstance();
			},String.valueOf(i)).start();
		}
	}
}