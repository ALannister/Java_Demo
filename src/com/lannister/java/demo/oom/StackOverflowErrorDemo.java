package com.lannister.java.demo.oom;

public class StackOverflowErrorDemo {
	static int cnt = 0;
	public static void main(String[] args) {
		method();
	}

	private static void method() {
		method();
		System.out.println(cnt++);
		
	}
}
